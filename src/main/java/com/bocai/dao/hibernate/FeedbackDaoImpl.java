package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.FeedbackDao;
import com.bocai.dao.domain.Feedback;

@Repository("feedbackDao")
public class FeedbackDaoImpl extends HibernateBaseDao<Feedback, Long> implements
		FeedbackDao {

	@Override
	public Feedback findById(Long id) {
		return super.get(id);
	}

	@Override
	public Feedback findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback save(Feedback bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Feedback updateByUpdater(Updater<Feedback> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<Feedback> getEntityClass() {
		return Feedback.class;
	}

}
