/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
	@NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id"),
	@NamedQuery(name = "Location.findByAddress", query = "SELECT l FROM Location l WHERE l.address = :address"),
	@NamedQuery(name = "Location.findByCreatedAt", query = "SELECT l FROM Location l WHERE l.createdAt = :createdAt"),
	@NamedQuery(name = "Location.findByUpdatedAt", query = "SELECT l FROM Location l WHERE l.updatedAt = :updatedAt"),
	@NamedQuery(name = "Location.findByBuilding", query = "SELECT l FROM Location l WHERE l.building = :building")})
@JsonIgnoreProperties(value = {"locationDetailSet", "locationHistorySet", "deviceSet"})
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 45)
	private String address;
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
	@Size(max = 45)
	private String building;
	@Lob
	@Size(max = 65535)
	private String note;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private Set<LocationDetail> locationDetailSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private Set<LocationHistory> locationHistorySet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private Set<Device> deviceSet;

	public Location() {
	}

	public Location(Integer id) {
		this.id = id;
	}

	public Location(Integer id, Date createdAt, Date updatedAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@XmlTransient
	public Set<LocationDetail> getLocationDetailSet() {
		return locationDetailSet;
	}

	public void setLocationDetailSet(Set<LocationDetail> locationDetailSet) {
		this.locationDetailSet = locationDetailSet;
	}

	@XmlTransient
	public Set<LocationHistory> getLocationHistorySet() {
		return locationHistorySet;
	}

	public void setLocationHistorySet(Set<LocationHistory> locationHistorySet) {
		this.locationHistorySet = locationHistorySet;
	}

	@XmlTransient
	public Set<Device> getDeviceSet() {
		return deviceSet;
	}

	public void setDeviceSet(Set<Device> deviceSet) {
		this.deviceSet = deviceSet;
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
		if (!(object instanceof Location)) {
			return false;
		}
		Location other = (Location) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Location[ id=" + id + " ]";
	}
}
