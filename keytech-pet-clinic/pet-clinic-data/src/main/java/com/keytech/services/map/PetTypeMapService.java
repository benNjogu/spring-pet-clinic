package com.keytech.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.keytech.model.PetType;
import com.keytech.services.PetTypeService;

@Service
@Profile({"default","map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService{

	@Override
	public
	Set<PetType> findAll() {
		return super.findAll();
	}

	@Override
	public
	PetType findById(Long id) {
		return super.findById(id);
	}

	@Override
	public
	PetType save(PetType object) {
		return super.save(object);
	}

	@Override
	public
	void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public
	void delete(PetType object) {
		super.delete(object);
	}

	
}
