package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.repositories.StatsRepository;
import com.tranlequocthong313.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatsServiceImpl implements StatsService {
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public List<Object[]> issuePerPeriod(int year, String period) {
		return statsRepository.issuePerPeriod(year, period);
	}

	@Override
	public List<Object[]> repairCostsPerPeriod(int year, String period) {
		return statsRepository.repairCostsPerPeriod(year, period);
	}

	@Override
	public List<Object[]> deviceStatuses() {
		return statsRepository.deviceStatuses();
	}

	@Override
	public List<Object[]> repairCountByRepairedBy(Map<String, String> queryParams) {
		return statsRepository.repairCountByRepairedBy(queryParams);
	}

	@Override
	public List<Object[]> repairCostsPerMonth(Map<String, String> queryParams) {
		return statsRepository.repairCostsPerMonth(queryParams);
	}

	@Override
	public List<Object[]> deviceCategoryRepairCountByRepairedBy(Map<String, String> queryParams) {
		return statsRepository.deviceCategoryRepairCountByRepairedBy(queryParams);
	}

	@Override
	public List<Object[]> deviceTypeRepairCountByRepairedBy(Map<String, String> queryParams) {
		return statsRepository.deviceTypeRepairCountByRepairedBy(queryParams);
	}

	@Override
	public List<Object[]> deviceCategoryRepairCostsPerMonth(Map<String, String> queryParams) {
		return statsRepository.deviceCategoryRepairCostsPerMonth(queryParams);
	}

	@Override
	public List<Object[]> deviceTypeRepairCostsPerMonth(Map<String, String> queryParams) {
		return statsRepository.deviceTypeRepairCostsPerMonth(queryParams);
	}
}
