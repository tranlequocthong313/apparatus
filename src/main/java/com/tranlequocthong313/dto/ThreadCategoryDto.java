/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import java.util.Date;

import com.tranlequocthong313.models.ForumCategory;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author tranlequocthong313
 */
@Data
@Builder
public class ThreadCategoryDto {

	private Integer id;
	private String name;
	private ForumCategory forumCategory;
	private Date createdAt;
	private Date updatedAt;
}
