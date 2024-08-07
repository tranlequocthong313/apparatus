/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.services.LocationHistoryService;
import com.tranlequocthong313.services.LocationService;
import com.tranlequocthong313.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
@Controller
@RequestMapping("/location-histories")
public class LocationHistoryController {

    @Autowired
    private LocationHistoryService locationHistoryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private Utils utils;

    @GetMapping
    public String getLocationHistorys(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) locationHistoryService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("locationHistories", locationHistoryService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        model.addAttribute("location", queryParams.getOrDefault("location", ""));
        model.addAttribute("locations", locationService.findAll());
        return "location-histories";
    }

    @GetMapping("/{id}/delete")
    public String deleteLocationHistory(@PathVariable(value = "id") int id) {
        locationHistoryService.delete(id);
        return "redirect:/location-histories";
    }

    @PostMapping("/bulk-action")
    public String bulkActionLocationHistory(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> locationHistoryService.delete(Integer.parseInt(id)));
        }
        return "redirect:/location-histories";
    }
}
