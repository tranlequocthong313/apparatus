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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
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
@Table(name = "message")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
	@NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
	@NamedQuery(name = "Message.findByCreatedAt", query = "SELECT m FROM Message m WHERE m.createdAt = :createdAt"),
	@NamedQuery(name = "Message.findByUpdatedAt", query = "SELECT m FROM Message m WHERE m.updatedAt = :updatedAt")})
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	private Integer id;
	@Lob
	@Size(max = 65535)
	private String content;
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
	@JoinColumn(name = "from", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	@JoinColumn(name = "to", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user1;

	public Message() {
	}

	public Message(Integer id) {
		this.id = id;
	}

	public Message(Integer id, Date createdAt, Date updatedAt) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
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
		if (!(object instanceof Message)) {
			return false;
		}
		Message other = (Message) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Message[ id=" + id + " ]";
	}
}