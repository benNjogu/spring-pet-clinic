package com.keytech.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.keytech.model.Speciality;
import com.keytech.model.Vet;
import com.keytech.services.SpecialtyService;
import com.keytech.services.VetService;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService{

	private final SpecialtyService specialtyService;
	
	public VetMapService(SpecialtyService specialtyService) {
		super();
		this.specialtyService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet object) {
		
		if (object.getSpecialities().size()>0) {
			object.getSpecialities().forEach(specialty ->{
				if(specialty.getId() == null) {
					Speciality savedSpeciality = specialtyService.save(specialty);
					specialty.setId(savedSpeciality.getId());
				}
			});
		}
		return super.save(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

	
}
