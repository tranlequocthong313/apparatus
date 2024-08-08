/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class IssueDto {

    private Integer id;
    private String title;
    private String description;
    private Date occurredAt;
    private Date resolvedAt;
    private String image;
    private Long cost;
    private Issue.Severity severity;
    private boolean done;
    private String note;
    private Date createdAt;
    private Date updatedAt;
    private Device device;
    private User user;

    public boolean getDone() {
        return this.done;
    }
}
