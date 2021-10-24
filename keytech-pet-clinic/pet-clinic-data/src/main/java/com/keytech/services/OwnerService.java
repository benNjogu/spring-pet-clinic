package com.keytech.services;


import com.keytech.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

	Owner findByLastName(String lastName);
	
}
