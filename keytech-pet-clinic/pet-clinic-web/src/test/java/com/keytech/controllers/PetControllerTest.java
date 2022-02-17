package com.keytech.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Matchers.any;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.keytech.model.Owner;
import com.keytech.model.Pet;
import com.keytech.model.PetType;
import com.keytech.services.OwnerService;
import com.keytech.services.PetService;
import com.keytech.services.PetTypeService;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

	@Mock
	PetService petService;
	
	@Mock
	OwnerService ownerService;
	
	@Mock
	PetTypeService petTypeService;
	
	@InjectMocks
	PetController petController;
	
	MockMvc mockMvc;
	
	Owner owner;
	
	Set<PetType> petTypes;
	
	@BeforeEach
	void setUp() throws Exception {
		
		owner = Owner.builder().id(1l).build();
		petTypes = new HashSet<>();
		petTypes.add(PetType.builder().id(1l).name("Dog").build());
		petTypes.add(PetType.builder().id(2l).name("Cat").build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
		
	}

	@Test
	void initCreationForm() throws Exception {
		when(ownerService.findById(any())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(get("/owners/1/pets/new"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	@Test
	void processCreationForm() throws Exception {
		when(ownerService.findById(any())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(post("/owners/1/pets/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(view().name("redirect:/owners/1"));
		
		verify(petService).save(any());
	}
	
	@Test
	void initUpdateForm() throws Exception {
		when(ownerService.findById(any())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		when(petService.findById(any())).thenReturn(Pet.builder().id(2l).build());
		
		mockMvc.perform(get("/owners/1/pets/2/edit"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	@Test
	void processUpdateForm() throws Exception {
		when(ownerService.findById(any())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(post("/owners/1/pets/2/edit"))
			 	.andExpect(status().is3xxRedirection())
			 	.andExpect(model().attributeExists("owner"))
			 	.andExpect(model().attributeExists("pet"))
			 	.andExpect(view().name("redirect:/owners/1"));
		
		verify(petService).save(any());
	}

}
