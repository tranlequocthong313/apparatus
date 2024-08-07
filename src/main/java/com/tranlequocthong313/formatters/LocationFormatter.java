/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.Location;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class LocationFormatter implements Formatter<Location> {

    @Override
    public String print(Location object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Location parse(String text, Locale locale) throws ParseException {
        Location o = new Location();
        o.setId(Integer.parseInt(text));
        return o;
    }

}
