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
public class DaysOfWeekMaintenancePK implements Serializable {

	@Basic(optional = false)
        @NotNull
        @Column(name = "days_of_week_id")
	private int daysOfWeekId;
	@Basic(optional = false)
        @NotNull
        @Column(name = "maintenance_id")
	private int maintenanceId;

	public DaysOfWeekMaintenancePK() {
	}

	public DaysOfWeekMaintenancePK(int daysOfWeekId, int maintenanceId) {
		this.daysOfWeekId = daysOfWeekId;
		this.maintenanceId = maintenanceId;
	}

	public int getDaysOfWeekId() {
		return daysOfWeekId;
	}

	public void setDaysOfWeekId(int daysOfWeekId) {
		this.daysOfWeekId = daysOfWeekId;
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
		hash += (int) daysOfWeekId;
		hash += (int) maintenanceId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DaysOfWeekMaintenancePK)) {
			return false;
		}
		DaysOfWeekMaintenancePK other = (DaysOfWeekMaintenancePK) object;
		if (this.daysOfWeekId != other.daysOfWeekId) {
			return false;
		}
		if (this.maintenanceId != other.maintenanceId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.DaysOfWeekMaintenancePK[ daysOfWeekId=" + daysOfWeekId + ", maintenanceId=" + maintenanceId + " ]";
	}
	
}
