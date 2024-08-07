/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.DeviceType;
import com.tranlequocthong313.models.ThreadCategory;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * @author tranlequocthong313
 */
public class DeviceTypeFormatter implements Formatter<DeviceType> {

    @Override
    public String print(DeviceType object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public DeviceType parse(String text, Locale locale) throws ParseException {
        DeviceType o = new DeviceType();
        o.setId(Integer.parseInt(text));
        return o;
    }

}
