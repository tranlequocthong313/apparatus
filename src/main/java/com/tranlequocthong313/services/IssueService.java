package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.DeviceDto;
import com.tranlequocthong313.dto.IssueDto;
import com.tranlequocthong313.models.Issue;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IssueService {
    List<IssueDto> findAll(Map<String, String> queryParams);
    IssueDto findById(Integer id);

    List<IssueDto> findByDone(Boolean done);

    Issue update(IssueDto threadDto);

    void save(Issue thread);

    void delete(int id);

    void update(Issue issue);

    void save(Issue issue, MultipartFile image);

    default Long count() {
        return count(null);
    }

    Long count(Map<String, String> queryParams);

    void update(IssueDto issue, MultipartFile image);

    default List<IssueDto> findAll() {
        return findAll(null);
    }

    default Long totalCost() {
        return totalCost(null);
    }

    Long totalCost(DeviceDto device);

    Long unresolvedDays(Map<String, String> queryParams);

    Issue mapToIssue(IssueDto issue);
}
