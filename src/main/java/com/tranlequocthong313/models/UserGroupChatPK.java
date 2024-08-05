/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tranlequocthong313
 */
@Embeddable
public class UserGroupChatPK implements Serializable {

	@Basic(optional = false)
        @NotNull
        @Column(name = "user_id")
	private int userId;
	@Basic(optional = false)
        @NotNull
        @Column(name = "group_chat_id")
	private int groupChatId;

	public UserGroupChatPK() {
	}

	public UserGroupChatPK(int userId, int groupChatId) {
		this.userId = userId;
		this.groupChatId = groupChatId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupChatId() {
		return groupChatId;
	}

	public void setGroupChatId(int groupChatId) {
		this.groupChatId = groupChatId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) userId;
		hash += (int) groupChatId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserGroupChatPK)) {
			return false;
		}
		UserGroupChatPK other = (UserGroupChatPK) object;
		if (this.userId != other.userId) {
			return false;
		}
		if (this.groupChatId != other.groupChatId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.UserGroupChatPK[ userId=" + userId + ", groupChatId=" + groupChatId + " ]";
	}
	
}
