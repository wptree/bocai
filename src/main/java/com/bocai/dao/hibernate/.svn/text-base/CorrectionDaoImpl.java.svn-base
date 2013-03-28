package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.CorrectionDao;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Correction;

@Repository("correctionDao")
public class CorrectionDaoImpl extends HibernateBaseDao<Correction, Long> implements CorrectionDao {

	@Override
	public Correction findById(Long id) {
		return super.get(id);
	}

	@Override
	public Correction findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Correction deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Correction save(Correction bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Correction updateByUpdater(Updater<Correction> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Correction> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<Correction> getEntityClass() {
		return Correction.class;
	}

}
