package com.example.jpaproyections.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.jpaproyections.entity.Employee;
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

private Specification<Employee> getSpecifition( SpecificationInputDTO specDTO) {
	return (root, query, criteriaBuilder) -> {
		return criteriaBuilder.equal(root.get(specDTO.getColumn()), specDTO.getValue());
	};
}

public List<Employee> getEmployeeData(SpecificationInputDTO specDTO){
	Specification<Employee> spec = getSpecifition(specDTO);
	return employeRepository.findAll(spec);
	}


public List<Employee> getEmployeeByName(){
	Specification<Employee> spec = getSpecifition();
	return employeRepository.findAll(spec);
	}

private Specification<Employee> getEmployeeSpecificationBetweenDates (SpecificationInputDTO specDTO) throws ParseException{
	String value = specDTO.getValue();
	String[] values = value.split(",");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date startDt = simpleDateFormat.parse(values[0]);
	Date endDt = simpleDateFormat.parse(values[1]);
	return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(specDTO.getColumn()),startDt, endDt); 

}

public List<Employee> getEmployeeBetweenDates(SpecificationInputDTO specDTO) throws ParseException{
	Specification<Employee> spec = getEmployeeSpecificationBetweenDates(specDTO);
	String sortColumn = specDTO.getSortColumn();
	String sortOrder  = specDTO.getSortOrder();
	boolean sortFlag = sortOrder.equalsIgnoreCase("ASC")? true: false;
	Sort sort = Sort.by(sortFlag?Sort.Direction.ASC : Sort.Direction.DESC, sortColumn);
	Integer pageNumber = 0;
	Integer pageSize = 2;
	pageNumber= specDTO.getPageNum() > 0? specDTO.getPageNum():pageNumber;
	pageSize= specDTO.getPageSize() > 0? specDTO.getPageSize():pageSize;
	Pageable page = PageRequest.of(pageNumber, pageSize, sort);
	Page<Employee> pageEmployee = employeRepository.findAll(spec, page);
	return pageEmployee.getContent();
	}


private Specification<Employee> getEmployeeSpecificationByLike (SpecificationInputDTO specDTO){
	return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(specDTO.getColumn()),"%" + specDTO.getValue() + "%");
}

public List<Employee> getEmployeeByLike(SpecificationInputDTO specDTO) {
	Specification<Employee> spec = getEmployeeSpecificationByLike(specDTO);
	return employeRepository.findAll(spec);
}


private Specification<Employee> getSpecificationByGreaterThan (SpecificationInputDTO specDTO){
	return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThan(root.get(specDTO.getColumn()),specDTO.getValue());
	// criteriaBuilder.greaterThanOrEqualTo(root.get(specDTO.getColumn()),specDTO.getValue());
}

public List<Employee> getEmployeeByGreaterThan(SpecificationInputDTO specDTO) {
	Specification<Employee> spec = getSpecificationByGreaterThan(specDTO);
	return employeRepository.findAll(spec);
}

}



