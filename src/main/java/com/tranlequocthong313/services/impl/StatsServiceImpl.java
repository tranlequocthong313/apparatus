package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.repositories.StatsRepository;
import com.tranlequocthong313.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
