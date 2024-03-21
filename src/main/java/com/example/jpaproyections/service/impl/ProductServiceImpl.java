package com.example.jpaproyections.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpaproyections.entity.Product;
import com.example.jpaproyections.projection.classbased.ProductDTO;
import com.example.jpaproyections.projection.interfacebased.closed.ProductClosedView;
import com.example.jpaproyections.repository.ProductRepository;
import com.example.jpaproyections.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<ProductClosedView> findBy() {
		// TODO Auto-generated method stub
		return productRepository.findBy();
	}

	@Override
	public Optional<ProductClosedView> findProductById(Long idProduct) {
		// TODO Auto-generated method stub
		return productRepository.findProductById(idProduct);
	}

	@Override
	public List<ProductDTO> findProductBy() {
		return productRepository.findProductBy();
	}

	@Override
	public ProductClosedView findByBrandDynamicClosedView(String brand) {
		ProductClosedView productClosedView = productRepository.findProductByBrand(brand, ProductClosedView.class);
		return productClosedView;
	}

	@Override
	public ProductDTO findByBrandDynamicClassBased(String brand) {
		ProductDTO productDTO = productRepository.findProductByBrand(brand, ProductDTO.class);
		return productDTO;
	}

}
