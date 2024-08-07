/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Device;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface DeviceService {

    List<DeviceDto> findAll(Map<String, String> queryParams);

    DeviceDto findById(String id);

    Device update(DeviceDto threadDto);

    void save(Device thread);

    void delete(String id);

    void update(Device device);

    void save(Device device, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(DeviceDto device, MultipartFile image);
}

