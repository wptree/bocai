package com.bocai.dao;

import com.bocai.dao.domain.UserToken;

public interface UserTokenDao extends BaseDao<UserToken> {
	
	public void updateBulk(String hql, Object... values);
	
}
