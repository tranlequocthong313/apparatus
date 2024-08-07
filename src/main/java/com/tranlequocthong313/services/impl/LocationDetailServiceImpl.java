/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.LocationDetailDto;
import com.tranlequocthong313.models.LocationDetail;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.LocationDetailService;
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
public class LocationDetailServiceImpl implements LocationDetailService {

    @Autowired
    private BaseRepository<LocationDetail, Integer> locationDetailRepository;

    @Override
    public List<LocationDetailDto> findAll(Map<String, String> queryParams) {
        List<LocationDetail> locationDetails = locationDetailRepository.findAll(queryParams);
        return locationDetails.stream().map(locationDetail -> mapToLocationDetailDto(locationDetail)).collect(Collectors.toList());
    }

    private LocationDetailDto mapToLocationDetailDto(LocationDetail locationDetail) {
        return LocationDetailDto.builder()
                .id(locationDetail.getId())
                .floor(locationDetail.getFloor())
                .room(locationDetail.getRoom())
                .note(locationDetail.getNote())
                .createdAt(locationDetail.getCreatedAt())
                .updatedAt(locationDetail.getUpdatedAt())
                .location(locationDetail.getLocation())
                .build();
    }

    @Override
    public LocationDetailDto findById(Integer id) {
        return mapToLocationDetailDto(locationDetailRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        locationDetailRepository.delete(id);
    }

    @Override
    public void update(LocationDetail locationDetail) {
        locationDetailRepository.save(locationDetail);
    }

    @Override
    public void save(LocationDetail locationDetail, MultipartFile image) {
        locationDetailRepository.save(locationDetail);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return locationDetailRepository.count(queryParams);
    }

    @Override
    public void update(LocationDetailDto locationDetail, MultipartFile image) {
        locationDetailRepository.save(mapToLocationDetail(locationDetail));
    }

    @Override
    public void save(LocationDetail locationDetail) {
        locationDetailRepository.save(locationDetail);
    }

    @Override
    public LocationDetail update(LocationDetailDto locationDetailDto) {
        LocationDetail locationDetail = mapToLocationDetail(locationDetailDto);
        locationDetailRepository.save(locationDetail);
        return locationDetail;
    }

    private LocationDetail mapToLocationDetail(LocationDetailDto locationDetailDto) {
        return LocationDetail.builder()
                .id(locationDetailDto.getId())
                .floor(locationDetailDto.getFloor())
                .room(locationDetailDto.getRoom())
                .note(locationDetailDto.getNote())
                .createdAt(locationDetailDto.getCreatedAt())
                .updatedAt(locationDetailDto.getUpdatedAt())
                .location(locationDetailDto.getLocation())
                .build();
    }
}
