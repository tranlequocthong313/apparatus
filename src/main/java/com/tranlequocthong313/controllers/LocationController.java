/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.LocationDto;
import com.tranlequocthong313.models.Location;
import com.tranlequocthong313.services.LocationService;
import com.tranlequocthong313.services.DeviceTypeService;
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
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private Utils utils;

    @GetMapping
    public String getLocations(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) locationService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("locations", locationService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        return "locations";
    }

    @GetMapping("/{id}")
    public String getLocation(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("location", locationService.findById(id));
        return "location";
    }

    @GetMapping("/create")
    public String createLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "location-create";
    }

    @PostMapping("/create")
    public String createLocation(@Valid @ModelAttribute("location") Location location,
                                       BindingResult result,
                                       Model model,
                                       @RequestPart(value = "img", required = false) MultipartFile image) {
        if (result.hasErrors()) {
            model.addAttribute("location", location);
            return "location-create";
        }
        locationService.save(location, image);
        return "redirect:/locations";
    }

    @GetMapping("/{id}/update")
    public String updateLocationForm(@PathVariable("id") int locationId, Model model) {
        model.addAttribute("location", locationService.findById(locationId));
        return "location-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateLocation(
            @Valid @ModelAttribute Location location,
            @PathVariable(value = "id") int id,
            @RequestPart(value = "img", required = false) MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("location", location);
            return "location-update";
        }
        LocationDto locationDto = locationService.findById(id);
        locationDto.setAddress(location.getAddress());
        locationDto.setBuilding(location.getBuilding());
        locationDto.setNote(location.getNote());
        locationService.update(locationDto, image);
        return "redirect:/locations";
    }

    @GetMapping("/{id}/delete")
    public String deleteLocation(@PathVariable(value = "id") int id) {
        locationService.delete(id);
        return "redirect:/locations";
    }

    @PostMapping("/bulk-action")
    public String bulkActionLocation(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> locationService.delete(Integer.parseInt(id)));
        }
        return "redirect:/locations";
    }
}
