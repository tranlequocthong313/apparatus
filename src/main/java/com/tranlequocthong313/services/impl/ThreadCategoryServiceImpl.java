/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.ThreadCategoryDto;
import com.tranlequocthong313.models.ThreadCategory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.ThreadCategoryService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tranlequocthong313
 */
@Service
public class ThreadCategoryServiceImpl implements ThreadCategoryService {

	@Autowired
	private BaseRepository<ThreadCategory, Integer> threadCategoryRepository;

	@Override
	public List<ThreadCategoryDto> findAll(Map<String, String> queryParams) {
		List<ThreadCategory> threadCategories = threadCategoryRepository.findAll(queryParams);
		return threadCategories.stream().map(threadCategory -> mapToThreadCategoryDto(threadCategory)).collect(Collectors.toList());
	}

	private ThreadCategoryDto mapToThreadCategoryDto(ThreadCategory threadCategory) {
		return ThreadCategoryDto.builder()
			.id(threadCategory.getId())
			.name(threadCategory.getName())
			.forumCategory(threadCategory.getForumCategory())
			.createdAt(threadCategory.getCreatedAt())
			.updatedAt(threadCategory.getUpdatedAt())
			.build();
	}

	@Override
	public ThreadCategoryDto findById(Integer id) {
		return mapToThreadCategoryDto(threadCategoryRepository.getReferenceById(id));
	}

	@Override
	public void delete(int id) {
		threadCategoryRepository.delete(id);
	}

	@Override
	public void update(ThreadCategory threadCategory) {
		threadCategoryRepository.save(threadCategory);
	}

	@Override
	public void save(ThreadCategory threadCategory, MultipartFile image) {
		threadCategoryRepository.save(threadCategory);
	}

	@Override
	public Long count(Map<String, String> queryParams) {
		return threadCategoryRepository.count(queryParams);
	}

	@Override
	public void update(ThreadCategoryDto threadCategory, MultipartFile image) {
		threadCategoryRepository.save(mapToThreadCategory(threadCategory));
	}

	@Override
	public void save(ThreadCategory threadCategory) {
		threadCategoryRepository.save(threadCategory);
	}

	@Override
	public ThreadCategory update(ThreadCategoryDto threadCategoryDto) {
		ThreadCategory threadCategory = mapToThreadCategory(threadCategoryDto);
		threadCategoryRepository.save(threadCategory);
		return threadCategory;
	}

	private ThreadCategory mapToThreadCategory(ThreadCategoryDto threadCategoryDto) {
		return ThreadCategory.builder()
			.id(threadCategoryDto.getId())
			.name(threadCategoryDto.getName())
			.forumCategory(threadCategoryDto.getForumCategory())
			.createdAt(threadCategoryDto.getCreatedAt())
			.updatedAt(threadCategoryDto.getUpdatedAt())
			.build();
	}
}
