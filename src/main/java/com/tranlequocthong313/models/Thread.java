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
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author tranlequocthong313
 */
@Entity
@Table(name = "thread")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Thread.findAll", query = "SELECT t FROM Thread t"),
	@NamedQuery(name = "Thread.findById", query = "SELECT t FROM Thread t WHERE t.id = :id"),
	@NamedQuery(name = "Thread.findByTitle", query = "SELECT t FROM Thread t WHERE t.title = :title"),
	@NamedQuery(name = "Thread.findByCreatedAt", query = "SELECT t FROM Thread t WHERE t.createdAt = :createdAt"),
	@NamedQuery(name = "Thread.findByUpdatedAt", query = "SELECT t FROM Thread t WHERE t.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"replySet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Thread implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@NotNull
	@Size(max = 255)
	private String title;
	@NotNull
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "thread")
	private Set<Reply> replySet;
	@JoinColumn(name = "thread_category_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private ThreadCategory threadCategory;
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private User user;

	public Thread(Integer id){
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

	@XmlTransient
	public Set<Reply> getReplySet() {
		return replySet;
	}

	public void setReplySet(Set<Reply> replySet) {
		this.replySet = replySet;
	}

	public ThreadCategory getThreadCategory() {
		return threadCategory;
	}

	public void setThreadCategory(ThreadCategory threadCategory) {
		this.threadCategory = threadCategory;
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
		if (!(object instanceof Thread)) {
			return false;
		}
		Thread other = (Thread) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.Thread[ id=" + id + " ]";
	}
}
