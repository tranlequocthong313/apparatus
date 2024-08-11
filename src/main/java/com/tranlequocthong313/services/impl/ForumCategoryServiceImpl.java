/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.ForumCategoryDto;
import com.tranlequocthong313.models.ForumCategory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.ForumCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tranlequocthong313
 */
@Service
public class ForumCategoryServiceImpl implements ForumCategoryService {

	@Autowired
	private BaseRepository<ForumCategory, Integer> threadCategoryRepository;

	@Override
	public List<ForumCategoryDto> findAll(Map<String, String> queryParams) {
		List<ForumCategory> threadCategories = threadCategoryRepository.findAll(queryParams);
		return threadCategories.stream().map(threadCategory -> mapToForumCategoryDto(threadCategory)).collect(Collectors.toList());
	}

	private ForumCategoryDto mapToForumCategoryDto(ForumCategory threadCategory) {
		return ForumCategoryDto.builder()
			.id(threadCategory.getId())
			.name(threadCategory.getName())
			.build();
	}

	@Override
	public ForumCategoryDto findById(Integer id) {
		return mapToForumCategoryDto(threadCategoryRepository.getReferenceById(id));
	}

	@Override
	public void delete(int id) {
		threadCategoryRepository.delete(id);
	}

	@Override
	public void update(ForumCategory threadCategory) {
		threadCategoryRepository.save(threadCategory);
	}

	@Override
	public void save(ForumCategory threadCategory, MultipartFile image) {
		threadCategoryRepository.save(threadCategory);
	}

	@Override
	public Long count(Map<String, String> queryParams) {
		return threadCategoryRepository.count(queryParams);
	}

	@Override
	public void update(ForumCategoryDto threadCategory, MultipartFile image) {
		threadCategoryRepository.save(mapToForumCategory(threadCategory));
	}

	@Override
	public void save(ForumCategory threadCategory) {
		threadCategoryRepository.save(threadCategory);
	}

	@Override
	public ForumCategory update(ForumCategoryDto threadCategoryDto) {
		ForumCategory threadCategory = mapToForumCategory(threadCategoryDto);
		threadCategoryRepository.save(threadCategory);
		return threadCategory;
	}

	private ForumCategory mapToForumCategory(ForumCategoryDto threadCategoryDto) {
		return ForumCategory.builder()
			.id(threadCategoryDto.getId())
			.name(threadCategoryDto.getName())
			.build();
	}
}
