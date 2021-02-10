package com.qa.barn.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BarnDto {

	private Long id;
	private String colour;
	private double area;
	private String name;

	private List<AnimalDto> animals = new ArrayList<>();

}
