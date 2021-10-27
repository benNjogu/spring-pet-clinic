package com.keytech.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.keytech.model.Owner;
import com.keytech.model.Pet;
import com.keytech.model.PetType;
import com.keytech.model.Speciality;
import com.keytech.model.Vet;
import com.keytech.services.OwnerService;
import com.keytech.services.PetTypeService;
import com.keytech.services.SpecialtyService;
import com.keytech.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}


	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		if (count == 0) {
			loadData();
		}
		
	}


	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialtyService.save(radiology);
		
		Speciality surgery = new Speciality();
		radiology.setDescription("Surgery");
		Speciality savedSurgery = specialtyService.save(surgery);
		
		Speciality dentistry = new Speciality();
		radiology.setDescription("Dentistry");
		Speciality savedDentistry = specialtyService.save(dentistry);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Alice");
		owner1.setLastName("Wairimu");
		owner1.setAdderess("kihingo001");
		owner1.setCity("Murang'a");
		owner1.setTelephone("+2547 345 678");
		
		Pet alicePet = new Pet();
		alicePet.setPetType(savedDogPetType);
		alicePet.setOwner(owner1);
		alicePet.setName("kadogo");
		alicePet.setBirthDate(LocalDate.now());
		
		owner1.getPets().add(alicePet);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Dedan");
		owner2.setLastName("Njogu");
		owner2.setAdderess("kihingo001254");
		owner2.setCity("Murang'a");
		owner2.setTelephone("+2547 879 678");
		
		Pet njoguPet = new Pet();
		njoguPet.setPetType(savedCatPetType);
		njoguPet.setOwner(owner2);
		njoguPet.setName("kanyau");
		njoguPet.setBirthDate(LocalDate.now());
		
		owner2.getPets().add(njoguPet);
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners...");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Stephen");
		vet1.setLastName("Gathaiya");
		vet1.getSpecialities().add(savedRadiology);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("LE");
		vet2.setLastName("Kimanga");
		vet2.getSpecialities().add(savedDentistry);
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets...");
	}

}
