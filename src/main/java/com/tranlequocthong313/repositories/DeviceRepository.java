/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.repositories;

import com.tranlequocthong313.models.Device;
import java.util.List;

/**
 *
 * @author tranlequocthong313
 */
public interface DeviceRepository {
    List<Device> findAll();
}
