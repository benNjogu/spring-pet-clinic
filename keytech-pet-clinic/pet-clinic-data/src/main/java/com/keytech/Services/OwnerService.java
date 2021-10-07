package com.keytech.Services;


import com.keytech.Model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

	Owner findByLastName(String lastName);
	
}
