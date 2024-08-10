/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.dto.IssueDto;
import com.tranlequocthong313.dto.RepairDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Repair;
import com.tranlequocthong313.services.*;
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
@RequestMapping("/repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RepairCategoryService repairCategoryService;
    @Autowired
    private IssueService issueService;
    @Autowired
    private Utils utils;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public String getRepairs(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) repairService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("repairs", repairService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        model.addAttribute("repairedBy", queryParams.getOrDefault("repairedby", ""));
        model.addAttribute("repairedBys", utils.getNames(Repair.RepairedBy.class));
        model.addAttribute("category", queryParams.getOrDefault("category", ""));
        model.addAttribute("repairCategories", repairCategoryService.findAll());
        return "repairs";
    }

    @GetMapping("/{id}")
    public String getRepair(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("repair", repairService.findById(id));
        return "repair";
    }

    @GetMapping("/device/{deviceId}/create")
    public String createRepairDeviceForm(Model model, @PathVariable(value = "deviceId") String deviceId) {
        model.addAttribute("repairedBys", utils.getNames(Repair.RepairedBy.class));
        model.addAttribute("repairCategories", repairCategoryService.findAll());
        Repair repair = new Repair();
        repair.setDevice(deviceService.mapToDevice(deviceService.findById(deviceId)));
        model.addAttribute("repair", repair);
        return "repair-create";
    }

    @GetMapping("/issue/{issueId}/create")
    public String createRepairIssueForm(Model model, @PathVariable(value = "issueId") int issueId) {
        model.addAttribute("repairedBys", utils.getNames(Repair.RepairedBy.class));
        model.addAttribute("repairCategories", repairCategoryService.findAll());
        Repair repair = new Repair();
        repair.setIssue(issueService.mapToIssue(issueService.findById(issueId)));
        model.addAttribute("repair", repair);
        return "repair-create";
    }

    @PostMapping("/create")
    public String createRepair(@Valid @ModelAttribute("repair") Repair repair,
                               BindingResult result,
                               Model model,
                               @RequestPart(value = "img", required = false) MultipartFile image) {
        if (result.hasErrors()) {
            model.addAttribute("repairedBys", utils.getNames(Repair.RepairedBy.class));
            model.addAttribute("repairCategories", repairCategoryService.findAll());
            model.addAttribute("repair", repair);
            return "repair-create";
        }
        repairService.save(repair, image);
        DeviceDto device = null;
        if (repair.getIssue() != null && repair.getIssue().getId() > 0) {
            IssueDto issue = issueService.findById(repair.getIssue().getId());
            issue.setNote(repair.getNote());
            issue.setCost(repair.getCost());
            issue.setResolvedAt(repair.getCompletedDate());
            issue.setDone(true);
            issueService.update(issue);
            device = deviceService.findById(issue.getDevice().getId());
        } else {
            device = deviceService.findById(repair.getDevice().getId());
        }
        device.setStatus(Device.Status.OPERATING);
        deviceService.update(device);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " created a new repair " + repair.getId())
                        .build()
        );
        return "redirect:/repairs";
    }

    @GetMapping("/{id}/update")
    public String updateRepairForm(@PathVariable("id") int repairId, Model model) {
        model.addAttribute("repairCategories", repairCategoryService.findAll());
        model.addAttribute("repairedBys", utils.getNames(Repair.RepairedBy.class));
        model.addAttribute("repair", repairService.findById(repairId));
        return "repair-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateRepair(
            @Valid @ModelAttribute Repair repair,
            @PathVariable(value = "id") int id,
            @RequestPart(value = "img", required = false) MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("repairCategories", repairCategoryService.findAll());
            model.addAttribute("repairedBys", utils.getNames(Repair.RepairedBy.class));
            model.addAttribute("repair", repair);
            return "repair-update";
        }
        RepairDto repairDto = repairService.findById(id);
        repairDto.setReceptionDate(repair.getReceptionDate());
        repairDto.setCompletedDate(repair.getCompletedDate());
        repairDto.setNote(repair.getNote());
        repairDto.setCost(repair.getCost());
        repairDto.setRepairedBy(repair.getRepairedBy());
        repairService.update(repairDto, image);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " updated a repair " + repair.getId())
                        .build()
        );
        return "redirect:/repairs";
    }

    @GetMapping("/{id}/delete")
    public String deleteRepair(@PathVariable(value = "id") int id) {
        repairService.delete(id);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " deleted a repair " + id)
                        .build()
        );
        return "redirect:/repairs";
    }

    @PostMapping("/bulk-action")
    public String bulkActionRepair(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> {
                repairService.delete(Integer.parseInt(id));
                activityLogService.save(
                        ActivityLog.builder()
                                .user(userService.getCurrentUser())
                                .log(userService.getCurrentUser().getFullName() + " deleted a repair " + id)
                                .build()
                );
            });
        }
        return "redirect:/repairs";
    }

}
