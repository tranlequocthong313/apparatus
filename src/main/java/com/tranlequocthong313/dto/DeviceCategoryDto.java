/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.DeviceType;
import com.tranlequocthong313.models.ThreadCategory;
import com.tranlequocthong313.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class DeviceCategoryDto {

    private Integer id;
    private String name;
    private String model;
    private String producer;
    private String origin;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private DeviceType deviceType;
}
