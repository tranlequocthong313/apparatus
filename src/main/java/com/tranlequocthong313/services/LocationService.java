/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.LocationDto;
import com.tranlequocthong313.models.Location;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface LocationService {

    List<LocationDto> findAll(Map<String, String> queryParams);

    LocationDto findById(Integer id);

    Location update(LocationDto threadDto);

    void save(Location thread);

    void delete(int id);

    void update(Location location);

    void save(Location location, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(LocationDto location, MultipartFile image);

    default List<LocationDto> findAll() {
        return findAll(null);
    }
}
