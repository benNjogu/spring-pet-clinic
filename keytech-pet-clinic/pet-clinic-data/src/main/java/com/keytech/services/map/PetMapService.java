package com.keytech.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.keytech.model.Pet;
import com.keytech.services.CrudService;
import com.keytech.services.PetService;

@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService{

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Pet save(Pet object) {
		return super.save(object);
	}

	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Pet object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
