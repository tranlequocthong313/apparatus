/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ThreadDto;
import com.tranlequocthong313.models.Thread;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tranlequocthong313
 */
public interface ThreadService {

	List<ThreadDto> findAll(Map<String, String> queryParams);

	ThreadDto findById(Integer id);

	Thread update(ThreadDto threadDto);

	void save(Thread thread);

	void delete(int id);
}
