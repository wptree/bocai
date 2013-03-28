package com.bocai.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.bocai.dao.domain.Location;

@SuppressWarnings("serial")
public class LocationVo implements Serializable {
	
	private Long id;
	private String country;
	private String province;
	private String city;
	private String district;
	private Double lat;
    private Double lng;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
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
		}else if(!(obj instanceof LocationVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((LocationVo)obj).getId())
				.isEquals();
	}
	
	public static LocationVo toBasic(Location location){
		if(location == null) return null;
		LocationVo vo = new LocationVo();
		vo.setCity(location.getCity());
		vo.setCountry(location.getCountry());
		vo.setDistrict(location.getDistrict());
		vo.setId(location.getId());
		vo.setLat(location.getLat());
		vo.setLng(location.getLng());
		vo.setProvince(location.getProvince());
		return vo;
	}
}
