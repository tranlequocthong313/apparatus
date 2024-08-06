package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.CategoryDto;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * @author tranlequocthong313
 */
@Controller
@ControllerAdvice
public class IndexController {

	@ModelAttribute
	public void commAttrs(Model model, HttpServletRequest request) {
		model.addAttribute("categories", Arrays.asList(
			new CategoryDto("/", "dashboard", "Dashboard"),
			new CategoryDto("/devices", "storage", "Device"),
			new CategoryDto("/maintenances", "engineering", "Maintenance"),
			new CategoryDto("/issues", "bug_report", "Issue"),
			new CategoryDto("/locations", "place", "Location"),
			new CategoryDto("/providers", "factory", "Provider"),
			new CategoryDto("/users", "people", "User"),
			new CategoryDto("/users/logout", "logout", "Logout", "Account")
		));
		model.addAttribute("currentPath", request.getRequestURI().replace("/apparatus/", ""));
	}

	@GetMapping("/")
	public String index(Locale locale, Model model) {
		return "index";
	}

	@GetMapping("/devices")
	public String devices(Locale locale, Model model) {
		return "index";
	}

	@GetMapping("/maintenances")
	public String maintenances(Locale locale, Model model) {
		return "index";
	}

	@GetMapping("/providers")
	public String providers(Locale locale, Model model) {
		return "index";
	}

	@GetMapping("/locations")
	public String locations(Locale locale, Model model) {
		return "index";
	}

	@GetMapping("/issues")
	public String issues(Locale locale, Model model) {
		return "index";
	}

	@GetMapping("/users")
	public String users(Locale locale, Model model) {
		return "index";
	}

}
