package com.keytech.services;


import java.util.List;

import com.keytech.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByLastNameLike(String lastName);
}
