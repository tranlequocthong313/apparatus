/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.ProviderDto;
import com.tranlequocthong313.models.Provider;
import com.tranlequocthong313.services.ProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private Utils utils;

    @GetMapping
    public String getProviders(@RequestParam Map<String, String> queryParams, Model model) {
        int page = queryParams.containsKey("page") ? Integer.parseInt(queryParams.get("page")) : 1;
        int totalPages = (int) Math.ceil((double) providerService.count(queryParams) / utils.DEFAULT_PAGE_SIZE);
        model.addAttribute("providers", providerService.findAll(queryParams));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchQuery", queryParams.getOrDefault("q", ""));
        model.addAttribute("sort", queryParams.getOrDefault("sort", "id"));
        model.addAttribute("direction", queryParams.getOrDefault("direction", "asc"));
        return "providers";
    }

    @GetMapping("/{id}")
    public String getProvider(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("provider", providerService.findById(id));
        return "provider";
    }

    @GetMapping("/create")
    public String createProviderForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider-create";
    }

    @PostMapping("/create")
    public String createProvider(@Valid @ModelAttribute("provider") Provider provider,
                                       BindingResult result,
                                       Model model,
                                       @RequestPart(value = "img", required = false) MultipartFile image) {
        if (result.hasErrors()) {
            model.addAttribute("provider", provider);
            return "provider-create";
        }
        providerService.save(provider, image);
        return "redirect:/providers";
    }

    @GetMapping("/{id}/update")
    public String updateProviderForm(@PathVariable("id") int providerId, Model model) {
        model.addAttribute("provider", providerService.findById(providerId));
        return "provider-update";
    }

    @PostMapping(path = "/{id}/update")
    public String updateProvider(
            @Valid @ModelAttribute Provider provider,
            @PathVariable(value = "id") int id,
            @RequestPart(value = "img", required = false) MultipartFile image,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("provider", provider);
            return "provider-update";
        }
        ProviderDto providerDto = providerService.findById(id);
        providerDto.setAddress(provider.getAddress());
        providerDto.setName(provider.getName());
        providerDto.setPhoneNumber(provider.getPhoneNumber());
        providerDto.setWebsite(provider.getWebsite());
        providerDto.setEmail(provider.getEmail());
        providerService.update(providerDto, image);
        return "redirect:/providers";
    }

    @GetMapping("/{id}/delete")
    public String deleteProvider(@PathVariable(value = "id") int id) {
        providerService.delete(id);
        return "redirect:/providers";
    }

    @PostMapping("/bulk-action")
    public String bulkActionProvider(@RequestParam(value = "action") String action, @RequestParam(value = "selectedIds") String[] selectedIds) {
        if (action.equals("delete")) {
            Arrays.stream(selectedIds).forEach(id -> providerService.delete(Integer.parseInt(id)));
        }
        return "redirect:/providers";
    }
}
