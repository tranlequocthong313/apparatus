/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author tranlequocthong313
 */
@Controller
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    
    @GetMapping("/devices")
    public String devices(Model model) {
        model.addAttribute("devices", deviceService.findAll());
        
        return "devices";
    }
}
