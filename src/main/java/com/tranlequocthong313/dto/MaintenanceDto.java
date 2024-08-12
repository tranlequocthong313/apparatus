/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.tranlequocthong313.models.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class MaintenanceDto {
	private Integer id;
	private String summary;
	private String description;
	private String image;
	private String link;
	private Integer repeatEvery;
	private Date endDateRecurrence;
	private Integer occurrenceCount;
	private Date startDateTime;
	private Date endDateTime;
	private Boolean allDay;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;
	private Maintenance.Type type;
	private Maintenance.RecurrenceType recurrenceType;
	private Maintenance.EndRecurrenceType endRecurrenceType;
	private Device device;
	private DeviceType deviceType;
	private Set<User> userSet;
}
