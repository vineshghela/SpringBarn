package com.qa.springExample.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.springExample.dto.AnimalDto;
import com.qa.springExample.persistence.domain.Animal;
import com.qa.springExample.persistence.repo.AnimalRepo;

@Service
public class AnimalService {

	// this is where all LOGIC should/ WILL happen

//	all of our crud will happen here also

	// implement the CRUD functionality from the JPA Repo
	private AnimalRepo repo;

//	Make object mapping easy by automatically determining how one object model maps to another (dto <--> domain)
	private ModelMapper mapper;

//	private <dto> mapToDTO(<Object> obj){
//		return this.mapper.map(obj, <dto.class>)
//	}

	private AnimalDto mapToDTO(Animal animal) {
		return this.mapper.map(animal, AnimalDto.class);
	}

	@Autowired
	public AnimalService(AnimalRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public AnimalDto create(Animal animal) {
		return this.mapToDTO(this.repo.save(animal));
	}

	// Read all
	public List<AnimalDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		// Stream - returns a sequential stream considering the collection as the
		// source.
		// map - used to map an element to its corrosponding result
		// :: - shortHand - forEach
		// random.ints().limit(10).forEach(System.out::println)
		// Collectors - used to return a list or string (in a list)
	}

}
