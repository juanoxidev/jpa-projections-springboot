package com.example.jpaproyections.projection.interfacebased.open;

import org.springframework.beans.factory.annotation.Value;

public interface LocalOpenView {
	/*
	 * Podemos personalizar lo que nos devuelve:
	 * 
	 * En este caso concatena el nombre y el piso del objeto Local
	 * y lo retorna, segun lo indicado en la anotation @Value
	 */
	@Value("#{target.name + ' ' + target.floor}")
	String getNameAndLocal();

}
