package com.bocai.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.constant.UserTokenStatus;
import com.bocai.common.constant.UserTokenType;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.dao.UserTokenDao;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserToken;

@Repository("userTokenDao")
public class UserTokenDaoImpl extends HibernateBaseDao<UserToken, Long> implements UserTokenDao {

	@Override
	public UserToken findById(Long id) {
		return super.get(id);
	}

	@Override
	public UserToken findUniqueBy(String prop, Object value) {
		UserToken entity = super.findUniqueByProperty(prop, value);
		return entity;
	}

	@Override
	public UserToken deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserToken save(UserToken bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public List<UserToken> findBy(String property, Object value) {
		return super.findByProperty(property, property);
	}

	@Override
	public List find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<UserToken> getEntityClass() {
		return UserToken.class;
	}

	public void updateBulk(String hql, Object... values) {
		super.update(hql, values);
	}
}
