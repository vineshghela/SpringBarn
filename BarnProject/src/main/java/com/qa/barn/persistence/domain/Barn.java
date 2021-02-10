package com.qa.barn.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Barn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String colour;
	@NotNull
	private double area;
	@NotNull
//	@Column(name = "address")
	private String name;

	@OneToMany(mappedBy = "barn", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Animal> animals;

	public Barn(@NotNull String colour, @NotNull double area, @NotNull String name) {
		super();
		this.colour = colour;
		this.area = area;
		this.name = name;
	}

	// Create we dont have an ID
	public Barn(Long id, @NotNull String colour, @NotNull double area, @NotNull String name) {
		super();
		this.id = id;
		this.colour = colour;
		this.area = area;
		this.name = name;
	}

}
