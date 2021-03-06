package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.UserTaskDao;
import com.bocai.dao.domain.UserTask;

@Repository("userTaskDao")
public class UserTaskDaoImpl extends HibernateBaseDao<UserTask, Long> implements UserTaskDao {

	@Override
	public UserTask findById(Long id) {
		return super.get(id);
	}

	@Override
	public UserTask findUniqueBy(String prop, Object value) {
		return super.findUniqueByProperty(prop, value);
	}

	@Override
	public UserTask deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTask save(UserTask bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTask updateByUpdater(Updater<UserTask> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTask> findBy(String property, Object value) {
		return super.findByProperty(property, value);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<UserTask> getEntityClass() {
		return UserTask.class;
	}
}
