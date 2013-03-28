package com.bocai.dao;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Comment;

public interface CommentDao extends BaseDao<Comment> {
	
	public Pagination getCommentsOnSpot(Long spotId, int pageNo, int pageAt);
	
	public Pagination getCommentsOnPeople(Long uid, int size, int at);
}
