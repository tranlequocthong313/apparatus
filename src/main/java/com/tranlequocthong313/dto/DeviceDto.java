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
public class DeviceDto {
    private String id;
    private String serial;
    private Date dateStartedOperation;
    private Date dateOfManufacture;
    private String image;
    private long price;
    private Date dateOfPurchase;
    private short warrantyPeriod;
    private String link;
    private short yearOfDepreciation;
    private Device.Status status;
    private String note;
    private Date createdAt;
    private Date updatedAt;
    private DeviceCategory deviceCategory;
    private Location location;
    private LocationDetail locationDetail;
    private Provider provider;
    private User user;
}
