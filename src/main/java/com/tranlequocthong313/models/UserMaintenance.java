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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "user_maintenance")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserMaintenance.findAll", query = "SELECT u FROM UserMaintenance u"),
	@NamedQuery(name = "UserMaintenance.findByUserId", query = "SELECT u FROM UserMaintenance u WHERE u.userMaintenancePK.userId = :userId"),
	@NamedQuery(name = "UserMaintenance.findByMaintenanceId", query = "SELECT u FROM UserMaintenance u WHERE u.userMaintenancePK.maintenanceId = :maintenanceId"),
	@NamedQuery(name = "UserMaintenance.findByCreatedAt", query = "SELECT u FROM UserMaintenance u WHERE u.createdAt = :createdAt"),
	@NamedQuery(name = "UserMaintenance.findByUpdatedAt", query = "SELECT u FROM UserMaintenance u WHERE u.updatedAt = :updatedAt")})
public class UserMaintenance implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected UserMaintenancePK userMaintenancePK;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "maintenance_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Maintenance maintenance;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private User user;

	public UserMaintenance() {
	}

	public UserMaintenance(UserMaintenancePK userMaintenancePK) {
		this.userMaintenancePK = userMaintenancePK;
	}

	public UserMaintenance(UserMaintenancePK userMaintenancePK, Date createdAt, Date updatedAt) {
		this.userMaintenancePK = userMaintenancePK;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserMaintenance(int userId, int maintenanceId) {
		this.userMaintenancePK = new UserMaintenancePK(userId, maintenanceId);
	}

	public UserMaintenancePK getUserMaintenancePK() {
		return userMaintenancePK;
	}

	public void setUserMaintenancePK(UserMaintenancePK userMaintenancePK) {
		this.userMaintenancePK = userMaintenancePK;
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

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userMaintenancePK != null ? userMaintenancePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserMaintenance)) {
			return false;
		}
		UserMaintenance other = (UserMaintenance) object;
		if ((this.userMaintenancePK == null && other.userMaintenancePK != null) || (this.userMaintenancePK != null && !this.userMaintenancePK.equals(other.userMaintenancePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.UserMaintenance[ userMaintenancePK=" + userMaintenancePK + " ]";
	}

}
