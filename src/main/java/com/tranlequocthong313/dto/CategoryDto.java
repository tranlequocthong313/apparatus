package com.tranlequocthong313.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tranlequocthong313
 */
@Getter
@Setter
@Builder
@Data
public class CategoryDto {

	public String link;
	public String icon;
	public String title;
	public String divider;

	public CategoryDto(String link, String icon, String title) {
		this.link = link;
		this.icon = icon;
		this.title = title;
	}

	public CategoryDto(String link, String icon, String title, String divider) {
		this.link = link;
		this.icon = icon;
		this.title = title;
		this.divider = divider;
	}
}
