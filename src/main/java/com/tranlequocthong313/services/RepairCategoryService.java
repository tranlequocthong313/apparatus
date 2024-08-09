package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.RepairCategoryDto;
import com.tranlequocthong313.models.RepairCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RepairCategoryService {
    List<RepairCategoryDto> findAll(Map<String, String> queryParams);

    RepairCategoryDto findById(Integer id);

    RepairCategory update(RepairCategoryDto threadDto);

    void save(RepairCategory thread);

    void delete(int id);

    void update(RepairCategory repairCategory);

    void save(RepairCategory repairCategory, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(RepairCategoryDto repairCategory, MultipartFile image);

    default List<RepairCategoryDto> findAll() {
        return findAll(null);
    }

}
