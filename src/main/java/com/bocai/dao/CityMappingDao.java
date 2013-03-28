package com.bocai.dao;

import com.bocai.dao.domain.CityMapping;

public interface CityMappingDao extends BaseDao<CityMapping> {
	
	public CityMapping getCityMappingByName(String cityName);
	
	public CityMapping getCityMappingByPinYin(String pinYin);
	
}
