package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.dao.UserThirdpartyDao;
import com.bocai.dao.domain.UserThirdparty;

@Repository("userThirdpartyDao")
public class UserThirdpartyDaoImpl extends HibernateBaseDao<UserThirdparty, Long> implements UserThirdpartyDao  {

	@Override
	protected Class<UserThirdparty> getEntityClass() {
		return UserThirdparty.class;
	}

	@Override
	public UserThirdparty findById(Long id) {
		return super.get(id);
	}

	@Override
	public UserThirdparty findUniqueBy(String prop, Object value) {
		UserThirdparty entity = findUniqueByProperty(prop, value);
		return entity;
	}

	@Override
	public UserThirdparty deleteById(Long id) {
		UserThirdparty entity = get(id);
		if(entity != null){
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public UserThirdparty save(UserThirdparty bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public List<UserThirdparty> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}
}
