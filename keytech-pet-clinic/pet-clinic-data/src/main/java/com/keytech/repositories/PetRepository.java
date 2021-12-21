package com.keytech.repositories;

import org.springframework.data.repository.CrudRepository;

import com.keytech.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{

}
