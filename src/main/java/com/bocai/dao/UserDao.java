/*
 * Licensed to the bocai007.com under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The bocai.com licenses this file to You under the bocai.com License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.bocai007.com/licenses/LICENSE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bocai.dao;
import java.util.List;

import com.bocai.common.constant.FollowingType;
import com.bocai.common.constant.MapRequestType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.User;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;

/**
 * <p>
 * Data access object class of User domain.
 * </p>
 * 
 * @author Shi,Tao
 * @since 0.1
 * @version 0.1
 * 
 * Revision History
 * DATE            | REVISER      | REASON
 * ---------------------------------------
 * 2011.04.25      | Shi,Tao      | Creation.
 * 
 */
public interface UserDao extends BaseDao<User>{
	
	public Pagination getAggSpotsByMap(MapRequest request);
	
	public Pagination getSpotsByUser(Long userid, int pageNo, int pageAt);
	
	/*targetType can be "user"|"dish"|"place"|"spotGuide"*/
	public Pagination getFollowed(Long userId, FollowingType type, int pageNo, int pageAt);

	public Pagination getUserFollow(Long userId, int pageNo, int pageAt);
	
	public Pagination getUserNomSpots(Long userid, int pageNo, int pageAt);

	public Pagination getUserWantedSpots(Long userid, int pageNo, int pageAt);
	
	public Pagination getBestUsers(int pageNo, int pageAt);
	
	public Pagination getLatestUsers(int pageNo, int pageAt);
	
	public void addScore(User user, int score);
	
	public void updateFeedFetchTimeline(User user);
	
	public void updateNotificationFetchTimeline(User user);
	
	public Pagination searchAggspotByUserName(MapRequestType reqType, String userName,int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchFollowingAggspotByUserName(MapRequestType reqType, String userName,int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchUsers(String keyword, int pageSize, int pageAt);
	
	public Pagination getUserCommentPage(Long userId, int pageSize, int pageAt);
	
	public Pagination getWanttedUsers(Long aggSpotId, int at, int size);
	
	public Pagination getNomedUsers(Long aggSpotId, int at, int size);
	
	public Pagination getSpotsOnFollowingPeopleByUserId(Long userId, int at, int size);
	
	public Pagination getSpotsOnFollowingPlaceByUserId(Long userId, int at, int size);
	
	public Long getFollowTosCount(Long userId);
	
	public Long getFollowingPlacesCount(Long userId);
	
	public List<String> retrieveAllUserNames();
	
	public void evictCollection(String roleName, Long id);
	
	public Pagination getMostSpottedUsers(Long uid, int at, int size);
}
