package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.dto.RepairDto;
import com.tranlequocthong313.models.Repair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RepairService {
	List<RepairDto> findAll(Map<String, String> queryParams);

	RepairDto findById(Integer id);

	Repair update(RepairDto threadDto);

	void save(Repair thread);

	void delete(int id);

	void update(Repair repair);

	void save(Repair repair, MultipartFile image);

	default Long count() {
		return count(null);
	}

	Long count(Map<String, String> queryParams);

	void update(RepairDto repair, MultipartFile image);

	default List<RepairDto> findAll() {
		return findAll(null);
	}

	default Long totalCost() {
		return totalCost(null);
	}

	Long totalCost(Map<String, String> queryParams);
}
