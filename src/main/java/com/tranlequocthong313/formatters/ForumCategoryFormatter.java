/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.ForumCategory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author tranlequocthong313
 */
public class ForumCategoryFormatter implements Formatter<ForumCategory> {

	@Override
	public String print(ForumCategory object, Locale locale) {
		return String.valueOf(object.getId());
	}

	@Override
	public ForumCategory parse(String text, Locale locale) throws ParseException {
		ForumCategory o = new ForumCategory();
		o.setId(Integer.parseInt(text));
		return o;
	}

}
