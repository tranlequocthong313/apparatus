/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.utils;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.tranlequocthong313.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public <T> List<Order> getOrdersBy(Map<String, String> queryParams, CriteriaBuilder builder, Root<T> root) {
        List<Order> orders = new ArrayList<Order>();
        if (queryParams != null && queryParams.containsKey("sort")) {
            String direction = queryParams.getOrDefault("direction", "asc");
            if (Objects.equals(direction, "desc")) {
                orders.add(builder.desc(root.get(queryParams.get("sort"))));
            } else {
                orders.add(builder.asc(root.get(queryParams.get("sort"))));
            }
        }
        return orders;
    }
}
