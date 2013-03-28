package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.LocationDao;
import com.bocai.dao.domain.Location;

@Repository("locationDao")
public class LocationDaoImpl extends HibernateBaseDao<Location, Long> implements LocationDao {

	@Override
	public Location findById(Long id) {
		return super.get(id);
	}

	@Override
	public Location findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Location> findBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Location deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location save(Location bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Location updateByUpdater(Updater<Location> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Location> getEntityClass() {
		return Location.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

}
