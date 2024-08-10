/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.ActivityLogDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.ActivityLogService;
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
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private BaseRepository<ActivityLog, Integer> activityLogRepository;

    @Override
    public List<ActivityLogDto> findAll(Map<String, String> queryParams) {
        List<ActivityLog> activityLogs = activityLogRepository.findAll(queryParams);
        return activityLogs.stream().map(activityLog -> mapToActivityLogDto(activityLog)).collect(Collectors.toList());
    }

    private ActivityLogDto mapToActivityLogDto(ActivityLog activityLog) {
        return ActivityLogDto.builder()
                .id(activityLog.getId())
                .log(activityLog.getLog())
                .user(activityLog.getUser())
                .createdAt(activityLog.getCreatedAt())
                .build();
    }

    @Override
    public ActivityLogDto findById(Integer id) {
        return mapToActivityLogDto(activityLogRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        activityLogRepository.delete(id);
    }

    @Override
    public void update(ActivityLog activityLog) {
        activityLogRepository.save(activityLog);
    }

    @Override
    public void save(ActivityLog activityLog, MultipartFile image) {
        activityLogRepository.save(activityLog);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return activityLogRepository.count(queryParams);
    }

    @Override
    public void update(ActivityLogDto activityLog, MultipartFile image) {
        activityLogRepository.save(mapToActivityLog(activityLog));
    }

    @Override
    public void save(ActivityLog activityLog) {
        activityLogRepository.save(activityLog);
    }

    @Override
    public ActivityLog update(ActivityLogDto activityLogDto) {
        ActivityLog activityLog = mapToActivityLog(activityLogDto);
        activityLogRepository.save(activityLog);
        return activityLog;
    }

    private ActivityLog mapToActivityLog(ActivityLogDto activityLogDto) {
        return ActivityLog.builder()
                .id(activityLogDto.getId())
                .log(activityLogDto.getLog())
                .user(activityLogDto.getUser())
                .createdAt(activityLogDto.getCreatedAt())
                .build();
    }
}
