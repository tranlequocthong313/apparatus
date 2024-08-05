/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

import com.tranlequocthong313.models.User;

/**
 *
 * @author tranlequocthong313
 */
public class UserDuplicatedEntryException extends RuntimeException {

	private User user;

	public UserDuplicatedEntryException(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
