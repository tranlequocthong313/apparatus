/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author tranlequocthong313
 */
@Entity
@Table(name = "repair_category")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RepairCategory.findAll", query = "SELECT r FROM RepairCategory r"),
        @NamedQuery(name = "RepairCategory.findById", query = "SELECT r FROM RepairCategory r WHERE r.id = :id"),
        @NamedQuery(name = "RepairCategory.findByName", query = "SELECT r FROM RepairCategory r WHERE r.name = :name")})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"repairSet"})
public class RepairCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 45)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "repairCategorySet")
    Set<Repair> repairSet = new HashSet<>();

    public RepairCategory(Integer id) {
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

    public Set<Repair> getRepairSet() {
        return repairSet;
    }

    public void setRepairSet(Set<Repair> repairSet) {
        this.repairSet = repairSet;
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
        if (!(object instanceof RepairCategory)) {
            return false;
        }
        RepairCategory other = (RepairCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tranlequocthong313.models.RepairCategory[ id=" + id + " ]";
    }

}
