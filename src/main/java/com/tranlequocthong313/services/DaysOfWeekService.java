package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.DaysOfWeekDto;
import com.tranlequocthong313.models.DaysOfWeek;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DaysOfWeekService {
    List<DaysOfWeekDto> findAll(Map<String, String> queryParams);

    DaysOfWeekDto findById(Integer id);

    DaysOfWeek update(DaysOfWeekDto threadDto);

    void save(DaysOfWeek thread);

    void delete(int id);

    void update(DaysOfWeek daysOfWeek);

    void save(DaysOfWeek daysOfWeek, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(DaysOfWeekDto daysOfWeek, MultipartFile image);

    default List<DaysOfWeekDto> findAll() {
        return findAll(null);
    }

	DaysOfWeek mapToDaysOfWeek(DaysOfWeekDto daysOfWeekDto);
}
