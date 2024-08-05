/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.formatters;

import com.tranlequocthong313.models.ThreadCategory;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tranlequocthong313
 */
public class ThreadCategoryFormatter implements Formatter<ThreadCategory>{

	@Override
	public String print(ThreadCategory object, Locale locale) {
		return String.valueOf(object.getId());
	}

	@Override
	public ThreadCategory parse(String text, Locale locale) throws ParseException {
		ThreadCategory category = new ThreadCategory();
		category.setId(Integer.parseInt(text));
		return category;
	}
	
}
