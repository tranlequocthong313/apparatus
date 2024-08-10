/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repair implements Serializable {

    public enum RepairedBy {
        INTERNAL,
        EXTERNAL;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reception_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receptionDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "completed_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date completedDate;
    @Lob
    @Size(max = 65535)
    private String note;
    @Size(max = 255)
    private String image;
    @Basic(optional = false)
    @NotNull
    private long cost;
    @Column(name = "repaired_by")
    @Enumerated(EnumType.STRING)
    private RepairedBy repairedBy;
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
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    @ManyToOne
    private Issue issue;
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
    @ManyToMany
    @JoinTable(
            name = "repair_category_repair",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "repair_category_id")
    )
    private Set<RepairCategory> repairCategorySet = new HashSet<>();

    public Repair(Integer id) {
        this.id = id;
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

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    public RepairedBy getRepairedBy() {
        return repairedBy;
    }

    public void setRepairedBy(RepairedBy repairedBy) {
        this.repairedBy = repairedBy;
    }

    public Set<RepairCategory> getRepairCategorySet() {
        return repairCategorySet;
    }

    public void setRepairCategorySet(Set<RepairCategory> repairCategorySet) {
        this.repairCategorySet = repairCategorySet;
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
