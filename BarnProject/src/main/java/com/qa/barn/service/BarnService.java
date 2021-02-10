package com.qa.barn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.barn.dto.BarnDto;
import com.qa.barn.exceptions.BarnNotFoundException;
import com.qa.barn.persistence.domain.Barn;
import com.qa.barn.persistence.repo.BarnRepo;
import com.qa.barn.utils.SpringBeanUtil;

@Service
public class BarnService {

	// Handles all of our business logic.

	// Allows us to implement CRUD in our service
	private BarnRepo repo;

	// Allows us to map DTO and domain automatically by working out which ones link
	private ModelMapper mapper;

	// Map to DTO
	private BarnDto mapToDTO(Barn barn) {
		return this.mapper.map(barn, BarnDto.class);
	}

	@Autowired
	public BarnService(BarnRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create
	public BarnDto create(Barn barn) {
		return this.mapToDTO(this.repo.save(barn));
	}

	// Read All
	public List<BarnDto> readllAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		// Stream -returns a sequential stream of data considering a collection as its
		// source.
		// map - used to do something to each elements for its corresponding result.
		// :: - forEach (short hand)
		// Collectors - return them to a list
	}

	// Read by Id
	public BarnDto readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(BarnNotFoundException::new));
	}

	// Update
	public BarnDto update(BarnDto barnDto, Long id) {
		// check if the record exists
		Barn toUpdate = this.repo.findById(id).orElseThrow(BarnNotFoundException::new);
		// set the record to update
		toUpdate.setName(barnDto.getName());
//		check during the update for any nulls
		SpringBeanUtil.mergeNotNull(barnDto, toUpdate);
//		return the method from the repo which is to save
		return this.mapToDTO(this.repo.save(toUpdate));
	}

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);// where we actually do the delete
		return !this.repo.existsById(id);// verify
	}

	// Custom methods
	public List<BarnDto> findbyColour(String colour) {
		return this.repo.findByColour(colour).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public List<BarnDto> findByName(String name) {
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

}
