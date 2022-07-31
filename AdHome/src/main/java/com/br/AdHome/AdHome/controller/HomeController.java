package com.br.AdHome.AdHome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("nome", "Alan Soares");
		return "index";
	}
	@GetMapping("/login")
	public ModelAndView exibirLoguin() {
		var mv = new ModelAndView("/login");
		return mv;
	}
}
