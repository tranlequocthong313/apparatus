/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ForumCategoryDto;
import com.tranlequocthong313.models.ForumCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface ForumCategoryService {

	List<ForumCategoryDto> findAll(Map<String, String> queryParams);

	ForumCategoryDto findById(Integer id);

	ForumCategory update(ForumCategoryDto threadDto);

	void save(ForumCategory thread);

	void delete(int id);

	void update(ForumCategory threadCategory);

	void save(ForumCategory threadCategory, MultipartFile image);

	default Long count() {
		return count(null);
	}

	Long count(Map<String, String> queryParams);

	void update(ForumCategoryDto threadCategory, MultipartFile image);

	default List<ForumCategoryDto> findAll() {
		return findAll(null);
	}

}
