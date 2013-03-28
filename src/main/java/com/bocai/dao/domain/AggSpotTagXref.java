package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_XREF_AGG_SPOT_TAG")
@AssociationOverrides({
	@AssociationOverride(name = "id.tag", joinColumns = @JoinColumn(name = "TAG_ID")),
	@AssociationOverride(name = "id.aggSpot", joinColumns = @JoinColumn(name = "AGG_SPOT_ID"))
 })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AggSpotTagXref implements Serializable {
	
	private AggSpotTagXrefCompKey id;
	private Long status;
	private Integer count;
	private Date createdAt;
	private Date updatedAt;
	
	@Id
	public AggSpotTagXrefCompKey getId() {
		return id;
	}
	public void setId(AggSpotTagXrefCompKey id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = false)
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	@Column(name = "COUNT", nullable = false)
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Column(name = "CREATED_AT")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Transient
	public Tag getTag(){
		return getId().getTag();
	}
	public void setUser(Tag tag){
		getId().setTag(tag);
	}
	
	@Transient
	public AggregatedSpot getAggSpot(){
		return getId().getAggSpot();
	}
	public void setAggSpot(AggregatedSpot aggSpot){
		getId().setAggSpot(aggSpot);
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
				.append(id, ((AggSpotTagXref)obj).id)
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(status)
				.toString();
	}
}
