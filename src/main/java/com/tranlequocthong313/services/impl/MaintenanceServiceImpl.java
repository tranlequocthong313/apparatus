/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.MaintenanceDto;
import com.tranlequocthong313.models.Maintenance;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.MaintenanceService;
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
public class MaintenanceServiceImpl implements MaintenanceService {

	@Autowired
	private BaseRepository<Maintenance, Integer> maintenanceRepository;

	@Override
	public List<MaintenanceDto> findAll(Map<String, String> queryParams) {
		List<Maintenance> maintenances = maintenanceRepository.findAll(queryParams);
		return maintenances.stream().map(maintenance -> mapToMaintenanceDto(maintenance)).collect(Collectors.toList());
	}

	private MaintenanceDto mapToMaintenanceDto(Maintenance maintenance) {
		return MaintenanceDto.builder()
			.id(maintenance.getId())
			.summary(maintenance.getSummary())
			.description(maintenance.getDescription())
			.image(maintenance.getImage())
			.link(maintenance.getLink())
			.repeatEvery(maintenance.getRepeatEvery())
			.startDate(maintenance.getStartDate())
			.endDate(maintenance.getEndDate())
			.startDateTime(maintenance.getStartDateTime())
			.endDateTime(maintenance.getEndDateTime())
			.allDay(maintenance.getAllDay())
			.endDateRecurrence(maintenance.getEndDateRecurrence())
			.occurrenceCount(maintenance.getOccurrenceCount())
			.type(maintenance.getType())
			.recurrenceType(maintenance.getRecurrenceType())
			.endRecurrenceType(maintenance.getEndRecurrenceType())
			.device(maintenance.getDevice())
			.deviceType(maintenance.getDeviceType())
			.createdAt(maintenance.getCreatedAt())
			.updatedAt(maintenance.getUpdatedAt())
			.build();
	}

	@Override
	public MaintenanceDto findById(Integer id) {
		return mapToMaintenanceDto(maintenanceRepository.getReferenceById(id));
	}

	@Override
	public void delete(int id) {
		maintenanceRepository.delete(id);
	}

	@Override
	public void update(Maintenance maintenance) {
		maintenanceRepository.save(maintenance);
	}

	@Override
	public void save(Maintenance maintenance, MultipartFile image) {
		maintenanceRepository.save(maintenance);
	}

	@Override
	public Long count(Map<String, String> queryParams) {
		return maintenanceRepository.count(queryParams);
	}

	@Override
	public void update(MaintenanceDto maintenance, MultipartFile image) {
		maintenanceRepository.save(mapToMaintenance(maintenance));
	}

	@Override
	public void save(Maintenance maintenance) {
		maintenanceRepository.save(maintenance);
	}

	@Override
	public Maintenance update(MaintenanceDto maintenanceDto) {
		Maintenance maintenance = mapToMaintenance(maintenanceDto);
		maintenanceRepository.save(maintenance);
		return maintenance;
	}

	private Maintenance mapToMaintenance(MaintenanceDto maintenanceDto) {
		return Maintenance.builder()
			.id(maintenanceDto.getId())
			.summary(maintenanceDto.getSummary())
			.description(maintenanceDto.getDescription())
			.image(maintenanceDto.getImage())
			.link(maintenanceDto.getLink())
			.repeatEvery(maintenanceDto.getRepeatEvery())
			.startDate(maintenanceDto.getStartDate())
			.endDate(maintenanceDto.getEndDate())
			.startDateTime(maintenanceDto.getStartDateTime())
			.endDateTime(maintenanceDto.getEndDateTime())
			.allDay(maintenanceDto.getAllDay())
			.endDateRecurrence(maintenanceDto.getEndDateRecurrence())
			.occurrenceCount(maintenanceDto.getOccurrenceCount())
			.type(maintenanceDto.getType())
			.recurrenceType(maintenanceDto.getRecurrenceType())
			.endRecurrenceType(maintenanceDto.getEndRecurrenceType())
			.device(maintenanceDto.getDevice())
			.deviceType(maintenanceDto.getDeviceType())
			.createdAt(maintenanceDto.getCreatedAt())
			.updatedAt(maintenanceDto.getUpdatedAt())
			.build();
	}
}
