package com.keytech.springdatajpa;

import static org.mockito.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
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
	
	Owner returnedOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		
		returnedOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(any())).thenReturn(Optional.of(returnedOwner));

		Owner owner = service.findById(ID);
		assertNotNull(owner);
	}
	
	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(any())).thenReturn(Optional.empty());

		Owner owner = service.findById(ID);
		assertNull(owner);
	}

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(ID).build();
		when(ownerRepository.save(any())).thenReturn(returnedOwner);
		
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		
		verify(ownerRepository).save(any());
	}

	@Test
	void testFindAll() {
		Set<Owner> returnedOwners = new HashSet<>();
		returnedOwners.add(returnedOwner);
		returnedOwners.add(Owner.builder().id(2l).build());
		when(ownerRepository.findAll()).thenReturn(returnedOwners);
		
		Set<Owner> owners = service.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testDelete() {
		service.delete(returnedOwner);
		//default is 1 times
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		
		verify(ownerRepository).deleteById(any());
	}

	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
		
		Owner emma = service.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, emma.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

}
