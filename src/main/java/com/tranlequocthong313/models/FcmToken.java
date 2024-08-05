/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "fcm_token")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "FcmToken.findAll", query = "SELECT f FROM FcmToken f"),
	@NamedQuery(name = "FcmToken.findById", query = "SELECT f FROM FcmToken f WHERE f.id = :id"),
	@NamedQuery(name = "FcmToken.findByToken", query = "SELECT f FROM FcmToken f WHERE f.token = :token")})
public class FcmToken implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @NotNull
	private Integer id;
	@Size(max = 163)
	private String token;
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
        @ManyToOne(optional = false)
	private User user;

	public FcmToken() {
	}

	public FcmToken(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
		if (!(object instanceof FcmToken)) {
			return false;
		}
		FcmToken other = (FcmToken) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.FcmToken[ id=" + id + " ]";
	}
	
}
