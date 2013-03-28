package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.ReplyDao;
import com.bocai.dao.domain.Reply;

@Repository("replyDao")
public class ReplyDaoImpl extends HibernateBaseDao<Reply, Long> implements ReplyDao {

	@Override
	public Reply findById(Long id) {
		return super.get(id);
	}

	@Override
	public Reply findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply save(Reply bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Reply updateByUpdater(Updater<Reply> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Reply> getEntityClass() {
		return Reply.class;
	}

}
