/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "notification_sender")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "NotificationSender.findAll", query = "SELECT n FROM NotificationSender n"),
	@NamedQuery(name = "NotificationSender.findById", query = "SELECT n FROM NotificationSender n WHERE n.id = :id"),
	@NamedQuery(name = "NotificationSender.findBySenderId", query = "SELECT n FROM NotificationSender n WHERE n.senderId = :senderId"),
	@NamedQuery(name = "NotificationSender.findByNotificationContentId", query = "SELECT n FROM NotificationSender n WHERE n.notificationContentId = :notificationContentId")})
public class NotificationSender implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
	private Integer id;
	@Basic(optional = false)
        @NotNull
        @Column(name = "sender_id")
	private int senderId;
	@Basic(optional = false)
        @NotNull
        @Column(name = "notification_content_id")
	private int notificationContentId;

	public NotificationSender() {
	}

	public NotificationSender(Integer id) {
		this.id = id;
	}

	public NotificationSender(Integer id, int senderId, int notificationContentId) {
		this.id = id;
		this.senderId = senderId;
		this.notificationContentId = notificationContentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getNotificationContentId() {
		return notificationContentId;
	}

	public void setNotificationContentId(int notificationContentId) {
		this.notificationContentId = notificationContentId;
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
		if (!(object instanceof NotificationSender)) {
			return false;
		}
		NotificationSender other = (NotificationSender) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.NotificationSender[ id=" + id + " ]";
	}
	
}
