package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.dao.domain.CityMapping;
import com.bocai.manager.CityMappingManager;
import com.bocai.manager.ManagerHelper;

@Service("cityMappingManager")
@Transactional
public class CityMappingManagerImpl extends ManagerHelper implements
		CityMappingManager {

	@Override
	public Long save(CityMapping bean) {
		if(bean!=null){
			 CityMapping cm = cityMappingDao.save(bean);
			 if(cm!=null)
				 return cm.getId();
		}
		return null;
	}

	@Override
	public Long update(CityMapping bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CityMapping bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CityMapping> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityMapping getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CityMapping getById(Long id) {
		return null;
	}

	@Override
	public CityMapping getCityMappingByName(String cityName) {
		return cityMappingDao.getCityMappingByName(cityName);
	}
	
	public CityMapping getCityMappingByPinYin(String pinYin){
		return cityMappingDao.getCityMappingByPinYin(pinYin);
	}

	@Override
	public List<CityMapping> getAll() {
		return cityMappingDao.find("from CityMapping order by id asc");
	}

}
