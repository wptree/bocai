package com.bocai.dao;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Invitation;

public interface InvitationDao extends BaseDao<Invitation>{
	
	public Pagination fetchInvited(Long userId, int pageSize, int pageAt) ;

}
