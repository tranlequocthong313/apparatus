/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.ProviderDto;
import com.tranlequocthong313.models.Provider;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tranlequocthong313
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private BaseRepository<Provider, Integer> providerRepository;

    @Override
    public List<ProviderDto> findAll(Map<String, String> queryParams) {
        List<Provider> providers = providerRepository.findAll(queryParams);
        return providers.stream().map(provider -> mapToProviderDto(provider)).collect(Collectors.toList());
    }

    private ProviderDto mapToProviderDto(Provider provider) {
        return ProviderDto.builder()
                .id(provider.getId())
                .address(provider.getAddress())
                .name(provider.getName())
                .phoneNumber(provider.getPhoneNumber())
                .email(provider.getEmail())
                .website(provider.getWebsite())
                .createdAt(provider.getCreatedAt())
                .updatedAt(provider.getUpdatedAt())
                .build();
    }

    @Override
    public ProviderDto findById(Integer id) {
        return mapToProviderDto(providerRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        providerRepository.delete(id);
    }

    @Override
    public void update(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public void save(Provider provider, MultipartFile image) {
        providerRepository.save(provider);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return providerRepository.count(queryParams);
    }

    @Override
    public void update(ProviderDto provider, MultipartFile image) {
        providerRepository.save(mapToProvider(provider));
    }

    @Override
    public void save(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public Provider update(ProviderDto providerDto) {
        Provider provider = mapToProvider(providerDto);
        providerRepository.save(provider);
        return provider;
    }

    private Provider mapToProvider(ProviderDto providerDto) {
        return Provider.builder()
                .id(providerDto.getId())
                .address(providerDto.getAddress())
                .name(providerDto.getName())
                .phoneNumber(providerDto.getPhoneNumber())
                .email(providerDto.getEmail())
                .website(providerDto.getWebsite())
                .createdAt(providerDto.getCreatedAt())
                .updatedAt(providerDto.getUpdatedAt())
                .build();
    }
}
