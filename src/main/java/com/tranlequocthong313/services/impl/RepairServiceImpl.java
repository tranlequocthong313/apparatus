/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.RepairDto;
import com.tranlequocthong313.models.Repair;
import com.tranlequocthong313.repositories.RepairRepository;
import com.tranlequocthong313.services.RepairService;
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
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Override
    public List<RepairDto> findAll(Map<String, String> queryParams) {
        List<Repair> repairs = repairRepository.findAll(queryParams);
        return repairs.stream().map(repair -> mapToRepairDto(repair)).collect(Collectors.toList());
    }

    private RepairDto mapToRepairDto(Repair repair) {
        return RepairDto.builder()
                .id(repair.getId())
                .receptionDate(repair.getReceptionDate())
                .completedDate(repair.getCompletedDate())
                .note(repair.getNote())
                .image(repair.getImage())
                .cost(repair.getCost())
                .repairedBy(repair.getRepairedBy())
                .device(repair.getDevice())
                .issue(repair.getIssue())
                .user(repair.getUser())
                .createdAt(repair.getCreatedAt())
                .updatedAt(repair.getUpdatedAt())
                .build();
    }

    @Override
    public RepairDto findById(Integer id) {
        return mapToRepairDto(repairRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        repairRepository.delete(id);
    }

    @Override
    public void update(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public void save(Repair repair, MultipartFile image) {
        repairRepository.save(repair);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return repairRepository.count(queryParams);
    }

    @Override
    public void update(RepairDto repair, MultipartFile image) {
        repairRepository.save(mapToRepair(repair));
    }

    @Override
    public void save(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public Repair update(RepairDto repairDto) {
        Repair repair = mapToRepair(repairDto);
        repairRepository.save(repair);
        return repair;
    }

    private Repair mapToRepair(RepairDto repairDto) {
        return Repair.builder()
                .id(repairDto.getId())
                .receptionDate(repairDto.getReceptionDate())
                .completedDate(repairDto.getCompletedDate())
                .note(repairDto.getNote())
                .image(repairDto.getImage())
                .cost(repairDto.getCost())
                .repairedBy(repairDto.getRepairedBy())
                .device(repairDto.getDevice())
                .issue(repairDto.getIssue())
                .user(repairDto.getUser())
                .createdAt(repairDto.getCreatedAt())
                .updatedAt(repairDto.getUpdatedAt())
                .build();
    }

    @Override
    public Long totalCost(Map<String, String> queryParams) {
        return repairRepository.totalCost(queryParams);
    }
}
