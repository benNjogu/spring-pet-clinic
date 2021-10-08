package com.keytech.keytechpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.keytech.Controllers")
public class KeytechPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeytechPetClinicApplication.class, args);
	}

}
