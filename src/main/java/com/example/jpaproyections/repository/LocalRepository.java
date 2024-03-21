package com.example.jpaproyections.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaproyections.entity.Local;
import com.example.jpaproyections.projection.interfacebased.open.LocalOpenView;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
	
	List<LocalOpenView> findBy();
}
