package com.keytech.springdatajpa;

import static org.mockito.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keytech.model.Owner;
import com.keytech.repositories.OwnerRepository;
import com.keytech.repositories.PetRepository;
import com.keytech.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	private static final long ID = 1L;

	private static final String LAST_NAME = "emma";

	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService service;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testFindById() {
		Optional<Owner> returnedOwner = Optional.ofNullable(Owner.builder().id(1L).build());
		when(ownerRepository.findById(any())).thenReturn(returnedOwner);

		Owner emma = service.findById(ID);
		assertEquals(emma, service.findById(ID));
		
	}

	@Test
	void testSave() {
		Owner returnedOwner = Owner.builder().id(ID).build();
		when(ownerRepository.save(any())).thenReturn(returnedOwner);
		
		Owner owner = service.save(Owner.builder().id(ID).build());
		assertEquals(owner, service.save(owner));
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = new HashSet<>();
		Owner returnedOwner = Owner.builder().id(ID).build();
		owners.add(returnedOwner);
		when(ownerRepository.findAll()).thenReturn(owners);
		
		assertEquals(1, service.findAll().size());
	}

	@Test
	void testDelete() {
		Owner owner = Owner.builder().id(ID).build();
		service.save(owner);
		service.delete(service.findById(ID));
		assertEquals(0, service.findAll().size());
	}

	@Test
	void testDeleteById() {
		Owner owner = Owner.builder().id(ID).build();
		service.save(owner);
		service.deleteById(ID);
		assertEquals(0, service.findAll().size());
	}

	@Test
	void testFindByLastName() {
		Owner returnedOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
		when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
		
		Owner emma = service.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, returnedOwner.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

}
