package com.keytech.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.keytech.model.Owner;
import com.keytech.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
	
	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
		
	}
	
	@GetMapping()
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		//allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName("");//empty string signifies broadest possible search
		}
		
		//find owners by last name
		List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
		
		if (owners.isEmpty()) {
			//owner not found
			result.rejectValue("lastName", "not found", "not found");
			return "owners/findOwners";
		}else if (owners.size()==1) {
			//1 owner found
			owner = owners.get(0);
			return "redirect:/owners/" + owner.getId();
		}else {
			//multiple owners found
			model.addAttribute("selections", owners);
			return "owners/ownersList";
		}
		
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showView(@PathVariable("ownerId") Long ownerId) {
		ModelAndView nav = new ModelAndView("owners/ownerDetails");
		nav.addObject(ownerService.findById(Long.valueOf(ownerId)));
		return nav;
	}
	
}
