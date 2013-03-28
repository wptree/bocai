package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.DishStyleMetaDao;
import com.bocai.dao.domain.DishStyleMeta;

@Repository("dishStyleMetaDao")
public class DishStyleMetaDaoImpl extends HibernateBaseDao<DishStyleMeta, Long> implements
		DishStyleMetaDao {

	@Override
	public DishStyleMeta findById(Long id) {
		return super.get(id);
	}

	@Override
	public DishStyleMeta findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishStyleMeta deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishStyleMeta save(DishStyleMeta bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public DishStyleMeta updateByUpdater(Updater<DishStyleMeta> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DishStyleMeta> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<DishStyleMeta> getEntityClass() {
		return DishStyleMeta.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

}
