/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "days_of_week")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "DaysOfWeek.findAll", query = "SELECT d FROM DaysOfWeek d"),
	@NamedQuery(name = "DaysOfWeek.findById", query = "SELECT d FROM DaysOfWeek d WHERE d.id = :id"),
	@NamedQuery(name = "DaysOfWeek.findByDay", query = "SELECT d FROM DaysOfWeek d WHERE d.day = :day")})
public class DaysOfWeek implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @NotNull
	private Integer id;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 9)
	private String day;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "daysOfWeek")
	private DaysOfWeekMaintenance daysOfWeekMaintenance;

	public DaysOfWeek() {
	}

	public DaysOfWeek(Integer id) {
		this.id = id;
	}

	public DaysOfWeek(Integer id, String day) {
		this.id = id;
		this.day = day;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public DaysOfWeekMaintenance getDaysOfWeekMaintenance() {
		return daysOfWeekMaintenance;
	}

	public void setDaysOfWeekMaintenance(DaysOfWeekMaintenance daysOfWeekMaintenance) {
		this.daysOfWeekMaintenance = daysOfWeekMaintenance;
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
		if (!(object instanceof DaysOfWeek)) {
			return false;
		}
		DaysOfWeek other = (DaysOfWeek) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.DaysOfWeek[ id=" + id + " ]";
	}
	
}
