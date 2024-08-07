package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.ProviderDto;
import com.tranlequocthong313.models.Provider;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProviderService {
    List<ProviderDto> findAll(Map<String, String> queryParams);

    ProviderDto findById(Integer id);

    Provider update(ProviderDto threadDto);

    void save(Provider thread);

    void delete(int id);

    void update(Provider provider);

    void save(Provider provider, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(ProviderDto provider, MultipartFile image);

    default List<ProviderDto> findAll() {
        return findAll(null);
    }

}
