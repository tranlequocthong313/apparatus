/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.DeviceTypeDto;
import com.tranlequocthong313.models.DeviceType;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.AuthorizationService;
import com.tranlequocthong313.services.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author tranlequocthong313
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

	@Autowired
	private BaseRepository<DeviceType, Integer> deviceTypeRepository;

	@Override
	public List<DeviceTypeDto> findAll(Map<String, String> queryParams) {
		List<DeviceType> deviceTypes = deviceTypeRepository.findAll(queryParams);
		return deviceTypes.stream().map(deviceType -> mapToDeviceTypeDto(deviceType)).collect(Collectors.toList());
	}

	private DeviceTypeDto mapToDeviceTypeDto(DeviceType deviceType) {
		return DeviceTypeDto.builder()
			.id(deviceType.getId())
			.name(deviceType.getName())
			.createdAt(deviceType.getCreatedAt())
			.updatedAt(deviceType.getUpdatedAt())
			.build();
	}

	@Override
	public DeviceTypeDto findById(Integer id) {
		return mapToDeviceTypeDto(deviceTypeRepository.getReferenceById(id));
	}

	@Override
	public void delete(int id) {
		deviceTypeRepository.delete(id);
	}

	@Override
	public void save(DeviceType deviceType) {
		deviceTypeRepository.save(deviceType);
	}

	@Override
	public DeviceType update(DeviceTypeDto deviceTypeDto) {
		DeviceType deviceType = mapToDeviceType(deviceTypeDto);
		deviceTypeRepository.save(deviceType);
		return deviceType;
	}

	private DeviceType mapToDeviceType(DeviceTypeDto deviceTypeDto) {
		return DeviceType.builder()
			.id(deviceTypeDto.getId())
			.name(deviceTypeDto.getName())
			.createdAt(deviceTypeDto.getCreatedAt())
			.updatedAt(deviceTypeDto.getUpdatedAt())
			.build();
	}
}
