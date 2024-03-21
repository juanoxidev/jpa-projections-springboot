package com.example.jpaproyections.service;

import java.util.List;
import java.util.Optional;

import com.example.jpaproyections.entity.Product;
import com.example.jpaproyections.projection.classbased.ProductDTO;
import com.example.jpaproyections.projection.interfacebased.closed.ProductClosedView;

public interface ProductService {
	
	List<Product> findAll();
	
	List<ProductClosedView> findBy();
	
	Optional<ProductClosedView> findProductById(Long idProduct);
	
	List<ProductDTO> findProductBy();

	ProductClosedView findByBrandDynamicClosedView(String brand);
	
	ProductDTO findByBrandDynamicClassBased(String brand);
}
