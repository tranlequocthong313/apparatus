/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.DeviceService;
import com.tranlequocthong313.services.DeviceService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tranlequocthong313
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private BaseRepository<Device, String> deviceRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<DeviceDto> findAll(Map<String, String> queryParams) {
        List<Device> devices = deviceRepository.findAll(queryParams);
        return devices.stream().map(device -> mapToDeviceDto(device)).collect(Collectors.toList());
    }

    private DeviceDto mapToDeviceDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .dateStartedOperation(device.getDateStartedOperation())
                .dateOfManufacture(device.getDateOfManufacture())
                .image(device.getImage())
                .serial(device.getSerial())
                .price(device.getPrice())
                .dateOfPurchase(device.getDateOfPurchase())
                .warrantyPeriod(device.getWarrantyPeriod())
                .link(device.getLink())
                .yearOfDepreciation(device.getYearOfDepreciation())
                .status(device.getStatus())
                .note(device.getNote())
                .deviceCategory(device.getDeviceCategory())
                .location(device.getLocation())
                .locationDetail(device.getLocationDetail())
                .provider(device.getProvider())
                .user(device.getUser())
                .createdAt(device.getCreatedAt())
                .updatedAt(device.getUpdatedAt())
                .build();
    }

    @Override
    public DeviceDto findById(String id) {
        return mapToDeviceDto(deviceRepository.getReferenceById(id));
    }

    @Override
    public void delete(String id) {
        deviceRepository.delete(id);
    }

    @Override
    public void update(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void save(Device device, MultipartFile image) {
        saveAndStoreImage(device, image);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return deviceRepository.count(queryParams);
    }

    @Override
    public void update(DeviceDto device, MultipartFile image) {
        saveAndStoreImage(mapToDevice(device), image);
    }

    private void saveAndStoreImage(Device device, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                device.setImage(res.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        deviceRepository.save(device);
    }

    @Override
    public void save(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public Device update(DeviceDto deviceDto) {
        Device device = mapToDevice(deviceDto);
        deviceRepository.save(device);
        return device;
    }

    private Device mapToDevice(DeviceDto deviceDto) {
        return Device.builder()
                .id(deviceDto.getId())
                .dateStartedOperation(deviceDto.getDateStartedOperation())
                .dateOfManufacture(deviceDto.getDateOfManufacture())
                .image(deviceDto.getImage())
                .serial(deviceDto.getSerial())
                .price(deviceDto.getPrice())
                .dateOfPurchase(deviceDto.getDateOfPurchase())
                .warrantyPeriod(deviceDto.getWarrantyPeriod())
                .link(deviceDto.getLink())
                .yearOfDepreciation(deviceDto.getYearOfDepreciation())
                .status(deviceDto.getStatus())
                .note(deviceDto.getNote())
                .deviceCategory(deviceDto.getDeviceCategory())
                .location(deviceDto.getLocation())
                .locationDetail(deviceDto.getLocationDetail())
                .provider(deviceDto.getProvider())
                .user(deviceDto.getUser())
                .createdAt(deviceDto.getCreatedAt())
                .updatedAt(deviceDto.getUpdatedAt())
                .build();
    }
}
