package com.bocai.vo;

import java.io.Serializable;
import java.util.Set;

import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;

@SuppressWarnings("serial")
public class PlaceVo implements Serializable {


	private Long id;
	private String fullName;
	private String fullAddress;
	private Double lat;
    private Double lng;
	
	public static PlaceVo toBasic(Place place){
		PlaceVo vo = new PlaceVo();
		vo.setId(place.getId());
		vo.setFullName(place.getFullName());
		vo.setFullAddress(place.getLocation().getCity()+place.getLocation().getStreet());
		vo.setLat(place.getLocation().getLat());
		vo.setLng(place.getLocation().getLng());
		return vo;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLat() {
		return lat;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLng() {
		return lng;
	}
}
