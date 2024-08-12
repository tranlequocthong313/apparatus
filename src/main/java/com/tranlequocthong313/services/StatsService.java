package com.tranlequocthong313.services;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface StatsService {
	String PERIOD = "MONTH";

	default List<Object[]> issuePerPeriod() {
		return issuePerPeriod(Year.now().getValue(), PERIOD);
	}

	default List<Object[]> repairCostsPerPeriod() {
		return repairCostsPerPeriod(Year.now().getValue(), PERIOD);
	}

	List<Object[]> issuePerPeriod(int year, String period);

	List<Object[]> repairCostsPerPeriod(int year, String period);

	List<Object[]> deviceStatuses();

	List<Object[]> repairCountByRepairedBy(Map<String, String> queryParams);

	List<Object[]> repairCostsPerMonth(Map<String, String> queryParams);

	List<Object[]> deviceCategoryRepairCountByRepairedBy(Map<String, String> queryParams);

	List<Object[]> deviceTypeRepairCountByRepairedBy(Map<String, String> queryParams);

	List<Object[]> deviceCategoryRepairCostsPerMonth(Map<String, String> queryParams);

	List<Object[]> deviceTypeRepairCostsPerMonth(Map<String, String> queryParams);
}
