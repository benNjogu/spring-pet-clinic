package com.keytech.Services;

import java.util.Set;

import com.keytech.Model.Pet;

public interface PetService {

	
	
	Pet findById(Long id);

	Pet save(Pet pet);

	Set<Pet> findAll();

}
