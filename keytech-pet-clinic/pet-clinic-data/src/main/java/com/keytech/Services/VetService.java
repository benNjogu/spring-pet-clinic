package com.keytech.Services;

import java.util.Set;
 
import com.keytech.Model.Vet;

public interface VetService {

	Vet findById(Long id);

	Vet save(Vet vet);

	Set<Vet> findAll();

}
