/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.ThreadDto;
import com.tranlequocthong313.models.Thread;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.AuthorizationService;
import com.tranlequocthong313.services.ThreadService;
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
public class ThreadServiceImpl implements ThreadService {

	@Autowired
	private BaseRepository<Thread, Integer> threadRepository;
	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public List<ThreadDto> findAll(Map<String, String> queryParams) {
		List<Thread> threads = threadRepository.findAll(queryParams);
		return threads.stream().map(thread -> mapToThreadDto(thread)).collect(Collectors.toList());
	}

	private ThreadDto mapToThreadDto(Thread thread) {
		return ThreadDto.builder()
			.id(thread.getId())
			.title(thread.getTitle())
			.content(thread.getContent())
			.createdAt(thread.getCreatedAt())
			.updatedAt(thread.getUpdatedAt())
			.threadCategory(thread.getThreadCategory())
			.user(thread.getUser())
			.build();
	}

	@Override
	public ThreadDto findById(Integer id) {
		return mapToThreadDto(threadRepository.getReferenceById(id));
	}

	@Override
	public void delete(int id) {
		Thread thread = threadRepository.getReferenceById(id);
		authorizationService.checkUserPermission(thread.getUser());
		threadRepository.delete(id);
	}

	@Override
	public void save(Thread thread) {
		threadRepository.save(thread);
	}

	@Override
	public Thread update(ThreadDto threadDto) {
		Thread thread = mapToThread(threadDto);
		authorizationService.checkUserPermission(thread.getUser());
		threadRepository.save(thread);
		return thread;
	}

	private Thread mapToThread(ThreadDto threadDto) {
		return Thread.builder()
			.id(threadDto.getId())
			.title(threadDto.getTitle())
			.content(threadDto.getContent())
			.createdAt(threadDto.getCreatedAt())
			.updatedAt(threadDto.getUpdatedAt())
			.threadCategory(threadDto.getThreadCategory())
			.user(threadDto.getUser())
			.build();
	}
}
