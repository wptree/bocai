package com.bocai.web.action.spot;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.manager.PlaceManager;
import com.bocai.util.CommonUtil;
import com.bocai.vo.AddressComponent;
import com.bocai.vo.LatLng;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax", type = "json", 
		params = {"includeProperties","id,fullName,latLng.*"}
	)
})
public class AddPlaceAction extends BaseAction<Object> {
	
	private Long id;
	private String name;
	private String secondaryName;
	private String fullName;
	private LatLng latLng;
	private String phone;
	private String fullAddress;
	private String city;
	private String venueType;
	private AddressComponent[] acs;
	private Place place;
	@Autowired
	private PlaceManager placeManager;
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		place = new Place();
	}
	
	@SuppressWarnings("rawtypes")
	@Action("add_place")
	public String add(){
		List list = placeManager.checkPlaceExist(getName(), getSecondaryName());
		if(list.size()==0){
			place.setName(getName());
			place.setSecondaryName(getSecondaryName());
			StringBuilder sb =  new StringBuilder();
			sb.append(place.getName());
			if(!StringUtils.isBlank(place.getSecondaryName())){
				sb.append(AppConstant.LEFT_PTS)
					.append(place.getSecondaryName())
					.append(AppConstant.RIGHT_PTS);
			}
			setFullName(sb.toString());
			place.setFullName(fullName);
			place.setVenueType(getVenueType());
			place.setPhone(getPhone());
			if(getLatLng()!=null || getAcs()!=null){
				Location loc = new Location();
				loc.setFullAddress(getFullAddress());
				loc.setStreet(fullAddress);
				CommonUtil.populateAddress(acs, loc);
				if(getLatLng()!=null){
					loc.setLat(latLng.getLat());
					loc.setLng(latLng.getLng());
				}
				place.setLocation(loc);
			}
			
			placeManager.save(place);
		}else{
			place = (Place)list.get(0);
		}
		setId(place.getId());
		return AJAX;
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

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondaryName() {
		return secondaryName;
	}

	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getVenueType() {
		return venueType;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public AddressComponent[] getAcs() {
		return acs;
	}

	public void setAcs(AddressComponent[] acs) {
		this.acs = acs;
	}
}
