/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author tranlequocthong313
 */
@Data
@Builder
public class ForumCategoryDto {

	private Integer id;
	private String name;
}
