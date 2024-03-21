package com.example.jpaproyections.service;

import java.util.List;

import com.example.jpaproyections.entity.Local;
import com.example.jpaproyections.projection.interfacebased.open.LocalOpenView;

public interface LocalService {
	
	List<Local> findAll();
	
	List<LocalOpenView> findBy();

}
