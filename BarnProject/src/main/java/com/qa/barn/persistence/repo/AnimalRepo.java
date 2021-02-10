package com.qa.barn.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.barn.persistence.domain.Animal;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {

}
