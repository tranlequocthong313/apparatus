/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.DeviceCategoryDto;
import com.tranlequocthong313.models.DeviceCategory;
import com.tranlequocthong313.services.DeviceCategoryService;
import com.tranlequocthong313.services.DeviceTypeService;
import com.tranlequocthong313.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
@Controller
@RequestMapping("/device-categories")
public class DeviceCategoryController {

    @Autowired
    private DeviceCategoryService deviceCategoryService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private Utils utils;

    @GetMapping
    public String getDeviceCategories(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) deviceCategoryService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("deviceCategories", deviceCategoryService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("deviceTypes", deviceTypeService.findAll());
        model.addAttribute("type", queryParams.getOrDefault("type", ""));
        return "device-categories";
    }

    @GetMapping("/{id}")
    public String getDeviceCategory(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("deviceCategory", deviceCategoryService.findById(id));
        return "device-category";
    }

    @GetMapping("/create")
    public String createDeviceCategoryForm(Model model) {
        model.addAttribute("deviceTypes", deviceTypeService.findAll());
        model.addAttribute("deviceCategory", new DeviceCategory());
        return "device-category-create";
    }

    @PostMapping("/create")
    public String createDeviceCategory(@Valid @ModelAttribute("device-category") DeviceCategory deviceCategory,
                                       BindingResult result,
                                       Model model,
                                       @RequestPart("img") MultipartFile image) {
        if (result.hasErrors()) {
            model.addAttribute("deviceTypes", deviceTypeService.findAll());
            model.addAttribute("deviceCategory", deviceCategory);
            return "device-category-create";
        }
        deviceCategoryService.save(deviceCategory, image);
        return "redirect:/device-categories";
    }

    @GetMapping("/{id}/update")
    public String updateDeviceCategoryForm(@PathVariable("id") int deviceCategoryId, Model model) {
        model.addAttribute("deviceTypes", deviceTypeService.findAll());
        model.addAttribute("deviceCategory", deviceCategoryService.findById(deviceCategoryId));
        return "device-category-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateDeviceCategory(
            @Valid @ModelAttribute DeviceCategory deviceCategory,
            @PathVariable(value = "id") int id,
            @RequestPart("img") MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("deviceTypes", deviceTypeService.findAll());
            model.addAttribute("deviceCategory", deviceCategory);
            return "device-category-update";
        }
        DeviceCategoryDto deviceCategoryDto = deviceCategoryService.findById(id);
        deviceCategoryDto.setDeviceType(deviceCategory.getDeviceType());
        deviceCategoryDto.setName(deviceCategory.getName());
        deviceCategoryDto.setModel(deviceCategory.getModel());
        deviceCategoryDto.setOrigin(deviceCategory.getOrigin());
        deviceCategoryDto.setProducer(deviceCategory.getProducer());
        deviceCategoryService.update(deviceCategoryDto, image);
        return "redirect:/device-categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteDeviceCategory(@PathVariable(value = "id") int id) {
        deviceCategoryService.delete(id);
        return "redirect:/device-categories";
    }

}
