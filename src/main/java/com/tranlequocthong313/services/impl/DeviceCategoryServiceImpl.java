/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tranlequocthong313.dto.DeviceCategoryDto;
import com.tranlequocthong313.models.DeviceCategory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.DeviceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tranlequocthong313
 */
@Service
public class DeviceCategoryServiceImpl implements DeviceCategoryService {

    @Autowired
    private BaseRepository<DeviceCategory, Integer> deviceCategoryRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<DeviceCategoryDto> findAll(Map<String, String> queryParams) {
        List<DeviceCategory> deviceCategories = deviceCategoryRepository.findAll(queryParams);
        return deviceCategories.stream().map(deviceCategory -> mapToDeviceCategoryDto(deviceCategory)).collect(Collectors.toList());
    }

    private DeviceCategoryDto mapToDeviceCategoryDto(DeviceCategory deviceCategory) {
        return DeviceCategoryDto.builder()
                .id(deviceCategory.getId())
                .name(deviceCategory.getName())
                .model(deviceCategory.getModel())
                .producer(deviceCategory.getProducer())
                .origin(deviceCategory.getOrigin())
                .image(deviceCategory.getImage())
                .deviceType(deviceCategory.getDeviceType())
                .createdAt(deviceCategory.getCreatedAt())
                .updatedAt(deviceCategory.getUpdatedAt())
                .build();
    }

    @Override
    public DeviceCategoryDto findById(Integer id) {
        return mapToDeviceCategoryDto(deviceCategoryRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        deviceCategoryRepository.delete(id);
    }

    @Override
    public void update(DeviceCategory deviceCategory) {
        deviceCategoryRepository.save(deviceCategory);
    }

    @Override
    public void save(DeviceCategory deviceCategory, MultipartFile image) {
        saveAndStoreImage(deviceCategory, image);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return deviceCategoryRepository.count(queryParams);
    }

    @Override
    public void update(DeviceCategoryDto deviceCategory, MultipartFile image) {
        saveAndStoreImage(mapToDeviceCategory(deviceCategory), image);
    }

    private void saveAndStoreImage(DeviceCategory deviceCategory, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                deviceCategory.setImage(res.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        deviceCategoryRepository.save(deviceCategory);
    }

    @Override
    public void save(DeviceCategory deviceCategory) {
        deviceCategoryRepository.save(deviceCategory);
    }

    @Override
    public DeviceCategory update(DeviceCategoryDto deviceCategoryDto) {
        DeviceCategory deviceCategory = mapToDeviceCategory(deviceCategoryDto);
        deviceCategoryRepository.save(deviceCategory);
        return deviceCategory;
    }

    private DeviceCategory mapToDeviceCategory(DeviceCategoryDto deviceCategoryDto) {
        return DeviceCategory.builder()
                .id(deviceCategoryDto.getId())
                .name(deviceCategoryDto.getName())
                .model(deviceCategoryDto.getModel())
                .producer(deviceCategoryDto.getProducer())
                .origin(deviceCategoryDto.getOrigin())
                .image(deviceCategoryDto.getImage())
                .deviceType(deviceCategoryDto.getDeviceType())
                .createdAt(deviceCategoryDto.getCreatedAt())
                .updatedAt(deviceCategoryDto.getUpdatedAt())
                .build();
    }


}
