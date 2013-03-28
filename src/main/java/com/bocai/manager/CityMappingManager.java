package com.bocai.manager;

import java.util.List;

import com.bocai.dao.domain.CityMapping;

public interface CityMappingManager extends BaseManager<CityMapping>  {
	
	public CityMapping getCityMappingByName(String cityName);
	
	public CityMapping getCityMappingByPinYin(String pinYin);
	
	public List<CityMapping> getAll();
	
}
