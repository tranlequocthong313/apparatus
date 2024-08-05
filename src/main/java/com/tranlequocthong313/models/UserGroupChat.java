/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "user_group_chat")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserGroupChat.findAll", query = "SELECT u FROM UserGroupChat u"),
	@NamedQuery(name = "UserGroupChat.findByUserId", query = "SELECT u FROM UserGroupChat u WHERE u.userGroupChatPK.userId = :userId"),
	@NamedQuery(name = "UserGroupChat.findByGroupChatId", query = "SELECT u FROM UserGroupChat u WHERE u.userGroupChatPK.groupChatId = :groupChatId")})
public class UserGroupChat implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected UserGroupChatPK userGroupChatPK;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "group_chat_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private GroupChat groupChat;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private User user;

	public UserGroupChat() {
	}

	public UserGroupChat(UserGroupChatPK userGroupChatPK) {
		this.userGroupChatPK = userGroupChatPK;
	}

	public UserGroupChat(int userId, int groupChatId) {
		this.userGroupChatPK = new UserGroupChatPK(userId, groupChatId);
	}

	public UserGroupChatPK getUserGroupChatPK() {
		return userGroupChatPK;
	}

	public void setUserGroupChatPK(UserGroupChatPK userGroupChatPK) {
		this.userGroupChatPK = userGroupChatPK;
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
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
		hash += (userGroupChatPK != null ? userGroupChatPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserGroupChat)) {
			return false;
		}
		UserGroupChat other = (UserGroupChat) object;
		if ((this.userGroupChatPK == null && other.userGroupChatPK != null) || (this.userGroupChatPK != null && !this.userGroupChatPK.equals(other.userGroupChatPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.UserGroupChat[ userGroupChatPK=" + userGroupChatPK + " ]";
	}

}
