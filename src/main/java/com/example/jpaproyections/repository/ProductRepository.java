package com.example.jpaproyections.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaproyections.entity.Product;
import com.example.jpaproyections.projection.interfacebased.closed.ProductClosedView;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// Closed View Interface Based
	List<ProductClosedView> findBy();
	
	Optional<ProductClosedView> findProductById(Long idProduct);
}
