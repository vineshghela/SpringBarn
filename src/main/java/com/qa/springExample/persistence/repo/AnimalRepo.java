package com.qa.springExample.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springExample.persistence.domain.Animal;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {

	// Allows us to implement CRUD
	// CRUD
	// create
	// read
	// read one
	// update
	// delete
	// delete all

//custom sql that we want e.g. find by animal type
	// find my name
	// find my barn
//JPQL
}
