/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Location;
import com.tranlequocthong313.models.LocationHistory;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class LocationDetailDto {

    private Integer id;
    private Short floor;
    private String room;
    private Date createdAt;
    private Date updatedAt;
    private String note;
    private Location location;
}
