/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.MaintenanceDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.models.Maintenance;
import com.tranlequocthong313.models.User;
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
@RequestMapping("/maintenances")
public class MaintenanceController {

	@Autowired
	private MaintenanceService maintenanceService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DaysOfWeekService daysOfWeekService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private GoogleCalendarService googleCalendarService;
	@Autowired
	private Utils utils;

	@GetMapping
	public String getMaintenances(@RequestParam Map<String, String> queryParams, Model model) {
		int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
		int totalPages = (int) Math.ceil((double) maintenanceService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
		model.addAttribute("maintenances", maintenanceService.findAll(queryParams));
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
		model.addAttribute("type", queryParams.getOrDefault("type", ""));
		model.addAttribute("recurrence", queryParams.getOrDefault("recurrence", ""));
		model.addAttribute("endrecurrence", queryParams.getOrDefault("endrecurrence", ""));
		model.addAttribute("devicetype", queryParams.getOrDefault("devicetype", ""));
		model.addAttribute("location", queryParams.getOrDefault("location", ""));
		model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
		model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
		model.addAttribute("deviceTypes", deviceTypeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("types", utils.getNames(Maintenance.Type.class));
		model.addAttribute("recurrences", utils.getNames(Maintenance.RecurrenceType.class));
		model.addAttribute("endRecurrences", utils.getNames(Maintenance.EndRecurrenceType.class));
		return "maintenances";
	}

	@GetMapping("/{id}")
	public String getMaintenance(@PathVariable(value = "id") Integer id, Model model) {
		model.addAttribute("maintenance", maintenanceService.findById(id));
		return "maintenance";
	}

	@GetMapping("/device/{deviceId}/create")
	public String createMaintenanceFormForDevice(Model model, @PathVariable(value = "deviceId") String deviceId) {
		model.addAttribute("users", userService.findByRoles(new User.UserRole[]{User.UserRole.ROLE_ADMIN, User.UserRole.ROLE_WORKER}));
		model.addAttribute("daysOfWeeks", daysOfWeekService.findAll());
		model.addAttribute("types", utils.getNames(Maintenance.Type.class));
		model.addAttribute("recurrences", utils.getNames(Maintenance.RecurrenceType.class));
		model.addAttribute("endRecurrences", utils.getNames(Maintenance.EndRecurrenceType.class));
		Maintenance maintenance = new Maintenance();
		maintenance.setDevice(deviceService.mapToDevice(deviceService.findById(deviceId)));
		model.addAttribute("maintenance", maintenance);
		return "maintenance-create";
	}

	@PostMapping("/create")
	public String createMaintenance(@Valid @ModelAttribute("maintenance") Maintenance maintenance,
									BindingResult result,
									Model model,
									@RequestPart(value = "img", required = false) MultipartFile image) {
		if (result.hasErrors()) {
			model.addAttribute("users", userService.findByRoles(new User.UserRole[]{User.UserRole.ROLE_ADMIN, User.UserRole.ROLE_WORKER}));
			model.addAttribute("daysOfWeeks", daysOfWeekService.findAll());
			model.addAttribute("types", utils.getNames(Maintenance.Type.class));
			model.addAttribute("recurrences", utils.getNames(Maintenance.RecurrenceType.class));
			model.addAttribute("endRecurrences", utils.getNames(Maintenance.EndRecurrenceType.class));
			model.addAttribute("maintenance", maintenance);
			return "maintenance-create";
		}
		maintenanceService.save(maintenance, image);
		googleCalendarService.createEvent(maintenance);
		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " created a new maintenance " + maintenance.getId())
				.build()
		);
		return "redirect:/maintenances";
	}

	@GetMapping("/{id}/update")
	public String updateMaintenanceForm(@PathVariable("id") int maintenanceId, Model model) {
		model.addAttribute("users", userService.findByRoles(new User.UserRole[]{User.UserRole.ROLE_ADMIN, User.UserRole.ROLE_WORKER}));
		model.addAttribute("daysOfWeeks", daysOfWeekService.findAll());
		model.addAttribute("types", utils.getNames(Maintenance.Type.class));
		model.addAttribute("recurrences", utils.getNames(Maintenance.RecurrenceType.class));
		model.addAttribute("endRecurrences", utils.getNames(Maintenance.EndRecurrenceType.class));
		model.addAttribute("maintenance", maintenanceService.findById(maintenanceId));
		return "maintenance-update";
	}

	@PostMapping(path = "/{id}/update")
	public String updateMaintenance(
		@Valid @ModelAttribute Maintenance maintenance,
		@PathVariable(value = "id") int id,
		@RequestPart(value = "img", required = false) MultipartFile image,
		BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("daysOfWeeks", daysOfWeekService.findAll());
			model.addAttribute("types", utils.getNames(Maintenance.Type.class));
			model.addAttribute("recurrences", utils.getNames(Maintenance.RecurrenceType.class));
			model.addAttribute("endRecurrences", utils.getNames(Maintenance.EndRecurrenceType.class));
			model.addAttribute("maintenance", maintenance);
			return "maintenance-update";
		}
		MaintenanceDto maintenanceDto = maintenanceService.findById(id);
		maintenanceDto.setSummary(maintenance.getSummary());
		maintenanceDto.setDescription(maintenance.getDescription());
		maintenanceDto.setLink(maintenance.getLink());
		maintenanceDto.setRepeatEvery(maintenance.getRepeatEvery());
		maintenanceDto.setStartDate(maintenance.getStartDate());
		maintenanceDto.setEndDate(maintenance.getEndDate());
		maintenanceDto.setStartDateTime(maintenance.getStartDateTime());
		maintenanceDto.setEndDateTime(maintenance.getEndDateTime());
		maintenanceDto.setAllDay(maintenance.getAllDay());
		maintenanceDto.setEndDateRecurrence(maintenance.getEndDateRecurrence());
		maintenanceDto.setOccurrenceCount(maintenance.getOccurrenceCount());
		maintenanceDto.setType(maintenance.getType());
		maintenanceDto.setRecurrenceType(maintenance.getRecurrenceType());
		maintenanceDto.setEndRecurrenceType(maintenance.getEndRecurrenceType());
		maintenanceDto.setDevice(maintenance.getDevice());
		maintenanceDto.setDeviceType(maintenance.getDeviceType());
		maintenanceService.update(maintenanceDto, image);
		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " updated a maintenance " + maintenance.getId())
				.build()
		);
		return "redirect:/maintenances";
	}

	@GetMapping("/{id}/delete")
	public String deleteMaintenance(@PathVariable(value = "id") int id) {
		maintenanceService.delete(id);
		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " deleted a maintenance " + id)
				.build()
		);
		return "redirect:/maintenances";
	}

	@PostMapping("/bulk-action")
	public String bulkActionMaintenance(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
		if (action.equals("delete")) {
			Arrays.stream(selectedIds).forEach(id -> {
				maintenanceService.delete(Integer.parseInt(id));
				activityLogService.save(
					ActivityLog.builder()
						.user(userService.getCurrentUser())
						.log(userService.getCurrentUser().getFullName() + " deleted a maintenance " + id)
						.build()
				);
			});
		}
		return "redirect:/maintenances";
	}

	@GetMapping("/schedule")
	public String getSchedule() {
		return "schedule";
	}
}
