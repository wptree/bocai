package com.bocai.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.Notification;
import com.bocai.dao.domain.User;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.NotificationManager;
import com.bocai.manager.UserManager;

@Service("notificationManager")
@Transactional
public class NotificationManagerImpl extends ManagerHelper implements NotificationManager {
	
	@Override
	public Long save(Notification bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Notification bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Notification bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Notification> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notification getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void publish(User sourceUser, Long targetUserId, ActivityType type,
			Long targetId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination fetch(Long userId,int pageSize, int pageAt) {
		Pagination page = notificationDao.fetchWithPagination(userId, pageAt, pageSize);
		
		return page;
	}

	@Override
	public List<Notification> fetch(User user) {
		List<Notification> list = notificationDao.fetch(user);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long newNotificationCount(Long userId) {
		String hql = HQLContainer.getHql("new.notification.count");
		List<Long> count = notificationDao.find(hql, userId);
		if (count != null) {
			if (count.size() > 0) {
				return count.get(0);
			}
		}
		return 0L;
	}
	
	@Override
	public void updateNotiAsReaded(List<Long> ids){
		notificationDao.updateNotiAsReaded(ids);
	}
	
	@Override
	public Pagination getUnreadNoti(Long userId, int pageSize, int pageAt){
		return notificationDao.getUnreadNoti(userId, pageSize, pageAt);
	}


}
