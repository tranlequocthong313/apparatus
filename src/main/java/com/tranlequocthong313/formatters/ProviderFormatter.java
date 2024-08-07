/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.Provider;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class ProviderFormatter implements Formatter<Provider> {

    @Override
    public String print(Provider object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Provider parse(String text, Locale locale) throws ParseException {
        Provider o = new Provider();
        o.setId(Integer.parseInt(text));
        return o;
    }

}
