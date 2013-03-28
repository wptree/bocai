package com.bocai.manager.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Feed;
import com.bocai.dao.domain.Post;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.manager.FeedManager;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.UserManager;

@Service("feedManager")
@Transactional
public class FeedManagerImpl extends ManagerHelper implements FeedManager {

	@Override
	public Long save(Feed bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Feed bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Feed bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Feed> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Feed getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pagination fetch(Long userId, ActivityType type, int pageSize,int pageAt) {
		Pagination page = feedDao.fetch(userId, type, pageSize, pageAt);
		return page;
	}
	
	
	@Override
	public List<Feed> fetch(User follower, ActivityType type) {
		List<Feed> list =feedDao.fetch(follower, type);
		return list;
	}

	@Override
	public Long newFeedCount(User user, Long timeline) {
		String hql = HQLContainer.getHql("new.feed.count");
		List<Long> count = feedDao.find(hql, user.getId(),timeline == null ? user.getFeedFetchTimeline() : timeline);
		if (count != null) {
			if (count.size() > 0) {
				return count.get(0);
			}
		}
		return 0l;
	}

	@Override
	public Pagination fetch(Long userId, int pageSize, int pageAt) {
		Pagination page = feedDao.fetch(userId, pageSize, pageAt);
		return page;
	}

	@Override
	public Pagination fetchFeedBySrcUser(Long sourceUserId, int pageSize, int pageAt) {
		Pagination page = feedDao.fetch(sourceUserId, pageSize, pageAt);
		return page;
	}

}
