/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.DeviceTypeDto;
import com.tranlequocthong313.models.DeviceType;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface DeviceTypeService {

    default List<DeviceTypeDto> findAll() {
        return findAll(null);
    }

    List<DeviceTypeDto> findAll(Map<String, String> queryParams);

    DeviceTypeDto findById(Integer id);

    DeviceType update(DeviceTypeDto deviceTypeDto);

    void save(DeviceType deviceType);

    void delete(int id);
}
