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
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranlequocthong313
 */
@Service
public class ThreadCategoryServiceImpl implements ThreadCategoryService {

	@Autowired
	private BaseRepository<ThreadCategory, Integer> threadCategoryRepository;

	@Override
	public List<ThreadCategoryDto> findAll() {
		List<ThreadCategory> threadCategories = threadCategoryRepository.findAll();
		return threadCategories.stream().map(threadCategory -> mapToThreadCategoryDto(threadCategory)).collect(Collectors.toList());
	}

	private ThreadCategoryDto mapToThreadCategoryDto(ThreadCategory threadCategory) {
		ThreadCategoryDto threadCategoryDto = ThreadCategoryDto.builder()
			.id(threadCategory.getId())
			.name(threadCategory.getName())
			.createdAt(threadCategory.getCreatedAt())
			.updatedAt(threadCategory.getUpdatedAt())
			.build();
		return threadCategoryDto;
	}

	@Override
	public void save(ThreadCategory category) {
		threadCategoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		threadCategoryRepository.delete(id);
	}

	@Override
	public ThreadCategoryDto findById(Integer id) {
		return mapToThreadCategoryDto(threadCategoryRepository.getReferenceById(id));
	}

	@Override
	public ThreadCategory update(ThreadCategoryDto threadCategoryDto) {
		ThreadCategory threadCategory = mapToThreadCategory(threadCategoryDto);
		threadCategoryRepository.save(threadCategory);
		return threadCategory;
	}

	private ThreadCategory mapToThreadCategory(ThreadCategoryDto threadCategoryDto) {
		ThreadCategory threadCategory = ThreadCategory.builder()
			.id(threadCategoryDto.getId())
			.name(threadCategoryDto.getName())
			.createdAt(threadCategoryDto.getCreatedAt())
			.updatedAt(threadCategoryDto.getUpdatedAt())
			.build();
		return threadCategory;
	}
}
