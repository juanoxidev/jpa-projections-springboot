package com.example.jpaproyections.projection.classbased;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSpecification {
	private String column;
	private String value;
	private String operation;
	private String joinTable;
}
