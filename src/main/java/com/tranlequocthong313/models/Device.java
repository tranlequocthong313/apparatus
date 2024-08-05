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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "device")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d"),
	@NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id"),
	@NamedQuery(name = "Device.findByDateStartedOperation", query = "SELECT d FROM Device d WHERE d.dateStartedOperation = :dateStartedOperation"),
	@NamedQuery(name = "Device.findByDateOfManufacture", query = "SELECT d FROM Device d WHERE d.dateOfManufacture = :dateOfManufacture"),
	@NamedQuery(name = "Device.findByImage", query = "SELECT d FROM Device d WHERE d.image = :image"),
	@NamedQuery(name = "Device.findBySerial", query = "SELECT d FROM Device d WHERE d.serial = :serial"),
	@NamedQuery(name = "Device.findByPrice", query = "SELECT d FROM Device d WHERE d.price = :price"),
	@NamedQuery(name = "Device.findByDateOfPurchase", query = "SELECT d FROM Device d WHERE d.dateOfPurchase = :dateOfPurchase"),
	@NamedQuery(name = "Device.findByWarrantyPeriod", query = "SELECT d FROM Device d WHERE d.warrantyPeriod = :warrantyPeriod"),
	@NamedQuery(name = "Device.findByLink", query = "SELECT d FROM Device d WHERE d.link = :link"),
	@NamedQuery(name = "Device.findByYearOfDepreciation", query = "SELECT d FROM Device d WHERE d.yearOfDepreciation = :yearOfDepreciation"),
	@NamedQuery(name = "Device.findByStatus", query = "SELECT d FROM Device d WHERE d.status = :status"),
	@NamedQuery(name = "Device.findByCreatedAt", query = "SELECT d FROM Device d WHERE d.createdAt = :createdAt"),
	@NamedQuery(name = "Device.findByUpdatedAt", query = "SELECT d FROM Device d WHERE d.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"repairSet", "locationHistorySet", "maintenanceSet"})
public class Device implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	private String id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_started_operation")
	@Temporal(TemporalType.DATE)
	private Date dateStartedOperation;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_of_manufacture")
	@Temporal(TemporalType.DATE)
	private Date dateOfManufacture;
	@Size(max = 255)
	private String image;
	@Size(max = 45)
	private String serial;
	@Basic(optional = false)
	@NotNull
	private long price;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_of_purchase")
	@Temporal(TemporalType.DATE)
	private Date dateOfPurchase;
	@Basic(optional = false)
	@NotNull
	@Column(name = "warranty_period")
	private short warrantyPeriod;
	@Size(max = 255)
	private String link;
	@Basic(optional = false)
	@NotNull
	@Column(name = "year_of_depreciation")
	private short yearOfDepreciation;
	@Size(max = 11)
	private String status;
	@Lob
	@Size(max = 65535)
	private String note;
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
	@OneToMany(mappedBy = "device")
	private Set<Repair> repairSet;
	@OneToMany(mappedBy = "device")
	private Set<LocationHistory> locationHistorySet;
	@JoinColumn(name = "device_category_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private DeviceCategory deviceCategory;
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Location location;
	@JoinColumn(name = "location_detail_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private LocationDetail locationDetail;
	@JoinColumn(name = "provider_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Provider provider;
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	@OneToMany(mappedBy = "device")
	private Set<Maintenance> maintenanceSet;

	public Device() {
	}

	public Device(String id) {
		this.id = id;
	}

	public Device(String id, Date dateStartedOperation, Date dateOfManufacture, long price, Date dateOfPurchase, short warrantyPeriod, short yearOfDepreciation, Date createdAt, Date updatedAt) {
		this.id = id;
		this.dateStartedOperation = dateStartedOperation;
		this.dateOfManufacture = dateOfManufacture;
		this.price = price;
		this.dateOfPurchase = dateOfPurchase;
		this.warrantyPeriod = warrantyPeriod;
		this.yearOfDepreciation = yearOfDepreciation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateStartedOperation() {
		return dateStartedOperation;
	}

	public void setDateStartedOperation(Date dateStartedOperation) {
		this.dateStartedOperation = dateStartedOperation;
	}

	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public short getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(short warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public short getYearOfDepreciation() {
		return yearOfDepreciation;
	}

	public void setYearOfDepreciation(short yearOfDepreciation) {
		this.yearOfDepreciation = yearOfDepreciation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	@XmlTransient
	public Set<Repair> getRepairSet() {
		return repairSet;
	}

	public void setRepairSet(Set<Repair> repairSet) {
		this.repairSet = repairSet;
	}

	@XmlTransient
	public Set<LocationHistory> getLocationHistorySet() {
		return locationHistorySet;
	}

	public void setLocationHistorySet(Set<LocationHistory> locationHistorySet) {
		this.locationHistorySet = locationHistorySet;
	}

	public DeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(DeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public LocationDetail getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(LocationDetail locationDetail) {
		this.locationDetail = locationDetail;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@XmlTransient
	public Set<Maintenance> getMaintenanceSet() {
		return maintenanceSet;
	}

	public void setMaintenanceSet(Set<Maintenance> maintenanceSet) {
		this.maintenanceSet = maintenanceSet;
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
		if (!(object instanceof Device)) {
			return false;
		}
		Device other = (Device) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Device[ id=" + id + " ]";
	}

}
