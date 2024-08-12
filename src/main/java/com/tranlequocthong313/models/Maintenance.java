/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author tranlequocthong313
 */
@Entity
@Table(name = "maintenance")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Maintenance.findAll", query = "SELECT m FROM Maintenance m"),
	@NamedQuery(name = "Maintenance.findById", query = "SELECT m FROM Maintenance m WHERE m.id = :id"),
	@NamedQuery(name = "Maintenance.findBySummary", query = "SELECT m FROM Maintenance m WHERE m.summary = :summary"),
	@NamedQuery(name = "Maintenance.findByDescription", query = "SELECT m FROM Maintenance m WHERE m.description = :description"),
	@NamedQuery(name = "Maintenance.findByImage", query = "SELECT m FROM Maintenance m WHERE m.image = :image"),
	@NamedQuery(name = "Maintenance.findByLink", query = "SELECT m FROM Maintenance m WHERE m.link = :link"),
	@NamedQuery(name = "Maintenance.findByRepeatEvery", query = "SELECT m FROM Maintenance m WHERE m.repeatEvery = :repeatEvery"),
	@NamedQuery(name = "Maintenance.findByAllDay", query = "SELECT m FROM Maintenance m WHERE m.allDay = :allDay"),
	@NamedQuery(name = "Maintenance.findByEndDateRecurrence", query = "SELECT m FROM Maintenance m WHERE m.endDateRecurrence = :endDateRecurrence"),
	@NamedQuery(name = "Maintenance.findByOccurrenceCount", query = "SELECT m FROM Maintenance m WHERE m.occurrenceCount = :occurrenceCount"),
	@NamedQuery(name = "Maintenance.findByCreatedAt", query = "SELECT m FROM Maintenance m WHERE m.createdAt = :createdAt"),
	@NamedQuery(name = "Maintenance.findByUpdatedAt", query = "SELECT m FROM Maintenance m WHERE m.updatedAt = :updatedAt"),
	@NamedQuery(name = "Maintenance.findByType", query = "SELECT m FROM Maintenance m WHERE m.type = :type"),
	@NamedQuery(name = "Maintenance.findByRecurrenceType", query = "SELECT m FROM Maintenance m WHERE m.recurrenceType = :recurrenceType"),
	@NamedQuery(name = "Maintenance.findByEndRecurrenceType", query = "SELECT m FROM Maintenance m WHERE m.endRecurrenceType = :endRecurrenceType")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maintenance implements Serializable {

	public enum Type {
		PREVENTIVE,
		CORRECTIVE,
		PREDICTIVE,
		CONDITION_BASED,
		EMERGENCY;
	}

	public enum RecurrenceType {
		NONE,
		DAILY,
		WEEKLY,
		MONTHLY,
		YEARLY
	}

	public enum EndRecurrenceType {
		NEVER,
		ON_DATE,
		AFTER_OCCURRENCES;
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 255)
	private String summary;
	@Size(max = 255)
	private String description;
	@Size(max = 255)
	private String image;
	@Size(max = 100)
	private String link;
	@Column(name = "repeat_every")
	private Integer repeatEvery;
	@Column(name = "start_date_time")
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startDateTime;
	@Column(name = "end_date_time")
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date endDateTime;
	@Column(name = "all_day")
	private Boolean allDay;
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	@Column(name = "end_date_recurrence")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDateRecurrence;
	@Column(name = "occurrence_count")
	private Integer occurrenceCount;
	@Basic(optional = false)
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	@Basic(optional = false)
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(name = "recurrence_type")
	@Enumerated(EnumType.STRING)
	private RecurrenceType recurrenceType;
	@Column(name = "end_recurrence_type")
	@Enumerated(EnumType.STRING)
	private EndRecurrenceType endRecurrenceType;
	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;
	@JoinColumn(name = "device_type_id", referencedColumnName = "id")
	@ManyToOne
	private DeviceType deviceType;
	@ManyToMany
	@JoinTable(
		name = "days_of_week_maintenance",
		joinColumns = @JoinColumn(name = "maintenance_id"),
		inverseJoinColumns = @JoinColumn(name = "days_of_week_id")
	)
	private Set<DaysOfWeek> daysOfWeekSet = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "user_maintenance",
		joinColumns = @JoinColumn(name = "maintenance_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> userSet = new HashSet<>();

	public Maintenance(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getRepeatEvery() {
		return repeatEvery;
	}

	public void setRepeatEvery(Integer repeatEvery) {
		this.repeatEvery = repeatEvery;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public Date getEndDateRecurrence() {
		return endDateRecurrence;
	}

	public void setEndDateRecurrence(Date endDateRecurrence) {
		this.endDateRecurrence = endDateRecurrence;
	}

	public Integer getOccurrenceCount() {
		return occurrenceCount;
	}

	public void setOccurrenceCount(Integer occurrenceCount) {
		this.occurrenceCount = occurrenceCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public RecurrenceType getRecurrenceType() {
		return recurrenceType;
	}

	public void setRecurrenceType(RecurrenceType recurrenceType) {
		this.recurrenceType = recurrenceType;
	}

	public EndRecurrenceType getEndRecurrenceType() {
		return endRecurrenceType;
	}

	public void setEndRecurrenceType(EndRecurrenceType endRecurrenceType) {
		this.endRecurrenceType = endRecurrenceType;
	}

	public Set<DaysOfWeek> getDaysOfWeekSet() {
		return daysOfWeekSet;
	}

	public void setDaysOfWeekSet(Set<DaysOfWeek> daysOfWeekSet) {
		this.daysOfWeekSet = daysOfWeekSet;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Maintenance)) {
			return false;
		}
		Maintenance other = (Maintenance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Maintenance[ id=" + id + " ]";
	}
}
