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
@Table(name = "issue")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
        @NamedQuery(name = "Issue.findById", query = "SELECT i FROM Issue i WHERE i.id = :id"),
        @NamedQuery(name = "Issue.findByTitle", query = "SELECT i FROM Issue i WHERE i.title = :title"),
        @NamedQuery(name = "Issue.findByOccurredAt", query = "SELECT i FROM Issue i WHERE i.occurredAt = :occurredAt"),
        @NamedQuery(name = "Issue.findByImage", query = "SELECT i FROM Issue i WHERE i.image = :image"),
        @NamedQuery(name = "Issue.findByCost", query = "SELECT i FROM Issue i WHERE i.cost = :cost"),
        @NamedQuery(name = "Issue.findBySeverity", query = "SELECT i FROM Issue i WHERE i.severity = :severity"),
        @NamedQuery(name = "Issue.findByDone", query = "SELECT i FROM Issue i WHERE i.done = :done"),
        @NamedQuery(name = "Issue.findByCreatedAt", query = "SELECT i FROM Issue i WHERE i.createdAt = :createdAt"),
        @NamedQuery(name = "Issue.findByUpdatedAt", query = "SELECT i FROM Issue i WHERE i.updatedAt = :updatedAt"),
        @NamedQuery(name = "Issue.findByDevice", query = "SELECT i FROM Issue i WHERE i.device = :device")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue implements Serializable {

    public enum Severity {
        LOW,
        MEDIUM,
        HIGH;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 45)
    private String title;
    @Lob
    @Size(max = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "occurred_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date occurredAt;
    @Column(name = "resolved_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date resolvedAt;
    @Size(max = 255)
    private String image;
    private Long cost;
    @Enumerated(EnumType.STRING)
    private Severity severity;
    @Basic(optional = false)
    @NotNull
    private boolean done;
    @Lob
    @Size(max = 65535)
    private String note;
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
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Device device;
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public Issue(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Date occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Date getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Date fixedAt) {
        this.resolvedAt = fixedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tranlequocthong313.models.Issue[ id=" + id + " ]";
    }

}
