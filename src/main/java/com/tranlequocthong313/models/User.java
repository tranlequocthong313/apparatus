/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.findByFullName", query = "SELECT u FROM User u WHERE u.fullName = :fullName"),
	@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber"),
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
	@NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
	@NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = :userRole"),
	@NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
	@NamedQuery(name = "User.findByCreatedAt", query = "SELECT u FROM User u WHERE u.createdAt = :createdAt"),
	@NamedQuery(name = "User.findByUpdatedAt", query = "SELECT u FROM User u WHERE u.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"repairSet", "notificationSet", "fcmTokenSet", "replySet", "issueSet", "threadSet", "messageSet", "messageSet1", "userMaintenanceSet", "userGroupChatSet", "deviceSet", "groupMessageSet"})
public class User implements Serializable {

	public enum UserRole {
		ROLE_ADMIN,
		ROLE_WORKER,
		ROLE_USER;
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 50)
	@Column(name = "full_name")
	private String fullName;
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	@Size(max = 100)
	private String email;
	@Size(max = 11)
	@Column(name = "phone_number")
	private String phoneNumber;
	@Size(max = 50)
	private String username;
	@Size(max = 100)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY
	)
	private String password;
	@Basic(optional = false)
	private boolean active;
	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	@Size(max = 255)
	private String avatar;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Repair> repairSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Notification> notificationSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<FcmToken> fcmTokenSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Reply> replySet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Issue> issueSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Thread> threadSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Message> messageSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
	private Set<Message> messageSet1;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private UserMaintenance userMaintenance;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private UserGroupChat userGroupChat;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Device> deviceSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<GroupMessage> groupMessageSet;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, boolean active, Date createdAt, Date updatedAt) {
		this.id = id;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	@XmlTransient
	public Set<Repair> getRepairSet() {
		return repairSet;
	}

	public void setRepairSet(Set<Repair> repairSet) {
		this.repairSet = repairSet;
	}

	@XmlTransient
	public Set<Notification> getNotificationSet() {
		return notificationSet;
	}

	public void setNotificationSet(Set<Notification> notificationSet) {
		this.notificationSet = notificationSet;
	}

	@XmlTransient
	public Set<FcmToken> getFcmTokenSet() {
		return fcmTokenSet;
	}

	public void setFcmTokenSet(Set<FcmToken> fcmTokenSet) {
		this.fcmTokenSet = fcmTokenSet;
	}

	@XmlTransient
	public Set<Reply> getReplySet() {
		return replySet;
	}

	public void setReplySet(Set<Reply> replySet) {
		this.replySet = replySet;
	}

	@XmlTransient
	public Set<Issue> getIssueSet() {
		return issueSet;
	}

	public void setIssueSet(Set<Issue> issueSet) {
		this.issueSet = issueSet;
	}

	@XmlTransient
	public Set<Thread> getThreadSet() {
		return threadSet;
	}

	public void setThreadSet(Set<Thread> threadSet) {
		this.threadSet = threadSet;
	}

	@XmlTransient
	public Set<Message> getMessageSet() {
		return messageSet;
	}

	public void setMessageSet(Set<Message> messageSet) {
		this.messageSet = messageSet;
	}

	@XmlTransient
	public Set<Message> getMessageSet1() {
		return messageSet1;
	}

	public void setMessageSet1(Set<Message> messageSet1) {
		this.messageSet1 = messageSet1;
	}

	public UserMaintenance getUserMaintenance() {
		return userMaintenance;
	}

	public void setUserMaintenance(UserMaintenance userMaintenance) {
		this.userMaintenance = userMaintenance;
	}

	public UserGroupChat getUserGroupChat() {
		return userGroupChat;
	}

	public void setUserGroupChat(UserGroupChat userGroupChat) {
		this.userGroupChat = userGroupChat;
	}

	@XmlTransient
	public Set<Device> getDeviceSet() {
		return deviceSet;
	}

	public void setDeviceSet(Set<Device> deviceSet) {
		this.deviceSet = deviceSet;
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
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.User[ id=" + id + " ]";
	}

}
