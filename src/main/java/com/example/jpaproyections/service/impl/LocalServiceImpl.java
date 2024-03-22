package com.example.jpaproyections.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.jpaproyections.entity.Local;
import com.example.jpaproyections.projection.interfacebased.open.LocalOpenView;
import com.example.jpaproyections.repository.LocalRepository;
import com.example.jpaproyections.service.LocalService;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalRepository localRepository;

	@Override
	public List<Local> findAll() {
		return localRepository.findAll();
	}

	@Override
	public List<LocalOpenView> findBy() {
		// TODO Auto-generated method stub
		return localRepository.findBy();
	}

	@Override
	public List<LocalOpenView> findByFloor(String floor) {
		// TODO Auto-generated method stub
		return localRepository.findByFloor(floor);
	}

	@Override
	public List<LocalOpenView> findByName() {
		// TODO Auto-generated method stub
		return null;
	}


}
