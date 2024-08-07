/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Location;
import com.tranlequocthong313.models.LocationDetail;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class LocationHistoryDto {

    private Integer id;
    private Date dateOfMoving;
    private String note;
    private Device device;
    private Location location;
    private LocationDetail locationDetail;
    private Date createdAt;
    private Date updatedAt;
}
