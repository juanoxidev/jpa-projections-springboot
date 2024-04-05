package com.example.jpaproyections.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpaproyections.entity.Employee;
import com.example.jpaproyections.projection.classbased.SpecificationInputDTO;
import com.example.jpaproyections.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/ByName")
	List<Employee> getByName(){
		return employeeService.getEmployeeByName();
	}
	
	@GetMapping("/ByEquals")
	List<Employee> ByEquals( @RequestBody SpecificationInputDTO specDTO){
		return employeeService.getEmployeeData(specDTO);
	}
	
	@GetMapping("/ByBetweenDates")
	List<Employee> byBetweenDates(@RequestBody SpecificationInputDTO specDTO) throws ParseException{
		return employeeService.getEmployeeBetweenDates(specDTO);
	}
	
	@GetMapping("/ByLike")
	List<Employee> byLikeOperation(@RequestBody SpecificationInputDTO specDTO){
		return employeeService.getEmployeeByLike(specDTO);
	}
	
	@GetMapping("/ByGreaterThanEqual")
	List<Employee> byGreaterThanEqual(@RequestBody SpecificationInputDTO specDTO){
		return employeeService.getEmployeeByGreaterThan(specDTO);
	}
}
