package com.example.jpaproyections.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaproyections.entity.Address;

@Repository
public interface AdressRepository extends JpaRepository<Address, Long>{

}
