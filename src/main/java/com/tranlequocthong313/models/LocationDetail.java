/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author tranlequocthong313
 */
@Entity
@Table(name = "location_detail")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LocationDetail.findAll", query = "SELECT l FROM LocationDetail l"),
        @NamedQuery(name = "LocationDetail.findById", query = "SELECT l FROM LocationDetail l WHERE l.id = :id"),
        @NamedQuery(name = "LocationDetail.findByFloor", query = "SELECT l FROM LocationDetail l WHERE l.floor = :floor"),
        @NamedQuery(name = "LocationDetail.findByRoom", query = "SELECT l FROM LocationDetail l WHERE l.room = :room"),
        @NamedQuery(name = "LocationDetail.findByCreatedAt", query = "SELECT l FROM LocationDetail l WHERE l.createdAt = :createdAt"),
        @NamedQuery(name = "LocationDetail.findByUpdatedAt", query = "SELECT l FROM LocationDetail l WHERE l.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"locationHistorySet", "deviceSet"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Short floor;
    @Size(max = 50)
    private String room;
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
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Location location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationDetail")
    private Set<LocationHistory> locationHistorySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationDetail")
    private Set<Device> deviceSet;

    public LocationDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @XmlTransient
    public Set<LocationHistory> getLocationHistorySet() {
        return locationHistorySet;
    }

    public void setLocationHistorySet(Set<LocationHistory> locationHistorySet) {
        this.locationHistorySet = locationHistorySet;
    }

    @XmlTransient
    public Set<Device> getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(Set<Device> deviceSet) {
        this.deviceSet = deviceSet;
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
        if (!(object instanceof LocationDetail)) {
            return false;
        }
        LocationDetail other = (LocationDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tranlequocthong313.models.LocationDetail[ id=" + id + " ]";
    }
}
