package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.dao.CityMappingDao;
import com.bocai.dao.domain.CityMapping;

@Repository("cityMappingDao")
public class CityMappingDaoImpl extends HibernateBaseDao<CityMapping, Long> implements CityMappingDao {

	@Override
	public CityMapping getCityMappingByName(String cityName) {
		return findUniqueBy("city", cityName);
	}
	
	public CityMapping getCityMappingByPinYin(String pinYin) {
		return findUniqueBy("pinYin", pinYin);
	}
	
	@Override
	protected Class<CityMapping> getEntityClass() {
		// TODO Auto-generated method stub
		return CityMapping.class;
	}

	@Override
	public CityMapping findById(Long id) {
		return super.get(id);
	}

	@Override
	public CityMapping findUniqueBy(String prop, Object value) {
		return super.findUniqueByProperty(prop, value);
	}

	@Override
	public CityMapping deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityMapping save(CityMapping bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public List<CityMapping> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

}
