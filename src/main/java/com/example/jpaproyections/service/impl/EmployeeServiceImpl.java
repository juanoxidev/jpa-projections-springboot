package com.example.jpaproyections.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.jpaproyections.entity.Employee;
import com.example.jpaproyections.projection.classbased.SearchSpecification;
import com.example.jpaproyections.projection.classbased.SpecificationInputDTO;
import com.example.jpaproyections.repository.EmployeeRepository;
import com.example.jpaproyections.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeRepository;

	private Specification<Employee> getSpecifition() {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("name"), "Juan");
		};
	}

	private Specification<Employee> getSpecifition(SpecificationInputDTO specDTO) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(specDTO.getColumn()), specDTO.getValue());
		};
	}

	public List<Employee> getEmployeeData(SpecificationInputDTO specDTO) {
		Specification<Employee> spec = getSpecifition(specDTO);
		return employeRepository.findAll(spec);
	}

	public List<Employee> getEmployeeByName() {
		Specification<Employee> spec = getSpecifition();
		return employeRepository.findAll(spec);
	}

	private Specification<Employee> getEmployeeSpecificationBetweenDates(SpecificationInputDTO specDTO)
			throws ParseException {
		String value = specDTO.getValue();
		String[] values = value.split(",");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date startDt = simpleDateFormat.parse(values[0]);
		Date endDt = simpleDateFormat.parse(values[1]);
		return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(specDTO.getColumn()), startDt, endDt);

	}

	public List<Employee> getEmployeeBetweenDates(SpecificationInputDTO specDTO) throws ParseException {
		Specification<Employee> spec = getEmployeeSpecificationBetweenDates(specDTO);
		String sortColumn = specDTO.getSortColumn();
		String sortOrder = specDTO.getSortOrder();
		boolean sortFlag = sortOrder.equalsIgnoreCase("ASC") ? true : false;
		Sort sort = Sort.by(sortFlag ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn);
		Integer pageNumber = 0;
		Integer pageSize = 2;
		if (specDTO.getPageNum() != null && specDTO.getPageSize() != null) {
			pageNumber = specDTO.getPageNum() > 0 ? specDTO.getPageNum() : pageNumber;
			pageSize = specDTO.getPageSize() > 0 ? specDTO.getPageSize() : pageSize;
		}
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Employee> pageEmployee = employeRepository.findAll(spec, page);
		return pageEmployee.getContent();
	}

	private Specification<Employee> getEmployeeSpecificationByLike(SpecificationInputDTO specDTO) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(specDTO.getColumn()),
				"%" + specDTO.getValue() + "%");
	}

	public List<Employee> getEmployeeByLike(SpecificationInputDTO specDTO) {
		Specification<Employee> spec = getEmployeeSpecificationByLike(specDTO);
		return employeRepository.findAll(spec);
	}

	private Specification<Employee> getSpecificationByGreaterThan(SpecificationInputDTO specDTO) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(specDTO.getColumn()),
				specDTO.getValue());
//	criteriaBuilder.lessThan(root.get(specDTO.getColumn()),specDTO.getValue());

	}

	public List<Employee> getEmployeeByGreaterThan(SpecificationInputDTO specDTO) {
		Specification<Employee> spec = getSpecificationByGreaterThan(specDTO);
		return employeRepository.findAll(spec);
	}

	public long getEmployeeByGreaterThanDelete(SpecificationInputDTO specDTO) {
		Specification<Employee> spec = getSpecificationByGreaterThan(specDTO);
		Long empCount = employeRepository.count(spec);
		System.out.printf("%d empleados cumplen con la consulta. %n", empCount);
		boolean emExists = employeRepository.exists(spec);
		long deleteStatus = 0;
		if (emExists) {
			List<Employee> aEliminar = employeRepository.findAll(spec);
			for (Employee employee : aEliminar) {
				deleteStatus++;
				System.out.printf("El empleado/a %s sera eliminado %n", employee.getName());
				employeRepository.delete(employee);
			}
		}
		return deleteStatus;

	}

	private Specification<Employee> getSpecificationForList(List<SearchSpecification> searchSpecificationList,
			String overallOp) {

		List<Predicate> predicateList = new ArrayList<>();
		return (root, query, criteriaBuilder) -> {
			for (SearchSpecification sf : searchSpecificationList) {
				String operation = sf.getOperation();
				switch (operation) {
				case "EQUAL":
					predicateList.add(criteriaBuilder.equal(root.get(sf.getColumn()), sf.getValue()));
					break;
				case "GREATER_THAN":
					predicateList.add(criteriaBuilder.greaterThan(root.get(sf.getColumn()), sf.getValue()));
					break;

				case "GREATER_THAN_EQUAL":
					predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(sf.getColumn()), sf.getValue()));
					break;

				case "LESS_THAN":
					predicateList.add(criteriaBuilder.lessThan(root.get(sf.getColumn()), sf.getValue()));
					break;

				case "LESS_THAN_EQUAL":
					predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(sf.getColumn()), sf.getValue()));
					break;

				case "LIKE":
					predicateList.add(criteriaBuilder.like(root.get(sf.getColumn()), "%" + sf.getValue() + "%"));
					break;

				case "IN":
					String[] sp = sf.getValue().split(",");
					predicateList.add(root.get(sf.getColumn()).in(Arrays.asList(sp)));
					break;

				case "JOIN":
					predicateList.add(
							criteriaBuilder.equal(root.join(sf.getJoinTable()).get(sf.getColumn()), sf.getValue()));
					break;
				}
			}
			if ("AND".equalsIgnoreCase(overallOp)) {
				return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
			} else {
				return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
			}

		};
	}

	public List<Employee> getDetailsFromList(List<SearchSpecification> searchSpecificationList, String overallOp) {
		Specification<Employee> spec = getSpecificationForList(searchSpecificationList, overallOp);
		return employeRepository.findAll(spec);
	}

}
