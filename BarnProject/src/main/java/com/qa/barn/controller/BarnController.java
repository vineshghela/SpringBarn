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

import com.qa.barn.dto.BarnDto;
import com.qa.barn.persistence.domain.Barn;
import com.qa.barn.service.BarnService;

@RestController
@CrossOrigin
@RequestMapping("/barn")
public class BarnController {

	private BarnService service;

	@Autowired
	public BarnController(BarnService service) {
		super();
		this.service = service;
	}

	// pathvariable - URL pattern matching
	// REquestBody -append the data of the message to the body of the request

//	C
	@PostMapping("/create")
	public ResponseEntity<BarnDto> create(@RequestBody Barn barn) {
		BarnDto created = this.service.create(barn);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

//	R
	@GetMapping("/read")
	public ResponseEntity<List<BarnDto>> readAll() {
		return ResponseEntity.ok(this.service.readllAll());
	}

//	R  by 1
	@GetMapping("/read/{id}")
	public ResponseEntity<BarnDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}

//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<BarnDto> update(@PathVariable Long id, @RequestBody BarnDto barnDto) {

		return new ResponseEntity<>(this.service.update(barnDto, id), HttpStatus.ACCEPTED);
	}

//	D
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BarnDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		<condition>? <true> : false;
	}

}
