/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class RepairDto {
    private Integer id;
    private Date receptionDate;
    private Date completedDate;
    private String note;
    private String image;
    private long cost;
    private Repair.RepairedBy repairedBy;
    private Date createdAt;
    private Date updatedAt;
    private Device device;
    private Issue issue;
    private User user;
    private RepairCategory repairCategorySet;
}
