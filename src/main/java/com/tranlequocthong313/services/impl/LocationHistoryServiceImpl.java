/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.LocationHistoryDto;
import com.tranlequocthong313.models.LocationHistory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.LocationHistoryService;
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
public class LocationHistoryServiceImpl implements LocationHistoryService {

    @Autowired
    private BaseRepository<LocationHistory, Integer> locationHistoryRepository;

    @Override
    public List<LocationHistoryDto> findAll(Map<String, String> queryParams) {
        List<LocationHistory> locationHistories = locationHistoryRepository.findAll(queryParams);
        return locationHistories.stream().map(locationHistory -> mapToLocationHistoryDto(locationHistory)).collect(Collectors.toList());
    }

    private LocationHistoryDto mapToLocationHistoryDto(LocationHistory locationHistory) {
        return LocationHistoryDto.builder()
                .id(locationHistory.getId())
                .dateOfMoving(locationHistory.getDateOfMoving())
                .note(locationHistory.getNote())
                .device(locationHistory.getDevice())
                .location(locationHistory.getLocation())
                .locationDetail(locationHistory.getLocationDetail())
                .createdAt(locationHistory.getCreatedAt())
                .updatedAt(locationHistory.getUpdatedAt())
                .build();
    }

    @Override
    public LocationHistoryDto findById(Integer id) {
        return mapToLocationHistoryDto(locationHistoryRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        locationHistoryRepository.delete(id);
    }

    @Override
    public void update(LocationHistory locationHistory) {
        locationHistoryRepository.save(locationHistory);
    }

    @Override
    public void save(LocationHistory locationHistory, MultipartFile image) {
        locationHistoryRepository.save(locationHistory);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return locationHistoryRepository.count(queryParams);
    }

    @Override
    public void update(LocationHistoryDto locationHistory, MultipartFile image) {
        locationHistoryRepository.save(mapToLocationHistory(locationHistory));
    }

    @Override
    public void save(LocationHistory locationHistory) {
        locationHistoryRepository.save(locationHistory);
    }

    @Override
    public LocationHistory update(LocationHistoryDto locationHistoryDto) {
        LocationHistory locationHistory = mapToLocationHistory(locationHistoryDto);
        locationHistoryRepository.save(locationHistory);
        return locationHistory;
    }

    private LocationHistory mapToLocationHistory(LocationHistoryDto locationHistoryDto) {
        return LocationHistory.builder()
                .id(locationHistoryDto.getId())
                .dateOfMoving(locationHistoryDto.getDateOfMoving())
                .note(locationHistoryDto.getNote())
                .device(locationHistoryDto.getDevice())
                .location(locationHistoryDto.getLocation())
                .locationDetail(locationHistoryDto.getLocationDetail())
                .createdAt(locationHistoryDto.getCreatedAt())
                .updatedAt(locationHistoryDto.getUpdatedAt())
                .build();
    }
}
