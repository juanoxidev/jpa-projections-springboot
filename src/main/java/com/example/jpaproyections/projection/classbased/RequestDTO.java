package com.example.jpaproyections.projection.classbased;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDTO {

	List<SearchSpecification> specificationList;
	String overallOperation;
}
