/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.utils;

import com.tranlequocthong313.models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author tranlequocthong313
 */
@Component
public class EnumUtils {
    @Autowired
    private MessageSource messageSource;

    public String getStatusLabel(Device.Status status, Locale locale) {
        return messageSource.getMessage("status." + status.name().toLowerCase(), null, locale);
    }
}
