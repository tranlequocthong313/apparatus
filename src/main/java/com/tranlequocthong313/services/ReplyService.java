/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ReplyDto;
import com.tranlequocthong313.models.Reply;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tranlequocthong313
 */
public interface ReplyService {

	List<ReplyDto> findAll(Map<String, String> queryParams);

	ReplyDto findById(Integer id);

	void save(Reply reply);

	void delete(Integer id);

	Reply update(ReplyDto replyDto);
}
