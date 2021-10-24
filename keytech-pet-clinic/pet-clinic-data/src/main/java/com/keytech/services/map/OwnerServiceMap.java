package com.keytech.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.keytech.model.Owner;
import com.keytech.model.Pet;
import com.keytech.services.OwnerService;
import com.keytech.services.PetService;
import com.keytech.services.PetTypeService;
import com.sun.istack.FinalArrayList;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;
	private final PetService petService;
	
	public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Owner findById(Long id) {

		return super.findById(id);
	}

	@Override
	public Owner save(Owner object) {
		
		if (object != null) {
			if (object.getPets()!=null) {
				object.getPets().forEach(pet ->{
					if(pet.getPetType() != null) {
						if(pet.getPetType().getId()==null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					}else {
						throw new RuntimeException("Pet type is required");
					}
					
					if(pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			
			return super.save(object);
		}else {
			return null;
		}
			
	}

	@Override
	public Set<Owner> findAll() {

		return super.findAll();
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
