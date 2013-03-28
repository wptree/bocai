package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="BC_USER_TITLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserTitle implements Serializable{

	private static final long serialVersionUID = -7648388028778533977L;
	private Long id;
	private String name;
	private User user;
	private Integer requiredSpotNum;
	private Integer realSpotNum;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "REQUIRED_SPOT_NUM", nullable = false)
	public Integer getRequiredSpotNum() {
		return requiredSpotNum;
	}
	public void setRequiredSpotNum(Integer requiredSpotNum) {
		this.requiredSpotNum = requiredSpotNum;
	}
	
	@Column(name = "REAL_SPOT_NUM", nullable = false)
	public Integer getRealSpotNum() {
		return realSpotNum;
	}
	public void setRealSpotNum(Integer realSpotNum) {
		this.realSpotNum = realSpotNum;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
