package com.bocai.dao;

import com.bocai.dao.domain.UserTaskCompKey;
import com.bocai.dao.domain.UserTaskXref;

public interface UserTaskXrefDao extends BaseDao<UserTaskXref> {
	
	public UserTaskXref load(UserTaskCompKey id);
	
}
