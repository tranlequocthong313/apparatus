/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.DaysOfWeek;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class DaysOfWeekFormatter implements Formatter<DaysOfWeek> {

    @Override
    public String print(DaysOfWeek object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public DaysOfWeek parse(String text, Locale locale) throws ParseException {
        DaysOfWeek o = new DaysOfWeek();
        o.setId(Integer.parseInt(text));
        return o;
    }

}
