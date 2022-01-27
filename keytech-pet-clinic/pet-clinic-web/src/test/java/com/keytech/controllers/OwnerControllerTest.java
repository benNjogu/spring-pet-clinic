package com.keytech.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.keytech.model.Owner;
import com.keytech.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;
	
	@InjectMocks
	OwnerController ownerController;
	
	Set<Owner>	owners;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		
		mockMvc = MockMvcBuilders
				.standaloneSetup(ownerController)
				.build();
	}

	@Test
	void testListOwners() throws Exception {
		
		when(ownerService.findAll()).thenReturn(owners);
		mockMvc.perform(get("/owners"))
				.andExpect(status().is(200));
	}

	@Test
	void testFindOwners() {
		fail("Not yet implemented");
	}

}
