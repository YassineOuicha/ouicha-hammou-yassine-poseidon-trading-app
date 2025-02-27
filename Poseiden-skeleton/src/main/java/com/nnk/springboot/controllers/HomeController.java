package com.nnk.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = ( UserDetails) authentication.getPrincipal();
		model.addAttribute("username", userDetails.getUsername() );
		return "home";
	}

	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = ( UserDetails) authentication.getPrincipal();
		model.addAttribute("username", userDetails.getUsername() );
		return "redirect:/bidList/list";
	}
}

