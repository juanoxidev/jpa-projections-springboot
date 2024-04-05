package com.example.jpaproyections.projection.classbased;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecificationInputDTO {

	private String column;
	private String value;
	private String sortColumn;
	private String sortOrder;
	private Integer pageNum;
	private Integer pageSize;
}
