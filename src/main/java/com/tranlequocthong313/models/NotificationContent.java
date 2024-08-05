/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "notification_content")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "NotificationContent.findAll", query = "SELECT n FROM NotificationContent n"),
	@NamedQuery(name = "NotificationContent.findById", query = "SELECT n FROM NotificationContent n WHERE n.id = :id"),
	@NamedQuery(name = "NotificationContent.findByEntityId", query = "SELECT n FROM NotificationContent n WHERE n.entityId = :entityId"),
	@NamedQuery(name = "NotificationContent.findByImage", query = "SELECT n FROM NotificationContent n WHERE n.image = :image"),
	@NamedQuery(name = "NotificationContent.findByEntityType", query = "SELECT n FROM NotificationContent n WHERE n.entityType = :entityType"),
	@NamedQuery(name = "NotificationContent.findByCreatedAt", query = "SELECT n FROM NotificationContent n WHERE n.createdAt = :createdAt"),
	@NamedQuery(name = "NotificationContent.findByUpdatedAt", query = "SELECT n FROM NotificationContent n WHERE n.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"notificationSet"})
public class NotificationContent implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	private Integer id;
	@Size(max = 100)
	@Column(name = "entity_id")
	private String entityId;
	@Size(max = 255)
	private String image;
	@Size(max = 100)
	@Column(name = "entity_type")
	private String entityType;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "notificationContent")
	private Set<Notification> notificationSet;

	public NotificationContent() {
	}

	public NotificationContent(Integer id) {
		this.id = id;
	}

	public NotificationContent(Integer id, Date createdAt, Date updatedAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
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

	@XmlTransient
	public Set<Notification> getNotificationSet() {
		return notificationSet;
	}

	public void setNotificationSet(Set<Notification> notificationSet) {
		this.notificationSet = notificationSet;
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
		if (!(object instanceof NotificationContent)) {
			return false;
		}
		NotificationContent other = (NotificationContent) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.NotificationContent[ id=" + id + " ]";
	}
}
