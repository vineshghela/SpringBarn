package com.qa.barn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.barn.dto.AnimalDto;
import com.qa.barn.persistence.domain.Animal;
import com.qa.barn.persistence.repo.AnimalRepo;
import com.qa.barn.utils.SpringBeanUtil;

@Service
public class AnimalService {

	// Handles all of our business logic.

	// Allows us to implement CRUD in our service
	private AnimalRepo repo;

	// Allows us to map DTO and domain automatically by working out which ones link
	private ModelMapper mapper;

	// Map to DTO
	private AnimalDto mapToDTO(Animal animal) {
		return this.mapper.map(animal, AnimalDto.class);
	}

	@Autowired
	public AnimalService(AnimalRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create
	public AnimalDto create(Animal animal) {
		return this.mapToDTO(this.repo.save(animal));
	}

	// Read All
	public List<AnimalDto> readllAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		// Stream -returns a sequential stream of data considering a collection as its
		// source.
		// map - used to do something to each elements for its corresponding result.
		// :: - forEach (short hand)
		// Collectors - return them to a list
	}

	// Read by Id
	public AnimalDto readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// Update
	public AnimalDto update(AnimalDto AnimalDto, Long id) {
		// check if the record exists
		Animal toUpdate = this.repo.findById(id).orElseThrow();
		// set the record to update
		toUpdate.setName(AnimalDto.getName());
//		check during the updaye for any nulls
		SpringBeanUtil.mergeNotNull(AnimalDto, toUpdate);
//		return the method from the repo which is to save
		return this.mapToDTO(this.repo.save(toUpdate));
	}

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);//
		return !this.repo.existsById(id);//
	}

}
