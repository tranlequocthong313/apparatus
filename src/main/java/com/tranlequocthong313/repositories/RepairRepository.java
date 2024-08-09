/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories;

import com.tranlequocthong313.models.Repair;

import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface RepairRepository extends BaseRepository<Repair, Integer> {

    default Long totalCost() {
        return totalCost(null) ;
    }

    Long totalCost(Map<String, String> queryParams);
}
