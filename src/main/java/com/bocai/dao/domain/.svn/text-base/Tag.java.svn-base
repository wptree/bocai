package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="BC_TAG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag implements Serializable{
	private Long id;
	private String tagName;
	private String iconPath;
	private String tagFrom;
	private String tagClass;
	private Integer weight;
	private List<AggSpotTagXref> aggSpotXrefs;
	private Date createdAt;
	private Date updatedAt;
	private String description;
	private Set<User> followers;
	private Set<User> creators;
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
	
	@Column(name = "TAGNAME", unique = true, nullable = false)
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Column(name = "TAG_FROM")
	public String getTagFrom() {
		return tagFrom;
	}
	public void setTagFrom(String tagFrom) {
		this.tagFrom = tagFrom;
	}
	
	@Column(name = "TAG_CLASS")
	public String getTagClass() {
		return tagClass;
	}
	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}
	
	@Column(name = "ICONPATH")
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	@Column(name = "WEIGHT")
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Column(name = "CREATED_AT", nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "UPDATED_AT", nullable = false)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@OneToMany(mappedBy = "id.aggSpot")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<AggSpotTagXref> getAggSpotXrefs() {
		return aggSpotXrefs;
	}
	public void setAggSpotXrefs(List<AggSpotTagXref> aggSpotXrefs) {
		this.aggSpotXrefs = aggSpotXrefs;
	}
	
	@ManyToMany(targetEntity=User.class, mappedBy="followingTags")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	@ManyToMany(targetEntity=User.class, mappedBy="createdTags")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getCreators() {
		return creators;
	}
	public void setCreators(Set<User> creators) {
		this.creators = creators;
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
		}
		return new EqualsBuilder()
				.append(id, ((Tag)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(tagName)
				.toString();
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	@Column(name = "FOLLOWER_COUNT")
	public Long getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}
}
