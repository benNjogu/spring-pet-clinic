package com.keytech.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listVets() {
		
		return "Vets/index";
	}
	
}
