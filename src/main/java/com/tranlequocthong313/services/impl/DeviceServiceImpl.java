/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.services.DeviceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranlequocthong313
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Override
    public List<Device> findAll() {
        return List.of();
    }

    @Override
    public Object count() {
        return 515;
    }
}
