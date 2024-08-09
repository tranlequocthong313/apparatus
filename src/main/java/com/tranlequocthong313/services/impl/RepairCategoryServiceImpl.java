/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.RepairCategoryDto;
import com.tranlequocthong313.models.RepairCategory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.RepairCategoryService;
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
public class RepairCategoryServiceImpl implements RepairCategoryService {

    @Autowired
    private BaseRepository<RepairCategory, Integer> repairCategoryRepository;

    @Override
    public List<RepairCategoryDto> findAll(Map<String, String> queryParams) {
        List<RepairCategory> repairCategories = repairCategoryRepository.findAll(queryParams);
        return repairCategories.stream().map(repairCategory -> mapToRepairCategoryDto(repairCategory)).collect(Collectors.toList());
    }

    private RepairCategoryDto mapToRepairCategoryDto(RepairCategory repairCategory) {
        return RepairCategoryDto.builder()
                .id(repairCategory.getId())
                .name(repairCategory.getName())
                .build();
    }

    @Override
    public RepairCategoryDto findById(Integer id) {
        return mapToRepairCategoryDto(repairCategoryRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        repairCategoryRepository.delete(id);
    }

    @Override
    public void update(RepairCategory repairCategory) {
        repairCategoryRepository.save(repairCategory);
    }

    @Override
    public void save(RepairCategory repairCategory, MultipartFile image) {
        repairCategoryRepository.save(repairCategory);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return repairCategoryRepository.count(queryParams);
    }

    @Override
    public void update(RepairCategoryDto repairCategory, MultipartFile image) {
        repairCategoryRepository.save(mapToRepairCategory(repairCategory));
    }

    @Override
    public void save(RepairCategory repairCategory) {
        repairCategoryRepository.save(repairCategory);
    }

    @Override
    public RepairCategory update(RepairCategoryDto repairCategoryDto) {
        RepairCategory repairCategory = mapToRepairCategory(repairCategoryDto);
        repairCategoryRepository.save(repairCategory);
        return repairCategory;
    }

    private RepairCategory mapToRepairCategory(RepairCategoryDto repairCategoryDto) {
        return RepairCategory.builder()
                .id(repairCategoryDto.getId())
                .name(repairCategoryDto.getName())
                .build();
    }
}
