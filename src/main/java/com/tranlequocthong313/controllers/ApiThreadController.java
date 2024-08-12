/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.ThreadDto;
import com.tranlequocthong313.exceptions.Error;
import com.tranlequocthong313.models.Thread;
import com.tranlequocthong313.exceptions.ThreadNotFoundException;
import com.tranlequocthong313.services.ThreadService;
import com.tranlequocthong313.services.UserService;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author tranlequocthong313
 */
@RestController
@RequestMapping("/api/threads")
@CrossOrigin
public class ApiThreadController {

	@Autowired
	private ThreadService threadService;

	@Autowired
	private UserService userService;

	@GetMapping
	public List<ThreadDto> getThreads(@RequestParam Map<String, String> queryParams) {
		return threadService.findAll(queryParams);
	}

	@GetMapping("/{id}")
	public ThreadDto getThread(@PathVariable(value = "id") Integer id) {
		ThreadDto thread = threadService.findById(id);
		return thread;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Thread createThread(@Valid @RequestBody Thread thread) {
		thread.setUser(userService.getCurrentUser());
		threadService.save(thread);
		return thread;
	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Thread updateThread(@Valid @RequestBody Thread thread, @PathVariable(value = "id") int id) {
		ThreadDto threadDto = threadService.findById(id);
		threadDto.setTitle(thread.getTitle());
		threadDto.setContent(thread.getContent());
		return threadService.update(threadDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteThread(@PathVariable(value = "id") int id) {
		threadService.delete(id);
	}

	@ExceptionHandler(ThreadNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error threadNotFound(ThreadNotFoundException e) {
		return new Error(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
}
