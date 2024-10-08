package com.tranlequocthong313.controllers;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.tranlequocthong313.dto.CategoryDto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Arrays;

import com.tranlequocthong313.services.*;
import com.tranlequocthong313.services.impl.GoogleCalendarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
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
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private UserService userService;
	@Autowired
	private IssueService issueService;
	@Autowired
	private RepairService repairService;
	@Autowired
	private StatsService statsService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private MaintenanceService maintenanceService;

	@ModelAttribute
	public void commAttrs(Model model, HttpServletRequest request, Principal principal) {
		model.addAttribute("categories", Arrays.asList(
			new CategoryDto("/", "dashboard", "dashboard"),
			new CategoryDto("/devices", "storage", "device", "device"),
			new CategoryDto("/device-categories", "category", "deviceCategory"),
			new CategoryDto("/locations", "place", "location"),
			new CategoryDto("/location-details", "corporate_fare", "locationDetail"),
			new CategoryDto("/location-histories", "history", "locationHistory"),
			new CategoryDto("/providers", "factory", "provider"),
			new CategoryDto("/maintenances", "engineering", "maintenance", "maintenance"),
			new CategoryDto("/maintenances/schedule", "event", "maintenanceSchedule"),
			new CategoryDto("/issues", "bug_report", "issue"),
			new CategoryDto("/repairs", "home_repair_service", "repair"),
			new CategoryDto("/thread-categories", "comment", "threadCategory", "other"),
			new CategoryDto("/users", "people", "user"),
			new CategoryDto("/users/logout", "logout", "logout", "account")
		));
		model.addAttribute("currentPath", request.getRequestURI().replace("/apparatus/", ""));
		model.addAttribute("currentUser", userService.getCurrentUser());
	}

	@GetMapping("/")
	public String index(Locale locale, Model model) throws GeneralSecurityException, IOException {
		model.addAttribute("totalDevices", deviceService.count());
		model.addAttribute("totalIssues", issueService.findByDone(false).size());
		model.addAttribute("totalRepairCost", repairService.totalCost());
		model.addAttribute("totalUsers", userService.count());

		model.addAttribute("issuePerMonth", statsService.issuePerPeriod());
		model.addAttribute("repairCostPerMonth", statsService.repairCostsPerPeriod());
		model.addAttribute("deviceStatuses", statsService.deviceStatuses());

		model.addAttribute("activityLogs", activityLogService.findAll());
		model.addAttribute("recentMaintenances", maintenanceService.findAll());

		return "index";
	}
}
