package com.keytech.Bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.keytech.Services.OwnerService;
import com.keytech.Services.PetTypeService;
import com.keytech.Model.Owner;
import com.keytech.Model.PetType;
import com.keytech.Model.Vet;
import com.keytech.Services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}


	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");
		
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
	
		PetType savedCatPetType = petTypeService.save(cat);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Alice");
		owner1.setLastName("Wairimu");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Dedan");
		owner2.setLastName("Njogu");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners...");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Stephen");
		vet1.setLastName("Gathaiya");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("LE");
		vet2.setLastName("Kimanga");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets...");
	}

}
