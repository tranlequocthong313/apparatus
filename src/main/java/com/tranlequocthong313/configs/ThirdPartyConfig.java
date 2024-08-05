/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author tranlequocthong313
 */
@Configuration
@PropertySource("classpath:env.properties")
public class ThirdPartyConfig {
	@Autowired
	private Environment env;

	@Bean
	public Cloudinary cloudinary() {
		Cloudinary cloudinary
			= new Cloudinary(ObjectUtils.asMap(
				"cloud_name", env.getProperty("cloudinary.cloud_name"),
				"api_key", env.getProperty("cloudinary.api_key"),
				"api_secret", env.getProperty("cloudinary.api_secret"),
				"secure", true));
		return cloudinary;
	}

}
