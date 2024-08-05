/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tranlequocthong313
 */
@Embeddable
public class UserMaintenancePK implements Serializable {

	@Basic(optional = false)
        @NotNull
        @Column(name = "user_id")
	private int userId;
	@Basic(optional = false)
        @NotNull
        @Column(name = "maintenance_id")
	private int maintenanceId;

	public UserMaintenancePK() {
	}

	public UserMaintenancePK(int userId, int maintenanceId) {
		this.userId = userId;
		this.maintenanceId = maintenanceId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(int maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) userId;
		hash += (int) maintenanceId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserMaintenancePK)) {
			return false;
		}
		UserMaintenancePK other = (UserMaintenancePK) object;
		if (this.userId != other.userId) {
			return false;
		}
		if (this.maintenanceId != other.maintenanceId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.UserMaintenancePK[ userId=" + userId + ", maintenanceId=" + maintenanceId + " ]";
	}
	
}
