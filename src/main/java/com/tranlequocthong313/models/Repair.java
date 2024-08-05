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
import javax.persistence.Lob;
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
@Table(name = "repair")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Repair.findAll", query = "SELECT r FROM Repair r"),
	@NamedQuery(name = "Repair.findById", query = "SELECT r FROM Repair r WHERE r.id = :id"),
	@NamedQuery(name = "Repair.findByReceptionDate", query = "SELECT r FROM Repair r WHERE r.receptionDate = :receptionDate"),
	@NamedQuery(name = "Repair.findByCompletedDate", query = "SELECT r FROM Repair r WHERE r.completedDate = :completedDate"),
	@NamedQuery(name = "Repair.findByImage", query = "SELECT r FROM Repair r WHERE r.image = :image"),
	@NamedQuery(name = "Repair.findByCost", query = "SELECT r FROM Repair r WHERE r.cost = :cost"),
	@NamedQuery(name = "Repair.findByRepairedBy", query = "SELECT r FROM Repair r WHERE r.repairedBy = :repairedBy"),
	@NamedQuery(name = "Repair.findByCreatedAt", query = "SELECT r FROM Repair r WHERE r.createdAt = :createdAt"),
	@NamedQuery(name = "Repair.findByUpdatedAt", query = "SELECT r FROM Repair r WHERE r.updatedAt = :updatedAt")})
public class Repair implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "reception_date")
	@Temporal(TemporalType.DATE)
	private Date receptionDate;
	@Basic(optional = false)
	@NotNull
	@Column(name = "completed_date")
	@Temporal(TemporalType.DATE)
	private Date completedDate;
	@Lob
	@Size(max = 65535)
	private String note;
	@Size(max = 255)
	private String image;
	@Basic(optional = false)
	@NotNull
	private long cost;
	@Size(max = 8)
	@Column(name = "repaired_by")
	private String repairedBy;
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
	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;
	@JoinColumn(name = "created_by_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "repair")
	private RepairCategoryRepair repairCategoryRepair;

	public Repair() {
	}

	public Repair(Integer id) {
		this.id = id;
	}

	public Repair(Integer id, Date receptionDate, Date completedDate, long cost, Date createdAt, Date updatedAt) {
		this.id = id;
		this.receptionDate = receptionDate;
		this.completedDate = completedDate;
		this.cost = cost;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public String getRepairedBy() {
		return repairedBy;
	}

	public void setRepairedBy(String repairedBy) {
		this.repairedBy = repairedBy;
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

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RepairCategoryRepair getRepairCategoryRepair() {
		return repairCategoryRepair;
	}

	public void setRepairCategoryRepair(RepairCategoryRepair repairCategoryRepair) {
		this.repairCategoryRepair = repairCategoryRepair;
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
		if (!(object instanceof Repair)) {
			return false;
		}
		Repair other = (Repair) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Repair[ id=" + id + " ]";
	}

}
