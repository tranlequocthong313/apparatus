/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.ReplyDto;
import com.tranlequocthong313.models.Reply;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.AuthorizationService;
import com.tranlequocthong313.services.ReplyService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranlequocthong313
 */
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private BaseRepository<Reply, Integer> replyRepository;

	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public List<ReplyDto> findAll(Map<String, String> queryParams) {
		List<Reply> replies = replyRepository.findAll(queryParams);
		return replies.stream().map(reply -> mapToReplyDto(reply)).collect(Collectors.toList());
	}

	private ReplyDto mapToReplyDto(Reply reply) {
		ReplyDto replyDto = ReplyDto.builder()
			.id(reply.getId())
			.content(reply.getContent())
			.createdAt(reply.getCreatedAt())
			.updatedAt(reply.getUpdatedAt())
			.thread(reply.getThread())
			.user(reply.getUser())
			.reply(reply.getReply())
			.build();
		return replyDto;
	}

	@Override
	public ReplyDto findById(Integer id) {
		return mapToReplyDto(replyRepository.getReferenceById(id));
	}

	@Override
	public void save(Reply reply) {
		replyRepository.save(reply);
	}

	@Override
	public void delete(Integer id) {
		Reply reply = replyRepository.getReferenceById(id);
		authorizationService.checkUserPermission(reply.getUser());
		replyRepository.delete(id);
	}

	@Override
	public Reply update(ReplyDto replyDto) {
		Reply reply = mapToReply(replyDto);
		authorizationService.checkUserPermission(reply.getUser());
		replyRepository.save(reply);
		return reply;
	}

	private Reply mapToReply(ReplyDto replyDto) {
		return Reply.builder()
			.id(replyDto.getId())
			.content(replyDto.getContent())
			.createdAt(replyDto.getCreatedAt())
			.updatedAt(replyDto.getUpdatedAt())
			.thread(replyDto.getThread())
			.user(replyDto.getUser())
			.reply(replyDto.getReply())
			.build();
	}

}
