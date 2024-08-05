/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ThreadCategoryDto;
import com.tranlequocthong313.models.ThreadCategory;
import java.util.List;

/**
 *
 * @author tranlequocthong313
 */
public interface ThreadCategoryService {

	List<ThreadCategoryDto> findAll();

	ThreadCategoryDto findById(Integer id);

	void save(ThreadCategory category);

	void delete(Integer id);

	public ThreadCategory update(ThreadCategoryDto threadCategoryDto);
}
