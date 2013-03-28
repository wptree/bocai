package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bocai.util.StringUtil;

@Entity
@Table(name="BC_PLACE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Place implements Serializable {
	private static final long serialVersionUID = 5908412628502522809L;
	private Long id;
	private String name;
	private String secondaryName;
	private String fullName;
	private String pinyin;
	private String pinyinHeader;
	private String venueType;
	private Location location;
	private String links;
	private String phone;
	private Long spottedCount;
	private Set<Spot> spots;
	private Set<AggregatedSpot> aggSpots;
	private Set<User> followers;
	private Long followerCount;

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
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		setPinyin(StringUtil.getPingYin(name));
		setPinyinHeader(StringUtil.getPinYinHeadChar(name));
	}
	
	@Column(name = "LINKS")
	public String getLinks() {
		return links;
	}
	public void setLinks(String links) {
		this.links = links;
	}
	
	@Column(name = "PHONE")
	public String getPhone() {
		if(phone==null){
			return "--";
		}
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "SECONDARY_NAME")
	public String getSecondaryName() {
		return secondaryName;
	}
	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}
	
	@Column(name = "FULL_NAME")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Column(name = "VENUE_TYPE")
	public String getVenueType() {
		return venueType;
	}
	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}
	
	@Column(name = "SPOTTED_COUNT")
	public Long getSpottedCount() {
		return spottedCount;
	}
	public void setSpottedCount(Long spottedCount) {
		this.spottedCount = spottedCount;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Column(name = "PINYIN")
	public String getPinyin() {
		return this.pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Column(name = "PINYIN_HEADER")
	public String getPinyinHeader() {
		return pinyinHeader;
	}
	public void setPinyinHeader(String pinyinHeader) {
		this.pinyinHeader = pinyinHeader;
	}
	
	
	@OneToMany(mappedBy="place", fetch = FetchType.LAZY)
	public Set<Spot> getSpots() {
		return spots;
	}
	public void setSpots(Set<Spot> spots) {
		this.spots = spots;
	}
	
	@OneToMany(mappedBy="place", fetch = FetchType.LAZY)
	public Set<AggregatedSpot> getAggSpots() {
		return aggSpots;
	}
	public void setAggSpots(Set<AggregatedSpot> aggSpots) {
		this.aggSpots = aggSpots;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Place)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Place)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(name)
				.append(secondaryName)
				.toString();
	}
	
	@ManyToMany(targetEntity=User.class, mappedBy="followingPlaces")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	@Column(name = "FOLLOWER_COUNT")
	public Long getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}
	
}
