package com.tranlequocthong313.repositories;

import java.time.Year;
import java.util.List;

public interface StatsRepository {
    List<Object[]> issuePerPeriod(int year, String period);

    List<Object[]> repairCostsPerPeriod(int year, String period);

    List<Object[]> deviceStatuses();
}
