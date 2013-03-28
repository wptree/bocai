package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="BC_SPOT_GUIDE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpotGuide implements Serializable {

	private static final long serialVersionUID = 2982253920081075302L;
	private Long id;
	private String name;
	private String icon; //path for image, used to represent this guide in visualization
	private String city;
	private User createdBy;
	private Date createdAt;
	private String description;
	private Set<AggregatedSpot> spotList;
	private Set<User> followedBy;
	private String mapLink;
	
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
	
	
	@Column(name = "ICON")
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@OneToOne
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "CREATED_AT")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany
	public Set<AggregatedSpot> getSpotList() {
		return spotList;
	}
	public void setSpotList(Set<AggregatedSpot> spotList) {
		this.spotList = spotList;
	}
	
	@OneToMany
	public Set<User> getFollowedBy() {
		return followedBy;
	}
	public void setFollowedBy(Set<User> followedBy) {
		this.followedBy = followedBy;
	}
	
	@Column(name = "MAP_LINK")
	public String getMapLink() {
		return mapLink;
	}
	public void setMapLink(String mapLink) {
		this.mapLink = mapLink;
	}
	
}
