/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author tranlequocthong313
 */
@Entity
@Table(name = "thread_category")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ThreadCategory.findAll", query = "SELECT t FROM ThreadCategory t"),
	@NamedQuery(name = "ThreadCategory.findById", query = "SELECT t FROM ThreadCategory t WHERE t.id = :id"),
	@NamedQuery(name = "ThreadCategory.findByName", query = "SELECT t FROM ThreadCategory t WHERE t.name = :name"),
	@NamedQuery(name = "ThreadCategory.findByCreatedAt", query = "SELECT t FROM ThreadCategory t WHERE t.createdAt = :createdAt"),
	@NamedQuery(name = "ThreadCategory.findByUpdatedAt", query = "SELECT t FROM ThreadCategory t WHERE t.updatedAt = :updatedAt")})
@JsonIgnoreProperties(value = {"threadSet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThreadCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;
	@Size(max = 100)
	@NotNull
	private String name;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "threadCategory")
	private Set<Thread> threadSet;
	@JoinColumn(name = "forum_category_id", referencedColumnName = "id")
	@ManyToOne
	private ForumCategory forumCategory;

	public ThreadCategory(Integer id) {
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
	public Set<Thread> getThreadSet() {
		return threadSet;
	}

	public void setThreadSet(Set<Thread> threadSet) {
		this.threadSet = threadSet;
	}

	public ForumCategory getForumCategory() {
		return forumCategory;
	}

	public void setForumCategory(ForumCategory forumCategory) {
		this.forumCategory = forumCategory;
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
		if (!(object instanceof ThreadCategory)) {
			return false;
		}
		ThreadCategory other = (ThreadCategory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tranlequocthong313.models.ThreadCategory[ id=" + id + " ]";
	}

}
