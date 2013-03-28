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

import com.bocai.common.constant.AppConstant;

@Entity
@Table(name="BC_LOCATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location implements Serializable {

	private static final long serialVersionUID = 4470962400671281650L;
	
	private Long id;
	private String country;
	private String province;
	private String city;
	private String district;
	private String street;
	private String postalCode;
	private String fullAddress;
    private Double lat;
    private Double lng;
	
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
	
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="PROVINCE")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="DISTRICT")
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Column(name="STREET")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name="POSTCODE")
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Column(name="FULL_ADDRESS")
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	
	@Column(name="LAT")
	public Double getLat() {
		return lat;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	@Column(name="LNG")
	public Double getLng() {
		return lng;
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
		}else if(!(obj instanceof Location)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Location)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(country)
				.append(province)
				.append(city)
				.append(district)
				.append(street)
				.append(postalCode)
				.toString();
	}
	
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	public void parse(String latAndLng){
		if(latAndLng!=null&&!"".equals(latAndLng)){
			setLat(Double.valueOf(latAndLng.split("_")[0]));
			setLng(Double.valueOf(latAndLng.split("_")[1]));
		}
	}
	
	public String toAddress(){
		StringBuilder sb = new StringBuilder();
		if(this.getProvince()!=null){
			sb.append(this.getProvince()).append(AppConstant.BLANK);
		}
		if(this.getCity()!=null){
			sb.append(this.getCity()).append(AppConstant.BLANK);
		}
		if(this.getDistrict()!=null){
			sb.append(this.getDistrict()).append(AppConstant.BLANK);
		}
		if(this.getStreet()!=null){
			sb.append(this.getStreet()).append(AppConstant.BLANK);
		}	
		return sb.toString();
	}

	
}
