/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.ThreadCategoryDto;
import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.models.ThreadCategory;
import com.tranlequocthong313.services.ActivityLogService;
import com.tranlequocthong313.services.ForumCategoryService;
import com.tranlequocthong313.services.ThreadCategoryService;
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
@RequestMapping("/thread-categories")
public class ThreadCategoryController {

	@Autowired
	private ThreadCategoryService threadCategoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityLogService activityLogService;
	@Autowired
	private ForumCategoryService forumCategoryService;
	@Autowired
	private Utils utils;

	@GetMapping
	public String getThreadCategorys(@RequestParam Map<String, String> queryParams, Model model) {
		int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
		int totalPages = (int) Math.ceil((double) threadCategoryService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
		model.addAttribute("threadCategories", threadCategoryService.findAll(queryParams));
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
		model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
		model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
		model.addAttribute("forums", forumCategoryService.findAll());
		model.addAttribute("forum", queryParams.getOrDefault("forum", ""));
		return "thread-categories";
	}

	@GetMapping("/create")
	public String createThreadCategoryForm(Model model) {
		model.addAttribute("forums", forumCategoryService.findAll());
		model.addAttribute("threadCategory", new ThreadCategory());
		return "thread-category-create";
	}

	@PostMapping("/create")
	public String createThreadCategory(@Valid @ModelAttribute("threadCategory") ThreadCategory threadCategory,
									   BindingResult result,
									   Model model,
									   @RequestPart(value = "img", required = false) MultipartFile image) {
		if (result.hasErrors()) {
			model.addAttribute("forums", forumCategoryService.findAll());
			model.addAttribute("threadCategory", threadCategory);
			return "thread-category-create";
		}
		threadCategoryService.save(threadCategory, image);
		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " created a new thread category " + threadCategory.getId())
				.build()
		);
		return "redirect:/thread-categories";
	}

	@GetMapping("/{id}/update")
	public String updateThreadCategoryForm(@PathVariable("id") int threadCategoryId, Model model) {
		model.addAttribute("forums", forumCategoryService.findAll());
		model.addAttribute("threadCategory", threadCategoryService.findById(threadCategoryId));
		return "thread-category-update";
	}

	@PostMapping(path = "/{id}/update")
	public String updateThreadCategory(
		@Valid @ModelAttribute ThreadCategory threadCategory,
		@PathVariable(value = "id") int id,
		@RequestPart(value = "img", required = false) MultipartFile image,
		BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("forums", forumCategoryService.findAll());
			model.addAttribute("threadCategory", threadCategory);
			return "thread-category-update";
		}
		ThreadCategoryDto threadCategoryDto = threadCategoryService.findById(id);
		threadCategoryDto.setName(threadCategory.getName());
		threadCategoryDto.setForumCategory(threadCategory.getForumCategory());
		threadCategoryService.update(threadCategoryDto, image);

		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " updated a thread category " + id)
				.build()
		);
		return "redirect:/thread-categories";
	}

	@GetMapping("/{id}/delete")
	public String deleteThreadCategory(@PathVariable(value = "id") int id) {
		threadCategoryService.delete(id);
		activityLogService.save(
			ActivityLog.builder()
				.user(userService.getCurrentUser())
				.log(userService.getCurrentUser().getFullName() + " deleted a thread category " + id)
				.build()
		);
		return "redirect:/thread-categories";
	}

	@PostMapping("/bulk-action")
	public String bulkActionThreadCategory(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
		if (action.equals("delete")) {
			Arrays.stream(selectedIds).forEach(id -> {
				threadCategoryService.delete(Integer.parseInt(id));
				activityLogService.save(
					ActivityLog.builder()
						.user(userService.getCurrentUser())
						.log(userService.getCurrentUser().getFullName() + " deleted a thread category " + id)
						.build()
				);
			});
		}
		return "redirect:/thread-categories";
	}
}
