package com.qa.barn.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.barn.persistence.domain.Barn;

public interface BarnRepo extends JpaRepository<Barn, Long> {

	// custom sql we want here we can have
	// findByName
	//
}
