package com.bocai.dao;

import java.util.List;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Notification;
import com.bocai.dao.domain.Post;
import com.bocai.dao.domain.User;

public interface NotificationDao extends BaseDao<Notification>{

	public void publish(User sourceUser, Long targetUserId, ActivityType type, Long targetId);
	
	public void publish(User sourceUser, Long targetUserId, ActivityType type, Post post, Comment comment);
	
	public List<Notification> fetch(User user);
	
	public Pagination fetchWithPagination(Long userId, int pageAt, int pageSize);
	
	public void updateNotiAsReaded(List<Long> ids);

	public Pagination getUnreadNoti(Long userId, int pageSize, int pageAt);
}
