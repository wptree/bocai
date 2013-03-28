package com.bocai.dao;

import java.util.List;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Feed;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Post;
import com.bocai.dao.domain.User;

public interface FeedDao extends BaseDao<Feed> {
	
	public void publish(User sourceUser, ActivityType type, Post post);
	
	public void publish(User sourceUser, User targetUser);
	
	public void publish(User sourceUser, Place place);
	
	public void publish(User sourceUser, ActivityType type, Comment comment);
	
	public void publish(User sourceUser, ActivityType type, AggregatedSpot spot);
	
	public Pagination fetch(Long userId, ActivityType type, int pageSize, int pageAt);
	
	public Pagination fetch(Long userId, int pageSize, int pageAt);
	
	public List<Feed> fetch(User user, ActivityType type);
	
	public Pagination fetch(long sourceUserId, int pageSize, int pageAt);
	
	public void undoConnected(Long sourceUserID, Long followerID);
}
