package com.bocai.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class LatLng implements Serializable {
	private double lat;
	private double lng;
	
	public LatLng(){};
	
	public LatLng(double lat, double lng){
		this.lat = lat;
		this.lng = lng;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof LatLng)){
			return false;
		}
		return new EqualsBuilder()
				.append(this.getLat(), ((LatLng)obj).getLat())
				.append(this.getLng(), ((LatLng)obj).getLng())
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getLat())
				.append(getLng())
				.toHashCode();
	}

	public String toString(){
		return lat + "_" + lng;
	}
}
