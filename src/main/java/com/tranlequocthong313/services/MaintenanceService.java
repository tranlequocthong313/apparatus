package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.MaintenanceDto;
import com.tranlequocthong313.models.Maintenance;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MaintenanceService {
    List<MaintenanceDto> findAll(Map<String, String> queryParams);

    MaintenanceDto findById(Integer id);

    Maintenance update(MaintenanceDto threadDto);

    void save(Maintenance thread);

    void delete(int id);

    void update(Maintenance maintenance);

    void save(Maintenance maintenance, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(MaintenanceDto maintenance, MultipartFile image);

    default List<MaintenanceDto> findAll() {
        return findAll(null);
    }

}
