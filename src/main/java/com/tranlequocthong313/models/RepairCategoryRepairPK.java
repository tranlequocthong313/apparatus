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
public class RepairCategoryRepairPK implements Serializable {

	@Basic(optional = false)
        @NotNull
        @Column(name = "repair_id")
	private int repairId;
	@Basic(optional = false)
        @Column(name = "repair_category_id")
	private int repairCategoryId;

	public RepairCategoryRepairPK() {
	}

	public RepairCategoryRepairPK(int repairId, int repairCategoryId) {
		this.repairId = repairId;
		this.repairCategoryId = repairCategoryId;
	}

	public int getRepairId() {
		return repairId;
	}

	public void setRepairId(int repairId) {
		this.repairId = repairId;
	}

	public int getRepairCategoryId() {
		return repairCategoryId;
	}

	public void setRepairCategoryId(int repairCategoryId) {
		this.repairCategoryId = repairCategoryId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) repairId;
		hash += (int) repairCategoryId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RepairCategoryRepairPK)) {
			return false;
		}
		RepairCategoryRepairPK other = (RepairCategoryRepairPK) object;
		if (this.repairId != other.repairId) {
			return false;
		}
		if (this.repairCategoryId != other.repairCategoryId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.RepairCategoryRepairPK[ repairId=" + repairId + ", repairCategoryId=" + repairCategoryId + " ]";
	}
	
}
