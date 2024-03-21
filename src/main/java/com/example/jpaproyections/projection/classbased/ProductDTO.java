package com.example.jpaproyections.projection.classbased;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ProductDTO {
	
	/*
	*
	* Los atributos de la clase DTO deben tener el mismo nombre que 
	* los que va a mapear en la entidad de la base de datos
	*
	*/
	private String name;
	
	private String brand;
	
	private BigDecimal price;

}
