package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ActivityLogDto;
import com.tranlequocthong313.models.ActivityLog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ActivityLogService {
    List<ActivityLogDto> findAll(Map<String, String> queryParams);

    ActivityLogDto findById(Integer id);

    ActivityLog update(ActivityLogDto threadDto);

    void save(ActivityLog thread);

    void delete(int id);

    void update(ActivityLog activityLog);

    void save(ActivityLog activityLog, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(ActivityLogDto activityLog, MultipartFile image);

    default List<ActivityLogDto> findAll() {
        return findAll(null);
    }

}
