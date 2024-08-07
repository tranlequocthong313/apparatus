/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.utils;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author tranlequocthong313
 */
@Component
@PropertySource("classpath:configs.properties")
public class Utils {
    @Autowired
    private Environment env;
    private String PAGE_SIZE_PROPERTY_NAME = "common.pageSize";
    public final int DEFAULT_PAGE_SIZE = 10;

    public void pagniate(Query query, int page) {
        pagniate(query, page, null);
    }

    public void pagniate(Query query, int page, String pageSizePropertyName) {
        int pageSize = env.getProperty(pageSizePropertyName == null ? PAGE_SIZE_PROPERTY_NAME : pageSizePropertyName, Integer.class, DEFAULT_PAGE_SIZE);
        pagniate(query, page, pageSize);
    }

    private void pagniate(Query query, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
    }
}
