package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.UserTaskXrefDao;
import com.bocai.dao.domain.UserTaskCompKey;
import com.bocai.dao.domain.UserTaskXref;

@Repository("userTaskXrefDao")
public class UserTaskXrefDaoImpl extends HibernateBaseDao<UserTaskXref, UserTaskCompKey> implements
		UserTaskXrefDao {

	@Override
	public UserTaskXref load(UserTaskCompKey id){
		return super.get(id);
	}
	
	@Override
	public UserTaskXref findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTaskXref findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTaskXref deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTaskXref save(UserTaskXref bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}

	@Override
	public UserTaskXref updateByUpdater(Updater<UserTaskXref> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTaskXref> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<UserTaskXref> getEntityClass() {
		return UserTaskXref.class;
	}

}
