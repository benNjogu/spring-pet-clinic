package com.keytech.Bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.keytech.Services.OwnerService;
import com.keytech.Model.Owner;
import com.keytech.Model.Vet;
import com.keytech.Services.VetService;
import com.keytech.Services.Map.OwnerServiceMap;
import com.keytech.Services.Map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	
	public DataLoader() {
		super();
		
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}

	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Alice");
		owner1.setLastName("Wairimu");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Dedan");
		owner2.setLastName("Njogu");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners...");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Stephen");
		vet1.setLastName("Gathaiya");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("L");
		vet2.setLastName("E");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets...");
	}

}
