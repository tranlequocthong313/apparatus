/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.exceptions.UnauthorizedException;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.services.AuthorizationService;
import com.tranlequocthong313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranlequocthong313
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	private UserService userService;

	public void checkUserPermission(User resourceOwner) {
		User currentUser = userService.getCurrentUser();

		if (!currentUser.getUserRole().equals(User.UserRole.ROLE_ADMIN) && !resourceOwner.equals(currentUser)) {
			throw new UnauthorizedException("You are not authorized to perform this action");
		}
	}
}
