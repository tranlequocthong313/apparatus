/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "days_of_week_maintenance")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "DaysOfWeekMaintenance.findAll", query = "SELECT d FROM DaysOfWeekMaintenance d"),
	@NamedQuery(name = "DaysOfWeekMaintenance.findByDaysOfWeekId", query = "SELECT d FROM DaysOfWeekMaintenance d WHERE d.daysOfWeekMaintenancePK.daysOfWeekId = :daysOfWeekId"),
	@NamedQuery(name = "DaysOfWeekMaintenance.findByMaintenanceId", query = "SELECT d FROM DaysOfWeekMaintenance d WHERE d.daysOfWeekMaintenancePK.maintenanceId = :maintenanceId")})
public class DaysOfWeekMaintenance implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected DaysOfWeekMaintenancePK daysOfWeekMaintenancePK;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "days_of_week_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private DaysOfWeek daysOfWeek;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "maintenance_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Maintenance maintenance;

	public DaysOfWeekMaintenance() {
	}

	public DaysOfWeekMaintenance(DaysOfWeekMaintenancePK daysOfWeekMaintenancePK) {
		this.daysOfWeekMaintenancePK = daysOfWeekMaintenancePK;
	}

	public DaysOfWeekMaintenance(int daysOfWeekId, int maintenanceId) {
		this.daysOfWeekMaintenancePK = new DaysOfWeekMaintenancePK(daysOfWeekId, maintenanceId);
	}

	public DaysOfWeekMaintenancePK getDaysOfWeekMaintenancePK() {
		return daysOfWeekMaintenancePK;
	}

	public void setDaysOfWeekMaintenancePK(DaysOfWeekMaintenancePK daysOfWeekMaintenancePK) {
		this.daysOfWeekMaintenancePK = daysOfWeekMaintenancePK;
	}

	public DaysOfWeek getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (daysOfWeekMaintenancePK != null ? daysOfWeekMaintenancePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DaysOfWeekMaintenance)) {
			return false;
		}
		DaysOfWeekMaintenance other = (DaysOfWeekMaintenance) object;
		if ((this.daysOfWeekMaintenancePK == null && other.daysOfWeekMaintenancePK != null) || (this.daysOfWeekMaintenancePK != null && !this.daysOfWeekMaintenancePK.equals(other.daysOfWeekMaintenancePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.DaysOfWeekMaintenance[ daysOfWeekMaintenancePK=" + daysOfWeekMaintenancePK + " ]";
	}

}
