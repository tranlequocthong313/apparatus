package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.LocationHistoryDto;
import com.tranlequocthong313.models.LocationHistory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface LocationHistoryService {
    List<LocationHistoryDto> findAll(Map<String, String> queryParams);

    LocationHistoryDto findById(Integer id);

    LocationHistory update(LocationHistoryDto threadDto);

    void save(LocationHistory thread);

    void delete(int id);

    void update(LocationHistory locationHistory);

    void save(LocationHistory locationHistory, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(LocationHistoryDto locationHistory, MultipartFile image);

    default List<LocationHistoryDto> findAll() {
        return findAll(null);
    }

}
