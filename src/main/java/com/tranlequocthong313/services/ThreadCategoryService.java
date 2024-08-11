/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ThreadCategoryDto;
import com.tranlequocthong313.models.ThreadCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface ThreadCategoryService {

	List<ThreadCategoryDto> findAll(Map<String, String> queryParams);

	ThreadCategoryDto findById(Integer id);

	ThreadCategory update(ThreadCategoryDto threadDto);

	void save(ThreadCategory thread);

	void delete(int id);

	void update(ThreadCategory threadCategory);

	void save(ThreadCategory threadCategory, MultipartFile image);

	default Long count() {
		return count(null);
	}

	Long count(Map<String, String> queryParams);

	void update(ThreadCategoryDto threadCategory, MultipartFile image);

	default List<ThreadCategoryDto> findAll() {
		return findAll(null);
	}

}
