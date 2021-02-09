package com.qa.springExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springExample.dto.AnimalDto;
import com.qa.springExample.persistence.domain.Animal;
import com.qa.springExample.service.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalsController {

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hello " + name;
	}

	private AnimalService animalService;

	@Autowired
	public AnimalsController(AnimalService animalService) {
		super();
		this.animalService = animalService;
	}

	// @pathVariable
	// @RequestBody

	// Create
	@PostMapping("/create")
	public ResponseEntity<AnimalDto> create(@RequestBody Animal animal) {
		AnimalDto created = this.animalService.create(animal);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
//		if all good - return a status code of created (201);

	}

	// read all method
	@GetMapping("/read")
	public ResponseEntity<List<AnimalDto>> read() {
		return ResponseEntity.ok(this.animalService.readAll());
		// OK == 200
	}

}
