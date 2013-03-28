package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.dao.DishTasteMetaDao;
import com.bocai.dao.domain.DishTasteMeta;

@Repository("dishTasteMetaDao")
public class DishTasteMetaDaoImpl extends HibernateBaseDao<DishTasteMeta, Long> implements DishTasteMetaDao {

	@Override
	public DishTasteMeta save(DishTasteMeta bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Class<DishTasteMeta> getEntityClass() {
		return DishTasteMeta.class;
	}

	@Override
	public DishTasteMeta findById(Long id) {
		return super.get(id);
	}

	@Override
	public DishTasteMeta findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishTasteMeta deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DishTasteMeta> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}


}
