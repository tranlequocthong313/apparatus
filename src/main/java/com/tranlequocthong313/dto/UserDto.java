/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.User.UserRole;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author tranlequocthong313
 */
@Data
@Builder
public class UserDto {

	private Integer id;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String username;
	private boolean active;
	private UserRole userRole;
	private Date createdAt;
	private Date updatedAt;
	private String note;
}
