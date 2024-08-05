package com.tranlequocthong313.controllers;


import com.tranlequocthong313.models.ThreadCategory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
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
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @GetMapping("/")
    @Transactional
    public String index(Locale locale, Model model) {
        String greeting = messageSource.getMessage("greeting", null, locale);
        model.addAttribute("msg", greeting);

        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM ThreadCategory");
        List<ThreadCategory> categories = q.getResultList();

        model.addAttribute("categories", categories);

        return "index";
    }
}
