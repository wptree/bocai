package com.bocai.manager;

import java.util.List;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Notification;
import com.bocai.dao.domain.User;

public interface NotificationManager extends BaseManager<Notification>{
	
	/**
	 * Publish notification to user
	 * @param targetUserId
	 * @param type
	 * @param targetPostId
	 */
	public void publish(User sourceUser, Long targetUserId, ActivityType type, Long targetId);
	/**
	 * Get @param User's unread notification
	 * Update User's notificatonFetchTimeline field before return.
	 * @param user
	 * @param type
	 * @param pageSize
	 * @param pageAt
	 * @return
	 */
	public Pagination fetch(Long userId,int pageSize, int pageAt);
	
	public List<Notification> fetch(User user);
	
	/**
	 * Get the new notification number against last query timeline
	 * @param user
	 * @return
	 */
	public Long newNotificationCount(Long userId); 
	
	public void updateNotiAsReaded(List<Long> ids);
	
	public Pagination getUnreadNoti(Long userId, int pageSize, int pageAt);
}
