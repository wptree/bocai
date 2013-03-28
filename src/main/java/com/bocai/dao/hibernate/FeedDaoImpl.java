package com.bocai.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.FeedDao;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Feed;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Post;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;

@Repository("feedDao")
public class FeedDaoImpl extends HibernateBaseDao<Feed, Long> implements FeedDao  {

	@Override
	public Feed findById(Long id) {
		return super.get(id);
	}

	@Override
	public Feed findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed save(Feed bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public List<Feed> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Feed> getEntityClass() {
		// TODO Auto-generated method stub
		return Feed.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	@Override
	public Pagination fetch(Long userId, ActivityType type, int pageSize, int pageAt) {
		String hql = HQLContainer.getHql("get.feed.pagination.by.user");
		Finder f = Finder.create(hql);
		f.setParam("followerID", userId);
		f.setParam("type", type);		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public void publish(User sourceUser, ActivityType type, Post post) {
		String hql = HQLContainer.getHql("get.followerIDs.by.user");
		List<Long> followerIDList = find(hql,sourceUser.getId());
		if (followerIDList != null) {
			Iterator<Long> it = followerIDList.iterator();
			while(it.hasNext()) {
				Long followerID = it.next();
				if (followerID == null) {
					continue;
				}
				Feed feed = new Feed();
				feed.setConnected(true);
				feed.setReceiverId(followerID);
				feed.setSourceUserId(sourceUser.getId());
				feed.setSourceUserName(sourceUser.getName());
				feed.setSourceUserAvatar(sourceUser.getAvatar());
				feed.setTargetId(post.getId());
				feed.setTargetUserId(((Spot)post).getSpotedBy().getId());
				feed.setTargetUserName(((Spot)post).getSpotedBy().getName());
				feed.setTimeline(System.currentTimeMillis());
				feed.setType(type);
				feed.setImgType(((Spot)post).getImgType());
				feed.setPostWay(((Spot)post).getPostBy());
				feed.setDishName(((Spot)post).getDish().getName());
				feed.setPlaceName(((Spot)post).getPlace().getName());
				feed.setComment(((Spot)post).getDescription());
				feed.setPlaceSecondaryName(((Spot)post).getPlace().getSecondaryName());
				
				save(feed);
			}
		}
	}
	
	@Override
	public void publish(User sourceUser, ActivityType type, Comment comment) {
		String hql = HQLContainer.getHql("get.followerIDs.by.user");
		List<Long> followerIDList = find(hql,sourceUser.getId());
		if (followerIDList != null) {
			Iterator<Long> it = followerIDList.iterator();
			while(it.hasNext()) {
				Long followerID = it.next();
				if (followerID == null) {
					continue;
				}
				Feed feed = new Feed();
				feed.setConnected(true);
				feed.setReceiverId(followerID);
				feed.setSourceUserId(sourceUser.getId());
				feed.setSourceUserName(sourceUser.getName());
				feed.setSourceUserAvatar(sourceUser.getAvatar());
				feed.setTargetId(comment.getPost().getId());
				feed.setTimeline(System.currentTimeMillis());
				feed.setType(type);
				feed.setImgType(((Spot)comment.getPost()).getImgType());
				feed.setPostWay(((Spot)comment.getPost()).getPostBy());
				feed.setDishName(((Spot)comment.getPost()).getDish().getName());
				feed.setPlaceName(((Spot)comment.getPost()).getPlace().getName());
				feed.setComment(comment.getContent());
				feed.setPlaceSecondaryName(((Spot)comment.getPost()).getPlace().getSecondaryName());
				feed.setTargetUserId(((Spot)comment.getPost()).getSpotedBy().getId());
				feed.setTargetUserName(((Spot)comment.getPost()).getSpotedBy().getName());
				
				save(feed);
			}
		}
	}

	@Override
	public void publish(User sourceUser, ActivityType type, AggregatedSpot spot) {
		String hql = HQLContainer.getHql("get.followerIDs.by.user");
		List<Long> followerIDList = find(hql,sourceUser.getId());
		if (followerIDList != null) {
			Iterator<Long> it = followerIDList.iterator();
			while(it.hasNext()) {
				Long followerID = it.next();
				if (followerID == null) {
					continue;
				}
				Feed feed = new Feed();
				feed.setConnected(true);
				feed.setReceiverId(followerID);
				feed.setSourceUserId(sourceUser.getId());
				feed.setSourceUserName(sourceUser.getName());
				feed.setTargetId(spot.getId());
				feed.setTimeline(System.currentTimeMillis());
				feed.setType(type);
				feed.setSourceUserAvatar(sourceUser.getAvatar());
				feed.setImgType(spot.getLastSpot().getImgType());
				feed.setPostWay(spot.getLastSpot().getPostBy());
				//feed.setComment(post.getContent());
				feed.setDishName(spot.getLastSpot().getDish().getName());
				feed.setPlaceName(spot.getLastSpot().getPlace().getName());
				feed.setPlaceSecondaryName(spot.getLastSpot().getPlace().getSecondaryName());
				
				save(feed);
			}
		}
	}
	
	@Override
	public List<Feed> fetch(User follower, ActivityType type) {
		String hql = HQLContainer.getHql("get.feeds.by.user");
		List<Feed> feedList = find(hql, follower.getId(), type, follower.getFeedFetchTimeline());
		return feedList;
	}

	@Override
	public void undoConnected(Long sourceUserID, Long followerID) {
		String hql = HQLContainer.getHql("undo.feed.connected");
		update(hql,sourceUserID, followerID);
	}

	@Override
	public Pagination fetch(Long userId, int pageSize, int pageAt) {
		String hql = HQLContainer.getHql("get.all.type.feed.pagination.by.user");
		Finder f = Finder.create(hql);
		f.setParam("followerID", userId);
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public Pagination fetch(long sourceUserId, int pageSize, int pageAt) {
		String hql = HQLContainer.getHql("get.feeds.by.source.user");
		Finder f = Finder.create(hql);
		f.setParam("sourceUserId", sourceUserId);
		
		Pagination p = find(f,pageAt,pageSize);

		return p;
	}

	@Override
	public void publish(User sourceUser, User targetUser) {
		String hql = HQLContainer.getHql("get.followerIDs.by.user");
		List<Long> followerIDList = find(hql,sourceUser.getId());
		if (followerIDList != null) {
			Iterator<Long> it = followerIDList.iterator();
			while(it.hasNext()) {
				Long followerID = it.next();
				if (followerID == null) {
					continue;
				}
				Feed feed = new Feed();
				feed.setConnected(true);
				feed.setReceiverId(followerID);
				feed.setSourceUserId(sourceUser.getId());
				feed.setSourceUserName(sourceUser.getName());
				feed.setTargetUserId(targetUser.getId());
				feed.setTargetUserName(targetUser.getName());
				feed.setTimeline(System.currentTimeMillis());
				feed.setType(ActivityType.USER_FOLLOW_USER);
				feed.setSourceUserAvatar(sourceUser.getAvatar());
				save(feed);
			}
		}
		
	}

	@Override
	public void publish(User sourceUser, Place place) {
		String hql = HQLContainer.getHql("get.followerIDs.by.user");
		List<Long> followerIDList = find(hql,sourceUser.getId());
		if (followerIDList != null) {
			Iterator<Long> it = followerIDList.iterator();
			while(it.hasNext()) {
				Long followerID = it.next();
				if (followerID == null) {
					continue;
				}
				Feed feed = new Feed();
				feed.setConnected(true);
				feed.setReceiverId(followerID);
				feed.setSourceUserId(sourceUser.getId());
				feed.setSourceUserName(sourceUser.getName());
				feed.setTargetId(place.getId());
				feed.setTimeline(System.currentTimeMillis());
				feed.setType(ActivityType.USER_FOLLOW_PLACE);
				feed.setSourceUserAvatar(sourceUser.getAvatar());
				feed.setPlaceName(place.getName());
				feed.setPlaceSecondaryName(place.getSecondaryName());
				
				save(feed);
			}
		}
	}

}
