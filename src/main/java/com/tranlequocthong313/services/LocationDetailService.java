/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.LocationDetailDto;
import com.tranlequocthong313.models.LocationDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface LocationDetailService {

    List<LocationDetailDto> findAll(Map<String, String> queryParams);

    LocationDetailDto findById(Integer id);

    LocationDetail update(LocationDetailDto threadDto);

    void save(LocationDetail thread);

    void delete(int id);

    void update(LocationDetail locationDetail);

    void save(LocationDetail locationDetail, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(LocationDetailDto locationDetail, MultipartFile image);
}
