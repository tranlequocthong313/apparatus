/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "device_category")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "DeviceCategory.findAll", query = "SELECT d FROM DeviceCategory d"),
	@NamedQuery(name = "DeviceCategory.findById", query = "SELECT d FROM DeviceCategory d WHERE d.id = :id"),
	@NamedQuery(name = "DeviceCategory.findByName", query = "SELECT d FROM DeviceCategory d WHERE d.name = :name"),
	@NamedQuery(name = "DeviceCategory.findByModel", query = "SELECT d FROM DeviceCategory d WHERE d.model = :model"),
	@NamedQuery(name = "DeviceCategory.findByProducer", query = "SELECT d FROM DeviceCategory d WHERE d.producer = :producer"),
	@NamedQuery(name = "DeviceCategory.findByOrigin", query = "SELECT d FROM DeviceCategory d WHERE d.origin = :origin"),
	@NamedQuery(name = "DeviceCategory.findByImage", query = "SELECT d FROM DeviceCategory d WHERE d.image = :image"),
	@NamedQuery(name = "DeviceCategory.findByCreatedAt", query = "SELECT d FROM DeviceCategory d WHERE d.createdAt = :createdAt"),
	@NamedQuery(name = "DeviceCategory.findByUpdatedAt", query = "SELECT d FROM DeviceCategory d WHERE d.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"deviceSpecificationSet", "deviceSet"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 100)
	private String name;
	@Size(max = 45)
	private String model;
	@Size(max = 45)
	private String producer;
	@Size(max = 45)
	private String origin;
	@Size(max = 255)
	private String image;
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
	@JoinColumn(name = "device_type_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private DeviceType deviceType;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceCategory")
	private Set<DeviceSpecification> deviceSpecificationSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceCategory")
	private Set<Device> deviceSet;

	public DeviceCategory(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@XmlTransient
	public Set<DeviceSpecification> getDeviceSpecificationSet() {
		return deviceSpecificationSet;
	}

	public void setDeviceSpecificationSet(Set<DeviceSpecification> deviceSpecificationSet) {
		this.deviceSpecificationSet = deviceSpecificationSet;
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
		if (!(object instanceof DeviceCategory)) {
			return false;
		}
		DeviceCategory other = (DeviceCategory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.DeviceCategory[ id=" + id + " ]";
	}
}
