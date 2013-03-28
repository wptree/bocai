package com.bocai.manager;
import java.util.List;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Feed;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;

public interface FeedManager extends BaseManager<Feed> {
	
	public Pagination fetch(Long userId, ActivityType type, int pageSize, int pageAt);
	
	public List<Feed> fetch(User user, ActivityType type);
	
	public Pagination fetch(Long userId, int pageSize, int pageAt);
	
	public Pagination fetchFeedBySrcUser(Long sourceUserId, int pageSize, int pageAt);
	
	/**
	 * Get the new feed number against last query timeline.
	 * @param user
	 * @return
	 */
	public Long newFeedCount(User user, Long timeline);
}
