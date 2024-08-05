/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
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
	@NamedQuery(name = "Maintenance.findByStart", query = "SELECT m FROM Maintenance m WHERE m.start = :start"),
	@NamedQuery(name = "Maintenance.findByEnd", query = "SELECT m FROM Maintenance m WHERE m.end = :end"),
	@NamedQuery(name = "Maintenance.findByAllDay", query = "SELECT m FROM Maintenance m WHERE m.allDay = :allDay"),
	@NamedQuery(name = "Maintenance.findByEndDateRecurrence", query = "SELECT m FROM Maintenance m WHERE m.endDateRecurrence = :endDateRecurrence"),
	@NamedQuery(name = "Maintenance.findByOccurrenceCount", query = "SELECT m FROM Maintenance m WHERE m.occurrenceCount = :occurrenceCount"),
	@NamedQuery(name = "Maintenance.findByCreatedAt", query = "SELECT m FROM Maintenance m WHERE m.createdAt = :createdAt"),
	@NamedQuery(name = "Maintenance.findByUpdatedAt", query = "SELECT m FROM Maintenance m WHERE m.updatedAt = :updatedAt"),
	@NamedQuery(name = "Maintenance.findByType", query = "SELECT m FROM Maintenance m WHERE m.type = :type"),
	@NamedQuery(name = "Maintenance.findByRecurrenceType", query = "SELECT m FROM Maintenance m WHERE m.recurrenceType = :recurrenceType"),
	@NamedQuery(name = "Maintenance.findByEndRecurrenceType", query = "SELECT m FROM Maintenance m WHERE m.endRecurrenceType = :endRecurrenceType")})
public class Maintenance implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 50)
	private String summary;
	@Size(max = 255)
	private String description;
	@Size(max = 255)
	private String image;
	@Size(max = 100)
	private String link;
	@Column(name = "repeat_every")
	private Integer repeatEvery;
	@Basic(optional = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Basic(optional = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	@Column(name = "all_day")
	private Boolean allDay;
	@Column(name = "end_date_recurrence")
	@Temporal(TemporalType.DATE)
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
	@Size(max = 15)
	private String type;
	@Size(max = 7)
	@Column(name = "recurrence_type")
	private String recurrenceType;
	@Size(max = 17)
	@Column(name = "end_recurrence_type")
	private String endRecurrenceType;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "maintenance")
	private DaysOfWeekMaintenance daysOfWeekMaintenance;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "maintenance")
	private UserMaintenance userMaintenance;
	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;

	public Maintenance() {
	}

	public Maintenance(Integer id) {
		this.id = id;
	}

	public Maintenance(Integer id, Date start, Date end, Date createdAt, Date updatedAt) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecurrenceType() {
		return recurrenceType;
	}

	public void setRecurrenceType(String recurrenceType) {
		this.recurrenceType = recurrenceType;
	}

	public String getEndRecurrenceType() {
		return endRecurrenceType;
	}

	public void setEndRecurrenceType(String endRecurrenceType) {
		this.endRecurrenceType = endRecurrenceType;
	}

	public DaysOfWeekMaintenance getDaysOfWeekMaintenance() {
		return daysOfWeekMaintenance;
	}

	public void setDaysOfWeekMaintenance(DaysOfWeekMaintenance daysOfWeekMaintenance) {
		this.daysOfWeekMaintenance = daysOfWeekMaintenance;
	}

	public UserMaintenance getUserMaintenance() {
		return userMaintenance;
	}

	public void setUserMaintenance(UserMaintenance userMaintenance) {
		this.userMaintenance = userMaintenance;
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
