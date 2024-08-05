/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

/**
 *
 * @author tranlequocthong313
 */
import com.tranlequocthong313.exceptions.DuplicateEntryException;
import com.tranlequocthong313.exceptions.EntityNotFoundException;
import com.tranlequocthong313.exceptions.Error;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder errorMessage = new StringBuilder("Validation failed for: ");
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorMessage.append(fieldName).append(" (").append(message).append(") ");
		});
		return new Error(HttpStatus.BAD_REQUEST.value(), errorMessage.toString());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleDuplicateEntry(EntityNotFoundException ex) {
		return new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(DuplicateEntryException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error handleDuplicateEntry(DuplicateEntryException ex) {
		return new Error(HttpStatus.CONFLICT.value(), ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Error serverError(RuntimeException e) {
		System.out.println(e);
		return new Error(500, "Internal server error");
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleNotFound(NoHandlerFoundException ex) {
		return new Error(HttpStatus.NOT_FOUND.value(), "Resource not found");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Error handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
		return new Error(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not allowed");
	}
}
