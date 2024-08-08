/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "location_history")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LocationHistory.findAll", query = "SELECT l FROM LocationHistory l"),
        @NamedQuery(name = "LocationHistory.findById", query = "SELECT l FROM LocationHistory l WHERE l.id = :id"),
        @NamedQuery(name = "LocationHistory.findByDateOfMoving", query = "SELECT l FROM LocationHistory l WHERE l.dateOfMoving = :dateOfMoving"),
        @NamedQuery(name = "LocationHistory.findByCreatedAt", query = "SELECT l FROM LocationHistory l WHERE l.createdAt = :createdAt"),
        @NamedQuery(name = "LocationHistory.findByUpdatedAt", query = "SELECT l FROM LocationHistory l WHERE l.updatedAt = :updatedAt")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_moving")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfMoving;
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
    @Lob
    @Size(max = 65535)
    private String note;
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Device device;
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Location location;
    @JoinColumn(name = "location_detail_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private LocationDetail locationDetail;

    public LocationHistory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfMoving() {
        return dateOfMoving;
    }

    public void setDateOfMoving(Date dateOfMoving) {
        this.dateOfMoving = dateOfMoving;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationDetail getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(LocationDetail locationDetail) {
        this.locationDetail = locationDetail;
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
        if (!(object instanceof LocationHistory)) {
            return false;
        }
        LocationHistory other = (LocationHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tranlequocthong313.models.LocationHistory[ id=" + id + " ]";
    }

}
