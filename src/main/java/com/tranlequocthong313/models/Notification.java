/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
	@NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id"),
	@NamedQuery(name = "Notification.findByRead", query = "SELECT n FROM Notification n WHERE n.read = :read"),
	@NamedQuery(name = "Notification.findByType", query = "SELECT n FROM Notification n WHERE n.type = :type"),
	@NamedQuery(name = "Notification.findByCreatedAt", query = "SELECT n FROM Notification n WHERE n.createdAt = :createdAt"),
	@NamedQuery(name = "Notification.findByUpdatedAt", query = "SELECT n FROM Notification n WHERE n.updatedAt = :updatedAt")})
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Basic(optional = false)
	@NotNull
	private boolean read;
	@Size(max = 9)
	private String type;
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
	@JoinColumn(name = "notification_content_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private NotificationContent notificationContent;
	@JoinColumn(name = "recipient_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;

	public Notification() {
	}

	public Notification(Integer id) {
		this.id = id;
	}

	public Notification(Integer id, boolean read, Date createdAt, Date updatedAt) {
		this.id = id;
		this.read = read;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public NotificationContent getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(NotificationContent notificationContent) {
		this.notificationContent = notificationContent;
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
		if (!(object instanceof Notification)) {
			return false;
		}
		Notification other = (Notification) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Notification[ id=" + id + " ]";
	}
}
