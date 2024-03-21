package com.example.jpaproyections.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpaproyections.entity.Product;
import com.example.jpaproyections.projection.classbased.ProductDTO;
import com.example.jpaproyections.projection.interfacebased.closed.ProductClosedView;
import com.example.jpaproyections.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/findAll")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	// Closed View Interface Based
	@GetMapping("/findAllProductsClosedView")
	public List<ProductClosedView> findAllProductsClosedView(){
		return productService.findBy();
	}
	
	@GetMapping("/findProductById/{idProduct}")
	public Optional<ProductClosedView> findProductById(@PathVariable Long idProduct){
		return productService.findProductById(idProduct);
	}
	
	// Class View Based
	@GetMapping("/findProductClassBassed")
	public List<ProductDTO> findProductClassBassed(){
		return productService.findProductBy();
	}
	
	//  View Dynamic
	
	@GetMapping("/findProductByBrandynamicClosedView/{brand}")
	public ProductClosedView findProductByBrandynamicClosedView (@PathVariable String brand) {
		return productService.findByBrandDynamicClosedView(brand);
	}
	
	@GetMapping("/findProductByBrandynamicClassBased/{brand}")
	public ProductDTO findProductByBrandynamicClassBased (@PathVariable String brand) {
		return productService.findByBrandDynamicClassBased(brand);
	}
	
	
	

}
