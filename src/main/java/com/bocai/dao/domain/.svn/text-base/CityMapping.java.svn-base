package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_CITY_MAPPING")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CityMapping implements Serializable {
	
	private Long id;
	private String city;
	private String pinYin;
	private Double lat;
    private Double lng;
    private Double swLat;
    private Double swLng;
    private Double neLat;
    private Double neLng;
    private Integer zoom;
    
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
	
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="PIN_YIN")
	public String getPinYin() {
		return pinYin;
	}
	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	
	@Column(name="LAT")
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Column(name="LNG")
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	@Column(name="SW_LAT")
	public Double getSwLat() {
		return swLat;
	}
	public void setSwLat(Double swLat) {
		this.swLat = swLat;
	}
	
	@Column(name="SW_LNG")
	public Double getSwLng() {
		return swLng;
	}
	public void setSwLng(Double swLng) {
		this.swLng = swLng;
	}
	
	@Column(name="NE_LAT")
	public Double getNeLat() {
		return neLat;
	}
	public void setNeLat(Double neLat) {
		this.neLat = neLat;
	}
	
	@Column(name="NE_LNG")
	public Double getNeLng() {
		return neLng;
	}
	public void setNeLng(Double neLng) {
		this.neLng = neLng;
	}
	
	@Column(name="ZOOM")
	public Integer getZoom() {
		return zoom;
	}
	public void setZoom(Integer zoom) {
		this.zoom = zoom;
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
		}else if(!(obj instanceof CityMapping)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((CityMapping)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(pinYin)
				.append(lat)
				.append(lng)
				.append(zoom)
				.append(swLat)
				.append(swLng)
				.append(neLat)
				.append(neLng)
				.toString();
	}
    
}
