package com.bocai.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.NotificationDao;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Feed;
import com.bocai.dao.domain.Notification;
import com.bocai.dao.domain.Post;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.util.StringUtil;

@Repository("notificationDao")
public class NotificationDaoImpl extends HibernateBaseDao<Notification, Long> implements
		NotificationDao {

	@Override
	public Notification findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification save(Notification bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public Notification updateByUpdater(Updater<Notification> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return super.find(queryString, values);
	}

	@Override
	public void publish(User sourceUser, Long targetUserId, ActivityType type, Long targetId) {
		Notification notify = new Notification();
		notify.setSourceUserId(sourceUser.getId());
		notify.setSourceUserName(sourceUser.getName());
		notify.setTargetId(targetId);
		notify.setReceiverId(targetUserId);
		notify.setTimeline(System.currentTimeMillis());
		notify.setType(type);
		notify.setUserAvatar(sourceUser.getAvatar());
		
		save(notify);
	}

	@Override
	protected Class<Notification> getEntityClass() {
		// TODO Auto-generated method stub
		return Notification.class;
	}

	@Override
	public List<Notification> fetch(User user) {
		String hql = HQLContainer.getHql("get.notification.by.user");
		List<Notification> notifyList = find(hql, user.getId(), user.getNotificatonFetchTimeline());
		return notifyList;
	}

	@Override
	public Pagination fetchWithPagination(Long userId, int pageAt, int pageSize) {
		String hql = HQLContainer.getHql("get.notification.pagination.by.user");
		Finder f = Finder.create(hql);
		f.setParam("followerID", userId);
		
		Pagination p = findWithOrder(f,pageAt,pageSize);
		return p;
	}

	@Override
	public void publish(User sourceUser, Long targetUserId, ActivityType type, Post post, Comment comment) {
		Spot spot = null; Comment cmt = null;
		if(post instanceof Spot){
			spot = (Spot)post;
		}else if(post instanceof Comment){
			cmt = (Comment)post;
		}
		Notification notify = new Notification();
		notify.setSourceUserId(sourceUser.getId());
		notify.setSourceUserName(sourceUser.getName());
		notify.setReceiverId(targetUserId);
		notify.setTimeline(System.currentTimeMillis());
		notify.setType(type);
		notify.setUserAvatar(sourceUser.getAvatar());
		
		notify.setComment(comment.getContent());
		if(spot!=null){
			notify.setTargetId(spot.getId());
			notify.setDishName(spot.getDish().getName());
			notify.setPlaceName(spot.getPlace().getName());
			notify.setPlaceSecondaryName(spot.getPlace().getSecondaryName());
			notify.setPlaceFullName(spot.getPlace().getFullName());
		}
		if(cmt!=null){
			notify.setTargetId(cmt.getPost().getId());
			notify.setBaseCmt(cmt.getContent());
		}
		save(notify);
	}

	@Override
	public void updateNotiAsReaded(List<Long> ids) {
		String hql = HQLContainer.getHql("update.notification.as.readed");
		this.getSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
	}

	@Override
	public Pagination getUnreadNoti(Long userId, int pageSize, int pageAt){
		String hql = HQLContainer.getHql("get.unread.noti");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		Pagination p = this.findWithOrder(f, pageAt, pageSize);
		return p;
	}
}
