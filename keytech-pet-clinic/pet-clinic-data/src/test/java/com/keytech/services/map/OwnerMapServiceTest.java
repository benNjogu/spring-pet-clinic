package com.keytech.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.keytech.model.Owner;
import com.keytech.model.PetType;
import com.keytech.services.PetTypeService;

import lombok.Builder;

class OwnerMapServiceTest {

	OwnerMapService ownerMapService;
	final Long ownerId = 1L;
	final String lastName = "emma";
	
	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(1L, ownerId);
	}

	@Test
	void testSaveOwnerExistingId() {
		long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(id, savedOwner.getId());
	}
	
	@Test
	void testSaveOwnerNoId() {
		Owner savedOwner = ownerMapService.save(Owner.builder().build());
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = ownerMapService.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testDeleteByIdLong() {
		ownerMapService.deleteById(ownerId);
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testFindByLastName() {
		Owner emma = ownerMapService.findByLastName(lastName);
		assertNotNull(emma);
		assertEquals(ownerId, emma.getId());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		Owner emma = ownerMapService.findByLastName("ella");
		assertNull(emma);
	}

}
