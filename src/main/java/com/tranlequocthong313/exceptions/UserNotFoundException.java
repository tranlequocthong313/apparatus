/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

/**
 *
 * @author tranlequocthong313
 */
public class UserNotFoundException extends RuntimeException {

	private long userId;

	public UserNotFoundException(long userId) {
		this.userId = userId;
	}

	public long getThreadId() {
		return userId;
	}

	@Override
	public String getMessage() {
		return "User [" + userId + "] not found";
	}

}
