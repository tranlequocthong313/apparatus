/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.LocationHistory;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.services.*;
import com.tranlequocthong313.services.DeviceService;
import com.tranlequocthong313.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private DeviceCategoryService deviceCategoryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationDetailService locationDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private LocationHistoryService locationHistoryService;
    @Autowired
    private Utils utils;

    @GetMapping
    public String getDevices(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) deviceService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("devices", deviceService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("deviceTypes", deviceTypeService.findAll());
        model.addAttribute("type", queryParams.getOrDefault("type", ""));
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("location", queryParams.getOrDefault("location", ""));
        model.addAttribute("providers", providerService.findAll());
        model.addAttribute("provider", queryParams.getOrDefault("provider", ""));
        model.addAttribute("statuses", utils.getNames(Device.Status.class));
        model.addAttribute("status", queryParams.getOrDefault("status", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        return "devices";
    }

    @GetMapping("/{id}")
    public String getDevice(@PathVariable(value = "id") String id, Model model) {
        model.addAttribute("device", deviceService.findById(id));
        return "device";
    }

    @GetMapping("/create")
    public String createDeviceForm(Model model) {
        model.addAttribute("deviceCategories", deviceCategoryService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("locationDetails", locationDetailService.findAll());
        model.addAttribute("providers", providerService.findAll());
        model.addAttribute("users", userService.findByRole(User.UserRole.ROLE_ADMIN));
        model.addAttribute("statuses", Device.Status.values());
        model.addAttribute("device", new Device());
        return "device-create";
    }

    @PostMapping("/create")
    public String createDevice(@Valid @ModelAttribute("device") Device device,
                               BindingResult result,
                               Model model,
                               @RequestPart("img") MultipartFile image) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("deviceCategories", deviceCategoryService.findAll());
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("locationDetails", locationDetailService.findAll());
            model.addAttribute("providers", providerService.findAll());
            model.addAttribute("users", userService.findByRole(User.UserRole.ROLE_ADMIN));
            model.addAttribute("statuses", Device.Status.values());
            model.addAttribute("device", device);
            return "device-create";
        }
        deviceService.save(device, image);
        return "redirect:/devices";
    }

    @GetMapping("/{id}/update")
    public String updateDeviceForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("deviceCategories", deviceCategoryService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("locationDetails", locationDetailService.findAll());
        model.addAttribute("providers", providerService.findAll());
        model.addAttribute("users", userService.findByRole(User.UserRole.ROLE_ADMIN));
        model.addAttribute("statuses", Device.Status.values());
        model.addAttribute("device", deviceService.findById(id));
        return "device-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateDevice(
            @Valid @ModelAttribute Device device,
            @PathVariable(value = "id") String id,
            @RequestPart("img") MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("deviceCategories", deviceCategoryService.findAll());
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("locationDetails", locationDetailService.findAll());
            model.addAttribute("providers", providerService.findAll());
            model.addAttribute("users", userService.findByRole(User.UserRole.ROLE_ADMIN));
            model.addAttribute("statuses", Device.Status.values());
            model.addAttribute("device", device);
            return "device-update";
        }

        DeviceDto deviceDto = deviceService.findById(id);
        deviceDto.setDateStartedOperation(device.getDateStartedOperation());
        deviceDto.setDateOfManufacture(device.getDateOfManufacture());
        deviceDto.setSerial(device.getSerial());
        deviceDto.setPrice(device.getPrice());
        deviceDto.setDateOfPurchase(device.getDateOfPurchase());
        deviceDto.setWarrantyPeriod(device.getWarrantyPeriod());
        deviceDto.setLink(device.getLink());
        deviceDto.setYearOfDepreciation(device.getYearOfDepreciation());
        deviceDto.setStatus(device.getStatus());
        deviceDto.setNote(device.getNote());
        deviceDto.setDeviceCategory(device.getDeviceCategory());
        deviceDto.setLocation(device.getLocation());
        deviceDto.setLocationDetail(device.getLocationDetail());
        deviceDto.setProvider(device.getProvider());
        deviceDto.setUser(device.getUser());
        deviceService.update(deviceDto, image);
        locationHistoryService.save(LocationHistory.builder()
                .dateOfMoving(new Date())
                .location(device.getLocation())
                .locationDetail(device.getLocationDetail())
                .device(device)
                .build()
        );
        return "redirect:/devices";
    }

    @GetMapping("/{id}/delete")
    public String deleteDevice(@PathVariable(value = "id") String id) {
        deviceService.delete(id);
        return "redirect:/devices";
    }

    @PostMapping("/bulk-action")
    public String bulkActionDevice(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> deviceService.delete(id));
        }
        return "redirect:/devices";
    }
}

