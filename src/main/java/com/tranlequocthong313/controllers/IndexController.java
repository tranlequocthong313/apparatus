package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.CategoryDto;

import java.security.Principal;
import java.util.Arrays;

import com.tranlequocthong313.models.User;
import com.tranlequocthong313.services.DeviceService;
import com.tranlequocthong313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

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
                new CategoryDto("/issues", "bug_report", "issue"),
                new CategoryDto("/thread-categories", "comment", "threadCategory", "other"),
                new CategoryDto("/users", "people", "user"),
                new CategoryDto("/users/logout", "logout", "logout", "account")
        ));
        model.addAttribute("currentPath", request.getRequestURI().replace("/apparatus/", ""));
        model.addAttribute("currentUser", userService.getCurrentUser());
    }

    @GetMapping("/")
    public String index(Locale locale, Model model) {
        model.addAttribute("totalDevices", deviceService.count());
        model.addAttribute("totalIssues", 22);
        model.addAttribute("totalRepairCost", 15200000);
        model.addAttribute("totalUsers", userService.count());
        return "index";
    }
}
