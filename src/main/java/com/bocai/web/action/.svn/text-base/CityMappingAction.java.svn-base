package com.bocai.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.CityMapping;
import com.bocai.manager.CityMappingManager;
import com.bocai.vo.LatLng;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "ajax_check_city", type = "json", 
			params = {"includeProperties","cityMapping.*"}
	),
	@Result(name = "ajax_rectify_city", type = "json", 
			params = {"includeProperties","cityMapping.*"}
	)
})
public class CityMappingAction extends BaseAction<CityMapping> {
	
	private static final Logger logger = 
		LoggerFactory.getLogger(CityMappingAction.class);
	private static final String AJAX_CHECK_CITY = "ajax_check_city";
	private static final String AJAX_RECTIFY_CITY = "ajax_rectify_city";
	private CityMapping cityMapping;
	private String city;
	private LatLng center;
	private LatLng sw;
	private LatLng ne;
	private int zoom = -1;
	
	@Autowired
	private CityMappingManager cityMappingManager;

	@Action("check_city")
	public String ajaxCheckCity(){
		try {
			cityMapping = cityMappingManager.getCityMappingByName(URLDecoder.decode(city,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			addFieldError("city", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		logger.debug("get city mapping " + cityMapping);
		return AJAX_CHECK_CITY;
	}
	
	@Action("rectify_city")
	public String ajaxRectifyCity(){		
		if(city!=null && !city.isEmpty() &&
				center!=null && zoom != -1){
			cityMapping = cityMappingManager.getCityMappingByName(city);
			if(cityMapping==null){
				cityMapping = new CityMapping();
				cityMapping.setCity(city);
				cityMapping.setLat(center.getLat());
				cityMapping.setLng(center.getLng());
				if(sw!=null){
					cityMapping.setSwLat(sw.getLat());
					cityMapping.setSwLng(sw.getLng());
				}
				if(ne!=null){
					cityMapping.setNeLat(ne.getLat());
					cityMapping.setNeLng(ne.getLng());
				}
				cityMapping.setZoom(zoom);
				cityMappingManager.save(cityMapping);
				logger.debug("rectify city mapping " + cityMapping);
			}
		}
		return AJAX_RECTIFY_CITY;
	}
	
	@Override
	public CityMapping getModel() {
		return cityMapping;
	}
	
	@Override
	protected void prepareModel() throws Exception {}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LatLng getCenter() {
		return center;
	}
	public void setCenter(LatLng center) {
		this.center = center;
	}

	public LatLng getSw() {
		return sw;
	}
	public void setSw(LatLng sw) {
		this.sw = sw;
	}

	public LatLng getNe() {
		return ne;
	}
	public void setNe(LatLng ne) {
		this.ne = ne;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public CityMapping getCityMapping() {
		return cityMapping;
	}

	public void setCityMapping(CityMapping cityMapping) {
		this.cityMapping = cityMapping;
	}
}
