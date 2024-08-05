/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

/**
 *
 * @author tranlequocthong313
 */
public class EntityNotFoundException extends RuntimeException {

	private final String id;

	public EntityNotFoundException(String id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		return "Entity [" + id + "] not found";
	}

}
