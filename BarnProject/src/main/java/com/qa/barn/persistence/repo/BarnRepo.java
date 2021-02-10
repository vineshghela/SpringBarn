package com.qa.barn.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.barn.persistence.domain.Barn;

@Repository
public interface BarnRepo extends JpaRepository<Barn, Long> {

	// custom sql we want here we can have
	// findByName
	//
//	JPQL
	@Query(value = "SELECT * FROM BARN WHERE COLOUR=?1", nativeQuery = true)
	List<Barn> findByColour(String colour);

	@Query(value = "SELECT * FROM BARN WHERE NAME=?1", nativeQuery = true)
	List<Barn> findByName(String name);
}
