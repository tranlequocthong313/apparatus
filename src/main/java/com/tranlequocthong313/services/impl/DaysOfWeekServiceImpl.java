/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.DaysOfWeekDto;
import com.tranlequocthong313.models.DaysOfWeek;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.DaysOfWeekService;
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
public class DaysOfWeekServiceImpl implements DaysOfWeekService {

	@Autowired
	private BaseRepository<DaysOfWeek, Integer> daysOfWeekRepository;

	@Override
	public List<DaysOfWeekDto> findAll(Map<String, String> queryParams) {
		List<DaysOfWeek> daysOfWeeks = daysOfWeekRepository.findAll(queryParams);
		return daysOfWeeks.stream().map(daysOfWeek -> mapToDaysOfWeekDto(daysOfWeek)).collect(Collectors.toList());
	}

	private DaysOfWeekDto mapToDaysOfWeekDto(DaysOfWeek daysOfWeek) {
		return DaysOfWeekDto.builder()
			.id(daysOfWeek.getId())
			.day(daysOfWeek.getDay())
			.key(daysOfWeek.getKey())
			.build();
	}

	@Override
	public DaysOfWeekDto findById(Integer id) {
		return mapToDaysOfWeekDto(daysOfWeekRepository.getReferenceById(id));
	}

	@Override
	public void delete(int id) {
		daysOfWeekRepository.delete(id);
	}

	@Override
	public void update(DaysOfWeek daysOfWeek) {
		daysOfWeekRepository.save(daysOfWeek);
	}

	@Override
	public void save(DaysOfWeek daysOfWeek, MultipartFile image) {
		daysOfWeekRepository.save(daysOfWeek);
	}

	@Override
	public Long count(Map<String, String> queryParams) {
		return daysOfWeekRepository.count(queryParams);
	}

	@Override
	public void update(DaysOfWeekDto daysOfWeek, MultipartFile image) {
		daysOfWeekRepository.save(mapToDaysOfWeek(daysOfWeek));
	}

	@Override
	public void save(DaysOfWeek daysOfWeek) {
		daysOfWeekRepository.save(daysOfWeek);
	}

	@Override
	public DaysOfWeek update(DaysOfWeekDto daysOfWeekDto) {
		DaysOfWeek daysOfWeek = mapToDaysOfWeek(daysOfWeekDto);
		daysOfWeekRepository.save(daysOfWeek);
		return daysOfWeek;
	}

	@Override
	public DaysOfWeek mapToDaysOfWeek(DaysOfWeekDto daysOfWeekDto) {
		return DaysOfWeek.builder()
			.id(daysOfWeekDto.getId())
			.day(daysOfWeekDto.getDay())
			.key(daysOfWeekDto.getKey())
			.build();
	}
}
