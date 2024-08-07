/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.DeviceType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class DeviceFormatter implements Formatter<Device> {

    @Override
    public String print(Device object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Device parse(String text, Locale locale) throws ParseException {
        Device o = new Device();
        o.setId(text);
        return o;
    }

}
