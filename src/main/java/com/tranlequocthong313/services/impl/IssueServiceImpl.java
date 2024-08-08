/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.tranlequocthong313.dto.IssueDto;
import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.services.IssueService;
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
public class IssueServiceImpl implements IssueService {

    @Autowired
    private BaseRepository<Issue, Integer> issueRepository;

    @Override
    public List<IssueDto> findAll(Map<String, String> queryParams) {
        List<Issue> issues = issueRepository.findAll(queryParams);
        return issues.stream().map(issue -> mapToIssueDto(issue)).collect(Collectors.toList());
    }

    private IssueDto mapToIssueDto(Issue issue) {
        return IssueDto.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .occurredAt(issue.getOccurredAt())
                .image(issue.getImage())
                .cost(issue.getCost())
                .severity(issue.getSeverity())
                .done(issue.getDone())
                .note(issue.getNote())
                .device(issue.getDevice())
                .user(issue.getUser())
                .createdAt(issue.getCreatedAt())
                .updatedAt(issue.getUpdatedAt())
                .build();
    }

    @Override
    public IssueDto findById(Integer id) {
        return mapToIssueDto(issueRepository.getReferenceById(id));
    }

    @Override
    public void delete(int id) {
        issueRepository.delete(id);
    }

    @Override
    public void update(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public void save(Issue issue, MultipartFile image) {
        issueRepository.save(issue);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        return issueRepository.count(queryParams);
    }

    @Override
    public void update(IssueDto issue, MultipartFile image) {
        issueRepository.save(mapToIssue(issue));
    }

    @Override
    public void save(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public Issue update(IssueDto issueDto) {
        Issue issue = mapToIssue(issueDto);
        issueRepository.save(issue);
        return issue;
    }

    private Issue mapToIssue(IssueDto issueDto) {
        return Issue.builder()
                .id(issueDto.getId())
                .title(issueDto.getTitle())
                .description(issueDto.getDescription())
                .occurredAt(issueDto.getOccurredAt())
                .image(issueDto.getImage())
                .cost(issueDto.getCost())
                .severity(issueDto.getSeverity())
                .done(issueDto.getDone())
                .note(issueDto.getNote())
                .device(issueDto.getDevice())
                .user(issueDto.getUser())
                .createdAt(issueDto.getCreatedAt())
                .updatedAt(issueDto.getUpdatedAt())
                .build();
    }
}
