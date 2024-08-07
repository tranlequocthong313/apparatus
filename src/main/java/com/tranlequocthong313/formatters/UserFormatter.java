/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class UserFormatter implements Formatter<User> {

    @Override
    public String print(User object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        User o = new User();
        o.setId(Integer.parseInt(text));
        return o;
    }

}
