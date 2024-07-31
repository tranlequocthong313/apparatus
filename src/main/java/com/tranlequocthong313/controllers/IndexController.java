package com.tranlequocthong313.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author tranlequocthong313
 */
@Controller
public class IndexController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index(Locale locale, Model model) {
        String greeting = messageSource.getMessage("greeting", null, locale);
        model.addAttribute("msg", greeting);
        return "index";
    }
}
