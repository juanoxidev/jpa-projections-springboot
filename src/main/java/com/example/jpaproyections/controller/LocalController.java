package com.example.jpaproyections.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpaproyections.entity.Local;
import com.example.jpaproyections.projection.interfacebased.open.LocalOpenView;
import com.example.jpaproyections.service.LocalService;

@RestController
@RequestMapping("/local")
public class LocalController {

	@Autowired
	private LocalService localService;
	
	@GetMapping("/findAll")
	public List<Local> findAll(){
		return localService.findAll();
	}
	
	// Open View Interface Based
	@GetMapping("/findAllLocalOpenView")
	public List<LocalOpenView> findAllLocalOpenView(){
		return localService.findBy();
	}
}
