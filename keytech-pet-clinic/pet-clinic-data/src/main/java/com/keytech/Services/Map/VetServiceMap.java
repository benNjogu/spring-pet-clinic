package com.keytech.Services.Map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.keytech.Model.Speciality;
import com.keytech.Model.Vet;
import com.keytech.Services.SpecialtyService;
import com.keytech.Services.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService{

	private final SpecialtyService specialtyService;
	
	public VetServiceMap(SpecialtyService specialtyService) {
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
