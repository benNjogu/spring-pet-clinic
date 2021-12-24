package com.keytech.keytechpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.keytech"})
@EnableJpaRepositories(basePackages = {"com.keytech"})
@EntityScan(basePackages = {"com.keytech"})
public class KeytechPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeytechPetClinicApplication.class, args);
	}

}
