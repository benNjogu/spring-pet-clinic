package com.keytech.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.keytech.model.Owner;
import com.keytech.repositories.OwnerRepository;
import com.keytech.repositories.PetRepository;
import com.keytech.repositories.PetTypeRepository;
import com.keytech.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService{
	
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		super();
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findById(Long id) {
		
		return ownerRepository.findById(id).orElse(null);
		
	}

	@Override
	public Owner save(Owner object) {
		
		return ownerRepository.save(object);
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}

	@Override
	public void delete(Owner object) {
		
		ownerRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		
		ownerRepository.deleteById(id);
		
	}

	@Override
	public Owner findByLastName(String lastName) {
		
		return ownerRepository.findByLastName(lastName);
		
	}

}
