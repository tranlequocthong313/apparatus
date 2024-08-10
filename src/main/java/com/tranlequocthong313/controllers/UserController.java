/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.tranlequocthong313.dto.UserDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.services.ActivityLogService;
import com.tranlequocthong313.services.UserService;
import com.tranlequocthong313.services.UserService;
import com.tranlequocthong313.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tranlequocthong313
 */
@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private Utils utils;

	@GetMapping("/login")
	public String login(Locale locale, Model model) {
		return "login";
	}

	@GetMapping
	public String getUsers(@RequestParam Map<String, String> queryParams, Model model) {
		int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
		int totalPages = (int) Math.ceil((double) userService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
		model.addAttribute("users", userService.findAll(queryParams));
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
		model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
		model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
		model.addAttribute("userRoles", utils.getNames(User.UserRole.class));
		model.addAttribute("userRole", queryParams.getOrDefault("userRole", ""));
		return "users";
	}

	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable(value = "id") int id) {
		userService.delete(id);
		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " deleted a user " + id)
				.build()
		);
		return "redirect:/users";
	}

	@PostMapping("/bulk-action")
	public String bulkActionUser(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
		if (action.equals("delete")) {
			Arrays.stream(selectedIds).forEach(id -> {
				userService.delete(Integer.parseInt(id));
				activityLogService.save(
					ActivityLog.builder()
						.user(userService.getCurrentUser())
						.log(userService.getCurrentUser().getFullName() + " deleted a user " + id)
						.build()
				);
			});
		}
		return "redirect:/users";
	}
}
