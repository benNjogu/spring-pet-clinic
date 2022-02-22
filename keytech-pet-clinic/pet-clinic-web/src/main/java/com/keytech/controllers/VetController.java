package com.keytech.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keytech.model.Vet;
import com.keytech.services.VetService;

@Controller
public class VetController {
	
	private final VetService vetService;
	
	public VetController(VetService vetService) {
		super();
		this.vetService = vetService;
	}



	@RequestMapping({"/vets", "/vets.html", "/vets/", "/vets/index", "/vets/index.html"})
	public String listVets(Model model) {
		
		model.addAttribute("vets", vetService.findAll());
		
		return "vets/index";
	}
	
	@GetMapping("api/vets")
	public @ResponseBody Set<Vet> getVetsJSON() {
		
		return vetService.findAll();
	}
	
	/**
	 * @The above produces the below:
	 * [{"id":1,"firstName":"Stephen","lastName":"Gathaiya","specialities":[{"id":1,"description":"Radiology","new":false}],"new":false},
	 * {"id":2,"firstName":"LE","lastName":"Kimanga","specialities":[{"id":3,"description":"Dentistry","new":false}],"new":false}]
	 */
	
}
