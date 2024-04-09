package com.example.jpaproyections.service;

import java.text.ParseException;
import java.util.List;

import com.example.jpaproyections.entity.Employee;
import com.example.jpaproyections.projection.classbased.SearchSpecification;
import com.example.jpaproyections.projection.classbased.SpecificationInputDTO;

public interface EmployeeService {
	
	 List<Employee> getEmployeeByName();
	 
	 List<Employee> getEmployeeData(SpecificationInputDTO specDTO);
	 
	 List<Employee> getEmployeeBetweenDates(SpecificationInputDTO specDTO) throws ParseException;

	 List<Employee> getEmployeeByLike(SpecificationInputDTO specDTO);
	 
	 List<Employee> getEmployeeByGreaterThan(SpecificationInputDTO specDTO);
	 
	 long getEmployeeByGreaterThanDelete(SpecificationInputDTO specDTO);
	 
	 List<Employee> getDetailsFromList(List<SearchSpecification> searchSpecificationList, String overallOp);
}
