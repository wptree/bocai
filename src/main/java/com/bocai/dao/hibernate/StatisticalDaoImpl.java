package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.dao.StatisticalDao;
import com.bocai.dao.domain.Statistical;

@Repository("statisticalDao")
public class StatisticalDaoImpl extends HibernateBaseDao<Statistical, Long>implements StatisticalDao {

	@Override
	protected Class<Statistical> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statistical findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statistical findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statistical deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statistical save(Statistical bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Statistical> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

}
