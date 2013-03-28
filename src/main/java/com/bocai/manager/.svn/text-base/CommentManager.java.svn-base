package com.bocai.manager;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.User;
import com.bocai.vo.CommentVO;

public interface CommentManager extends BaseManager<Comment>{
	
	public Long save(Comment bean, String contextPath);
	
	public Pagination getCommentsOnSpot(Long spotId, int pageSize, int pageAt);
	
	public Pagination getUserCommentPage(Long userId, int pageSize, int pageAt);
		
	public Pagination getCommentsOnPeople(Long uid, int size, int at);
}
