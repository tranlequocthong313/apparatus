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

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tranlequocthong313
 */
// TODO: Implement User xong quay lai day lam tiep
@RestController
@RequestMapping("/api/threads")
public class ApiThreadController {

	@Autowired
	private ThreadService threadService;

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
