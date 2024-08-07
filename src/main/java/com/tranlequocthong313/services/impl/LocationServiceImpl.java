/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.cloudinary.Cloudinary;
import com.tranlequocthong313.dto.LocationDto;
import com.tranlequocthong313.models.Location;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.LocationService;
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
public class LocationServiceImpl implements LocationService {

    @Autowired
    private BaseRepository<Location, Integer> locationRepository;

    @Override
    public List<LocationDto> findAll(Map<String, String> queryParams) {
        List<Location> locations = locationRepository.findAll(queryParams);
        return locations.stream().map(location -> mapToLocationDto(location)).collect(Collectors.toList());
    }

    private LocationDto mapToLocationDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .address(location.getAddress())
                .building(location.getBuilding())
                .note(location.getNote())
                .createdAt(location.getCreatedAt())
                .updatedAt(location.getUpdatedAt())
                .build();
    }

    @Override
    public LocationDto findById(Integer id) {
        return mapToLocationDto(locationRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        locationRepository.delete(id);
    }

    @Override
    public void update(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void save(Location location, MultipartFile image) {
        locationRepository.save(location);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return locationRepository.count(queryParams);
    }

    @Override
    public void update(LocationDto location, MultipartFile image) {
        locationRepository.save(mapToLocation(location));
    }

    @Override
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location update(LocationDto locationDto) {
        Location location = mapToLocation(locationDto);
        locationRepository.save(location);
        return location;
    }

    private Location mapToLocation(LocationDto locationDto) {
        return Location.builder()
                .id(locationDto.getId())
                .address(locationDto.getAddress())
                .building(locationDto.getBuilding())
                .note(locationDto.getNote())
                .createdAt(locationDto.getCreatedAt())
                .updatedAt(locationDto.getUpdatedAt())
                .build();
    }
}
