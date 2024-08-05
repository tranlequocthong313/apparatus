/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.ThreadCategoryDto;
import com.tranlequocthong313.exceptions.Error;
import com.tranlequocthong313.exceptions.ThreadCategoryNotFoundException;
import com.tranlequocthong313.models.ThreadCategory;
import com.tranlequocthong313.services.ThreadCategoryService;
import com.tranlequocthong313.services.UserService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author tranlequocthong313
 */
@RestController
@RequestMapping("/api/thread-categories")
public class ApiThreadCategoryController {

	@Autowired
	private ThreadCategoryService threadCategoryService;

	@GetMapping
	public List<ThreadCategoryDto> categories() {
		return threadCategoryService.findAll();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ThreadCategory createCategory(@Valid @RequestBody ThreadCategory category) {
		threadCategoryService.save(category);
		return category;
	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ThreadCategory updateCategory(@Valid @RequestBody ThreadCategory category, @PathVariable(value = "id") int id) {
		ThreadCategoryDto threadCategoryDto = threadCategoryService.findById(id);
		threadCategoryDto.setName(category.getName());
		return threadCategoryService.update(threadCategoryDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable(value = "id") int id) {
		threadCategoryService.delete(id);
	}

	@ExceptionHandler(ThreadCategoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error hibernateError(ThreadCategoryNotFoundException e) {
		return new Error(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
}
