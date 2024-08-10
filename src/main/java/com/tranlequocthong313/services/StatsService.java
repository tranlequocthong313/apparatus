package com.tranlequocthong313.services;

import java.time.Year;
import java.util.List;

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
}
