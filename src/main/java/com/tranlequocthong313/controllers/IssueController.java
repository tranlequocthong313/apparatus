/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.dto.IssueDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.services.ActivityLogService;
import com.tranlequocthong313.services.DeviceService;
import com.tranlequocthong313.services.IssueService;
import com.tranlequocthong313.services.UserService;
import com.tranlequocthong313.services.impl.UserServiceImpl;
import com.tranlequocthong313.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

/**
 * @author tranlequocthong313
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private Utils utils;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public String getIssues(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) issueService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("issues", issueService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        model.addAttribute("severity", queryParams.getOrDefault("severity", ""));
        model.addAttribute("severities", utils.getNames(Issue.Severity.class));
        return "issues";
    }

    @GetMapping("/{id}")
    public String getIssue(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("issue", issueService.findById(id));
        return "issue";
    }

    @GetMapping("/device/{deviceId}/create")
    public String createIssueForm(Model model, @PathVariable(value = "deviceId") String deviceId) {
        model.addAttribute("severities", utils.getNames(Issue.Severity.class));
        Issue issue = new Issue();
        issue.setDevice(deviceService.mapToDevice(deviceService.findById(deviceId)));
        model.addAttribute("issue", issue);
        return "issue-create";
    }

    @PostMapping("/create")
    public String createIssue(@Valid @ModelAttribute("issue") Issue issue,
                              BindingResult result,
                              Model model,
                              @RequestPart(value = "img", required = false) MultipartFile image) {
        if (result.hasErrors()) {
            model.addAttribute("severities", utils.getNames(Issue.Severity.class));
            model.addAttribute("issue", issue);
            return "issue-create";
        }
        issueService.save(issue, image);
        DeviceDto deviceDto = deviceService.findById(issue.getDevice().getId());
        deviceDto.setStatus(Device.Status.ERROR);
        deviceService.update(deviceDto);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " created a new issue " + issue.getId())
                        .build()
        );
        return "redirect:/issues";
    }

    @GetMapping("/{id}/update")
    public String updateIssueForm(@PathVariable("id") int issueId, Model model) {
        model.addAttribute("severities", utils.getNames(Issue.Severity.class));
        model.addAttribute("issue", issueService.findById(issueId));
        return "issue-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateIssue(
            @Valid @ModelAttribute Issue issue,
            @PathVariable(value = "id") int id,
            @RequestPart(value = "img", required = false) MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("severities", utils.getNames(Issue.Severity.class));
            model.addAttribute("issue", issue);
            return "issue-update";
        }
        IssueDto issueDto = issueService.findById(id);
        issueDto.setTitle(issue.getTitle());
        issueDto.setDescription(issue.getDescription());
        issueDto.setOccurredAt(issue.getOccurredAt());
        issueDto.setCost(issue.getCost());
        issueDto.setSeverity(issue.getSeverity());
        issueDto.setDone(issue.getDone());
        issueDto.setNote(issue.getNote());
        issueService.update(issueDto, image);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " update an issue " + id)
                        .build()
        );
        return "redirect:/issues";
    }

    @GetMapping("/{id}/delete")
    public String deleteIssue(@PathVariable(value = "id") int id) {
        issueService.delete(id);
        activityLogService.save(
                ActivityLog.builder()
                        .user(userService.getCurrentUser())
                        .log(userService.getCurrentUser().getFullName() + " deleted an issue " + id)
                        .build()
        );
        return "redirect:/issues";
    }

    @PostMapping("/bulk-action")
    public String bulkActionIssue(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> {
                issueService.delete(Integer.parseInt(id));
                activityLogService.save(
                        ActivityLog.builder()
                                .user(userService.getCurrentUser())
                                .log(userService.getCurrentUser().getFullName() + " deleted an issue " + id)
                                .build()
                );
            });
        }
        return "redirect:/issues";
    }
}
