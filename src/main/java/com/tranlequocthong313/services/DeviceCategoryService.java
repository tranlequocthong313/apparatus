/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.DeviceCategoryDto;
import com.tranlequocthong313.models.DeviceCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface DeviceCategoryService {

    List<DeviceCategoryDto> findAll(Map<String, String> queryParams);

    DeviceCategoryDto findById(Integer id);

    DeviceCategory update(DeviceCategoryDto threadDto);

    void save(DeviceCategory thread);

    void delete(int id);

    void update(DeviceCategory deviceCategory);

    void save(DeviceCategory deviceCategory, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(DeviceCategoryDto deviceCategory, MultipartFile image);
}
