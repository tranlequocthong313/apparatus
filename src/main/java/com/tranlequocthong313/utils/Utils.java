/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.utils;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author tranlequocthong313
 */
@PropertySource("classpath:configs.properties")
public class Utils {

    @Autowired
    private static Environment env;
    private static String PAGE_SIZE_PROPERTY_NAME = "common.pageSize";

    public static void pagniate(Query query, String page, String pageSizePropertyName) {
        if (page != null && !page.isEmpty()) {
            int pageSize = env.getProperty(pageSizePropertyName, Integer.class);
            int start = (Integer.parseInt(page) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
    }

    public static void pagniate(Query query, String page) {
        if (page != null && !page.isEmpty()) {
            int pageSize = env.getProperty(PAGE_SIZE_PROPERTY_NAME, Integer.class);
            int start = (Integer.parseInt(page) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
    }
}
