package com.gft.gerenciadoreventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home.html");
		return mv;
	}
	
	@GetMapping
	public String goToHome() {
		return "redirect://localhost:8080/home";
	}
}
