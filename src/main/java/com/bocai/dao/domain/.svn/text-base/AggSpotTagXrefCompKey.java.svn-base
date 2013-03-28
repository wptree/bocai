package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
@Embeddable
public class AggSpotTagXrefCompKey implements Serializable {
	
	private Tag tag;
	private AggregatedSpot aggSpot;
	
	public AggSpotTagXrefCompKey(){}
	
	public AggSpotTagXrefCompKey(Tag tag, AggregatedSpot aggSpot){
		this.tag = tag;
		this.aggSpot = aggSpot;
	}
	
	@ManyToOne
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	@ManyToOne
	public AggregatedSpot getAggSpot() {
		return aggSpot;
	}
	public void setAggSpot(AggregatedSpot aggSpot) {
		this.aggSpot = aggSpot;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(tag)
				.append(aggSpot)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof AggSpotTagXrefCompKey)){
			return false;
		}
		return new EqualsBuilder()
				.append(tag, ((AggSpotTagXrefCompKey)obj).getTag())
				.append(aggSpot, ((AggSpotTagXrefCompKey)obj).getAggSpot())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(tag)
				.append(aggSpot)
				.toString();
	}
	
}
