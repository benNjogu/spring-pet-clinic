package com.keytech.formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.keytech.model.PetType;
import com.keytech.services.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType>{

	private final PetTypeService petTypeService;
	
	public PetTypeFormatter(PetTypeService petTypeService) {
		super();
		this.petTypeService = petTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
		Collection<PetType> petTypes = petTypeService.findAll();
		
		for (PetType type : petTypes) {
			if (type .getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("Type not found " + text, 0);
	}

}
