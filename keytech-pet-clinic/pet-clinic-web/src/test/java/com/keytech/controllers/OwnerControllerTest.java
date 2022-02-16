package com.keytech.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
	void testFindOwners() throws Exception {
		
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/findOwners"))
				.andExpect(model().attributeExists("owner"));
		
		verifyNoInteractions(ownerService);
	}
	
	@Test
	void processFindFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(any())).thenReturn(Arrays.asList(
				Owner.builder().id(1l).build(), Owner.builder().id(2l).build()));
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownersList"))
				.andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void processFindFormReturnOne() throws Exception {
		when(ownerService.findAllByLastNameLike(any())).thenReturn(Arrays.asList(Owner.builder().id(1l).build()));
		mockMvc.perform(get("/owners"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
	}
	
	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(any())).thenReturn(Owner.builder().id(1l).build());
		mockMvc.perform(get("/owners/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1l))));
	}

}
