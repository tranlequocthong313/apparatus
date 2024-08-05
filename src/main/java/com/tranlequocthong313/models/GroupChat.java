/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "group_chat")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "GroupChat.findAll", query = "SELECT g FROM GroupChat g"),
	@NamedQuery(name = "GroupChat.findById", query = "SELECT g FROM GroupChat g WHERE g.id = :id"),
	@NamedQuery(name = "GroupChat.findByName", query = "SELECT g FROM GroupChat g WHERE g.name = :name")})
@JsonIgnoreProperties(value = {"groupMessageSet"})
public class GroupChat implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
	private Integer id;
	@Size(max = 45)
	private String name;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "groupChat")
	private UserGroupChat userGroupChat;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "groupChat")
	private Set<GroupMessage> groupMessageSet;

	public GroupChat() {
	}

	public GroupChat(Integer id) {
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

	public UserGroupChat getUserGroupChat() {
		return userGroupChat;
	}

	public void setUserGroupChat(UserGroupChat userGroupChat) {
		this.userGroupChat = userGroupChat;
	}

	@XmlTransient
	public Set<GroupMessage> getGroupMessageSet() {
		return groupMessageSet;
	}

	public void setGroupMessageSet(Set<GroupMessage> groupMessageSet) {
		this.groupMessageSet = groupMessageSet;
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
		if (!(object instanceof GroupChat)) {
			return false;
		}
		GroupChat other = (GroupChat) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.GroupChat[ id=" + id + " ]";
	}
	
}
