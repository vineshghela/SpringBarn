package com.qa.barn.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String type;
	@NotNull
	private int age;

	@ManyToOne
	private Barn barn = null;

	public Animal(String name, String type, int age) {
		super();
		this.name = name;
		this.type = type;
		this.age = age;

	}

	public Animal(Long id, String name, String type, int age) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.age = age;
	}

}
