package com.qa.barn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.barn.dto.AnimalDto;
import com.qa.barn.persistence.domain.Animal;
import com.qa.barn.service.AnimalService;

@RestController
@CrossOrigin
@RequestMapping("/animal")
public class AnimalController {

	private AnimalService service;

	@Autowired
	public AnimalController(AnimalService service) {
		super();
		this.service = service;
	}

	// pathvariable - URL pattern matching
	// REquestBody -append the data of the message to the body of the request

//	C
	@PostMapping("/create")
	public ResponseEntity<AnimalDto> create(@RequestBody Animal animal) {
		AnimalDto created = this.service.create(animal);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

//	R
	@GetMapping("/read")
	public ResponseEntity<List<AnimalDto>> readAll() {
		return ResponseEntity.ok(this.service.readllAll());
	}

//	R  by 1
	@GetMapping("/read/{id}")
	public ResponseEntity<AnimalDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}

//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<AnimalDto> update(@PathVariable Long id, @RequestBody AnimalDto animalDto) {

		return new ResponseEntity<>(this.service.update(animalDto, id), HttpStatus.ACCEPTED);
	}

//	D
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AnimalDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		<condition>? <true> : false;
	}

}
