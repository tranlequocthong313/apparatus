/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories;

import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.models.User;

import java.util.List;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
public interface IssueRepository extends BaseRepository<Issue, Integer> {

    default Long totalCost() {
        return totalCost(null) ;
    }

    Long totalCost(Map<String, String> queryParams);

    Long unresolvedDays(Map<String, String> queryParams);
}
