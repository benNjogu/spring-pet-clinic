package com.keytech.controllers;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keytech.model.Pet;
import com.keytech.model.Visit;
import com.keytech.services.PetService;
import com.keytech.services.VisitService;

@Controller
public class VisitController {

	private final VisitService visitService;
	private final PetService petService;

	public VisitController(VisitService visitService, PetService petService) {
		super();
		this.visitService = visitService;
		this.petService = petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		
		dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text));
			}
		});
	}
	/**
	 * Called before each and every @RequestMapping annotated method
	 * 2 goals:
	 * -Make sure we always have fresh data.
	 * -Since we do not use the session scope, make sure that the pet object always has an id
	 * (Even though id is not part of the form fields)
	 * 
	 * @param petId
	 * @return Pet
	 * 
	 */
	
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
		Pet pet = petService.findById(petId);
		model.addAttribute("pet", pet);
		Visit visit = new Visit();
		pet.getVisits().add(visit);
		visit.setPet(pet);
		return visit;
	}
	
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitorForm(@PathVariable Long petId, Model model) {
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Validated Visit visit, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}else {
			visitService.save(visit);
			return "redirect:/owners/{ownerId}";
		}
	}
	
}
