/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.ThreadCategory;
import com.tranlequocthong313.models.User;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author tranlequocthong313
 */
@Data
@Builder
public class ThreadDto {

	private Integer id;
	private String title;
	private String content;
	private Date createdAt;
	private Date updatedAt;
	private ThreadCategory threadCategory;
	private User user;
}
