/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.ReplyDto;
import com.tranlequocthong313.models.Reply;
import com.tranlequocthong313.services.ReplyService;
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
@RequestMapping("/api/replies")
@CrossOrigin
public class ApiReplyController {

	@Autowired
	private ReplyService replyService;

	@Autowired
	private UserService userService;

	@GetMapping
	public List<ReplyDto> getReplies(@RequestParam Map<String, String> queryParams) {
		return replyService.findAll(queryParams);
	}

	@GetMapping("/{id}")
	public ReplyDto getReply(@PathVariable(value = "id") Integer id) {
		ReplyDto reply = replyService.findById(id);
		return reply;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Reply createReply(@Valid @RequestBody Reply reply) {
		reply.setUser(userService.getCurrentUser());
		replyService.save(reply);
		return reply;
	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Reply updateReply(@Valid @RequestBody Reply reply, @PathVariable(value = "id") int id) {
		ReplyDto replyDto = replyService.findById(id);
		replyDto.setContent(reply.getContent());
		return replyService.update(replyDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReply(@PathVariable(value = "id") int id) {
		replyService.delete(id);
	}
}
