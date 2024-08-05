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
@Table(name = "repair_category_repair")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "RepairCategoryRepair.findAll", query = "SELECT r FROM RepairCategoryRepair r"),
	@NamedQuery(name = "RepairCategoryRepair.findByRepairId", query = "SELECT r FROM RepairCategoryRepair r WHERE r.repairCategoryRepairPK.repairId = :repairId"),
	@NamedQuery(name = "RepairCategoryRepair.findByRepairCategoryId", query = "SELECT r FROM RepairCategoryRepair r WHERE r.repairCategoryRepairPK.repairCategoryId = :repairCategoryId")})
public class RepairCategoryRepair implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected RepairCategoryRepairPK repairCategoryRepairPK;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "repair_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Repair repair;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "repair_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RepairCategory repairCategory;

	public RepairCategoryRepair() {
	}

	public RepairCategoryRepair(RepairCategoryRepairPK repairCategoryRepairPK) {
		this.repairCategoryRepairPK = repairCategoryRepairPK;
	}

	public RepairCategoryRepair(int repairId, int repairCategoryId) {
		this.repairCategoryRepairPK = new RepairCategoryRepairPK(repairId, repairCategoryId);
	}

	public RepairCategoryRepairPK getRepairCategoryRepairPK() {
		return repairCategoryRepairPK;
	}

	public void setRepairCategoryRepairPK(RepairCategoryRepairPK repairCategoryRepairPK) {
		this.repairCategoryRepairPK = repairCategoryRepairPK;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public RepairCategory getRepairCategory() {
		return repairCategory;
	}

	public void setRepairCategory(RepairCategory repairCategory) {
		this.repairCategory = repairCategory;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (repairCategoryRepairPK != null ? repairCategoryRepairPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RepairCategoryRepair)) {
			return false;
		}
		RepairCategoryRepair other = (RepairCategoryRepair) object;
		if ((this.repairCategoryRepairPK == null && other.repairCategoryRepairPK != null) || (this.repairCategoryRepairPK != null && !this.repairCategoryRepairPK.equals(other.repairCategoryRepairPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.RepairCategoryRepair[ repairCategoryRepairPK=" + repairCategoryRepairPK + " ]";
	}

}
