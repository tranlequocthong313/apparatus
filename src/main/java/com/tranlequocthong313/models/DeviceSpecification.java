/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "device_specification")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "DeviceSpecification.findAll", query = "SELECT d FROM DeviceSpecification d"),
	@NamedQuery(name = "DeviceSpecification.findById", query = "SELECT d FROM DeviceSpecification d WHERE d.id = :id"),
	@NamedQuery(name = "DeviceSpecification.findByName", query = "SELECT d FROM DeviceSpecification d WHERE d.name = :name"),
	@NamedQuery(name = "DeviceSpecification.findByValue", query = "SELECT d FROM DeviceSpecification d WHERE d.value = :value")})
public class DeviceSpecification implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 45)
	private String name;
	@Size(max = 45)
	private String value;
	@JoinColumn(name = "device_category_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private DeviceCategory deviceCategory;

	public DeviceSpecification() {
	}

	public DeviceSpecification(Integer id) {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(DeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
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
		if (!(object instanceof DeviceSpecification)) {
			return false;
		}
		DeviceSpecification other = (DeviceSpecification) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.DeviceSpecification[ id=" + id + " ]";
	}

}
