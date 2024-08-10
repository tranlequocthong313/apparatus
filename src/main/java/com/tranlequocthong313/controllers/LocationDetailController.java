/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.LocationDetailDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.models.LocationDetail;
import com.tranlequocthong313.services.ActivityLogService;
import com.tranlequocthong313.services.LocationDetailService;
import com.tranlequocthong313.services.LocationService;
import com.tranlequocthong313.services.UserService;
import com.tranlequocthong313.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
@Controller
@RequestMapping("/location-details")
public class LocationDetailController {

    @Autowired
    private LocationDetailService locationDetailService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserService userService;
    @Autowired
    private Utils utils;
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public String getLocationDetails(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) locationDetailService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("locationDetails", locationDetailService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        model.addAttribute("location", queryParams.getOrDefault("location", ""));
        model.addAttribute("locations", locationService.findAll());
        return "location-details";
    }

    @GetMapping("/{id}")
    public String getLocationDetail(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("locationDetail", locationDetailService.findById(id));
        return "location-detail";
    }

    @GetMapping("/create")
    public String createLocationDetailForm(Model model) {
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("locationDetail", new LocationDetail());
        return "location-detail-create";
    }

    @PostMapping("/create")
    public String createLocationDetail(@Valid @ModelAttribute("locationDetail") LocationDetail locationDetail,
                                       BindingResult result,
                                       Model model,
                                       @RequestPart(value = "img", required = false) MultipartFile image) {
        if (result.hasErrors()) {
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("locationDetail", locationDetail);
            return "location-detail-create";
        }
        locationDetailService.save(locationDetail, image);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " created a new location detail " + locationDetail.getId())
                        .build()
        );

        return "redirect:/location-details";
    }

    @GetMapping("/{id}/update")
    public String updateLocationDetailForm(@PathVariable("id") int locationDetailId, Model model) {
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("locationDetail", locationDetailService.findById(locationDetailId));
        return "location-detail-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateLocationDetail(
            @Valid @ModelAttribute LocationDetail locationDetail,
            @PathVariable(value = "id") int id,
            @RequestPart(value = "img", required = false) MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("locationDetail", locationDetail);
            return "location-detail-update";
        }
        LocationDetailDto locationDetailDto = locationDetailService.findById(id);
        locationDetailDto.setFloor(locationDetail.getFloor());
        locationDetailDto.setRoom(locationDetail.getRoom());
        locationDetailDto.setLocation(locationDetail.getLocation());
        locationDetailDto.setNote(locationDetail.getNote());
        locationDetailService.update(locationDetailDto, image);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " updated a location detail " + locationDetail.getId())
                        .build()
        );
        return "redirect:/location-details";
    }

    @GetMapping("/{id}/delete")
    public String deleteLocationDetail(@PathVariable(value = "id") int id) {
        locationDetailService.delete(id);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " deleted a location detail " + id)
                        .build()
        );
        return "redirect:/location-details";
    }

    @PostMapping("/bulk-action")
    public String bulkActionLocationDetail(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> {
                locationDetailService.delete(Integer.parseInt(id));
                activityLogService.save(
                        ActivityLog.builder()
                                .user(userService.getCurrentUser())
                                .log(userService.getCurrentUser().getFullName() + " deleted a location detail " + id)
                                .build()
                );
            });
        }
        return "redirect:/location-details";
    }
}
