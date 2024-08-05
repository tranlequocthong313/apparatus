/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

/**
 *
 * @author tranlequocthong313
 */
public class DuplicateEntryException extends RuntimeException {

	public DuplicateEntryException(String message) {
		super(message);
	}

	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
	}
}
