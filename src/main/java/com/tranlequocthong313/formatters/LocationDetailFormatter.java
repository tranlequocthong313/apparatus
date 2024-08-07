/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.LocationDetail;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class LocationDetailFormatter implements Formatter<LocationDetail> {

    @Override
    public String print(LocationDetail object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public LocationDetail parse(String text, Locale locale) throws ParseException {
        LocationDetail o = new LocationDetail();
        o.setId(Integer.parseInt(text));
        return o;
    }

}
