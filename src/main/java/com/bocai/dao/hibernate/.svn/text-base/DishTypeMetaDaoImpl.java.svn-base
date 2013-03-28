package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.DishTypeMetaDao;
import com.bocai.dao.domain.DishTypeMeta;

@Repository("dishTypeMetaDao")
public class DishTypeMetaDaoImpl extends HibernateBaseDao<DishTypeMeta, Long> implements DishTypeMetaDao {

	@Override
	public DishTypeMeta findById(Long id) {
		return super.get(id);
	}

	@Override
	public DishTypeMeta findUniqueBy(String prop, Object value) {
		return super.findUniqueByProperty(prop, value);
	}

	@Override
	public DishTypeMeta deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishTypeMeta save(DishTypeMeta bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public DishTypeMeta updateByUpdater(Updater<DishTypeMeta> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DishTypeMeta> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<DishTypeMeta> getEntityClass() {
		return DishTypeMeta.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}


}
