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
package com.bocai.dao.hibernate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.bocai.common.constant.FollowingType;
import com.bocai.common.constant.MapRequestType;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.UserDao;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.vo.Area;
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
 * 2011.03.20      | Shi,Tao      | Creation.
 * 
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateBaseDao<User, Long> implements UserDao {

	@Override
	public User findById(Long id) {
		return super.get(id);
	}

	@Override
	public User deleteById(Long id) {
		User entity = get(id);
		if(entity != null){
			getSession().delete(entity);
		}
		return entity;
	}
	
	public User save(User bean){
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}
	
	@Override
	public User findUniqueBy(String prop, Object value) {
		User entity = super.findUniqueByProperty(prop, value);
		return entity;
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public List<User> findByProperty(String property, Object value) {
		return super.findByProperty(property, property);
	}

	@Override
	public List<User> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	public Pagination getAggSpotsByMap(MapRequest request) {		
		if (request.getType().equalsIgnoreCase(MapRequestType.FOLLOWING.toString())) {
			return getFollowedAggSpotsByMap(request);
		} else if (request.getType().equalsIgnoreCase(MapRequestType.WANTTED.toString())) {
			return getWantedAggSpotsByMap(request);
		}
		return null;
	}
	
	private Pagination getWantedAggSpotsByMap(MapRequest request) {
		Area area = request.getArea();
		String hql = null;
		if (area.getSw().getLng() < area.getNe().getLng()) {
			hql = HQLContainer.getHql("get.aggspot.by.map.3");
		} else{
			hql = HQLContainer.getHql("get.aggspot.by.map.4");
		}
		String orderBy = "wanted.createdAt";
		hql = hql.replace("ORDER_BY_TERM", orderBy);
		Finder f = Finder.create(hql);
		f.setParam("lngmin", area.getSw().getLng(),new DoubleType());
		f.setParam("lngmax", area.getNe().getLng(),new DoubleType());
		f.setParam("latmin", area.getSw().getLat(),new DoubleType());
		f.setParam("latmax", area.getNe().getLat(),new DoubleType());
		f.setParam("userID", request.getUserId(), new LongType());
		
		Pagination p = find(f,request.getPageAt(),20);
		
		return p;
	}
	
	private Pagination getFollowedAggSpotsByMap(MapRequest request) {
		Area area = request.getArea();
		String hql = null;
		if (area.getSw().getLng() < area.getNe().getLng()) {
			hql = HQLContainer.getHql("get.aggspot.by.map.5");
		} else{
			hql = HQLContainer.getHql("get.aggspot.by.map.6");
		}
		String orderBy = "attention.createdAt";
		hql = hql.replace("ORDER_BY_TERM", orderBy);
		Finder f = Finder.create(hql);
		f.setParam("lngmin", area.getSw().getLng(),new DoubleType());
		f.setParam("lngmax", area.getNe().getLng(),new DoubleType());
		f.setParam("latmin", area.getSw().getLat(),new DoubleType());
		f.setParam("latmax", area.getNe().getLat(),new DoubleType());
		f.setParam("userID", request.getUserId(), new LongType());
		
		Pagination p = find(f,request.getPageAt(),20);
		
		return p;
	}

	@Override
	public Pagination getSpotsByUser(Long userid, int pageNo, int pageAt) {
		
		String hql = HQLContainer.getHql("get.spots.by.user");
		Finder f = Finder.create(hql);
		f.setParam("userID", userid, new LongType());
		
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getFollowed(Long userId, FollowingType type, int pageNo,
			int pageAt) {
		String hql = "";
		
		if (FollowingType.USER.equals(type)) {
			hql = HQLContainer.getHql("get.followinguser.by.user");
			
		}else if (FollowingType.PLACE.equals(type)) {
			hql = HQLContainer.getHql("get.followingplace.by.user");

		}
		
		Finder f = Finder.create(hql);
		f.setParam("userID", userId, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getUserFollow(Long userId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.followeduser.by.user");
		Finder f = Finder.create(hql);
		f.setParam("userID", userId, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getUserNomSpots(Long userid, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.nom.spots.by.user");
		Finder f = Finder.create(hql);
		f.setParam("userID", userid, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getUserWantedSpots(Long userid, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.wanted.spots.by.user");
		Finder f = Finder.create(hql);
		f.setParam("userID", userid, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getBestUsers(int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.best.user.pagination");
		Finder f = Finder.create(hql);
	
		Pagination p = findWithOrder(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getLatestUsers(int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.latest.user.pagination");
		Finder f = Finder.create(hql);
	
		Pagination p = findWithOrder(f,pageAt,pageNo);
		return p;
	}

	@Override
	public void addScore(User user, int score) {
		int newScore = user.getScore() + score;
		user.setScore(newScore);
		updateByUpdater(new Updater<User>(user));
	}

	@Override
	public void updateFeedFetchTimeline(User user) {
		user.setFeedFetchTimeline(System.currentTimeMillis());
		updateByUpdater(new Updater<User>(user));
	}

	@Override
	public void updateNotificationFetchTimeline(User user) {
		user.setNotificatonFetchTimeline(System.currentTimeMillis());
		updateByUpdater(new Updater<User>(user));
	}

	@Override
	public Pagination searchAggspotByUserName(MapRequestType reqType, String userName, int pageAt,int pageSize, LatLng sw, LatLng ne) {
		//String hql = HQLContainer.getHql("search.aggspot.by.user.name");
		String hql;
		if (sw.getLng() < ne.getLng()) {
			hql = HQLContainer.getHql("search.aggspot.by.user.name.and.map.1");
		} else {
			hql = HQLContainer.getHql("search.aggspot.by.user.name.and.map.2");
		}
		
		Finder f = Finder.create(hql);
		f.setParam("username", userName);
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination getUserCommentPage(Long userId, int pageSize, int pageAt) {
		
		String hql = HQLContainer.getHql("get.comments.by.user");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		
		Pagination p = find(f,pageAt,pageSize);
		Map<Long, Set<Comment>> spotMap = null;
		if(p.getList()!=null && !p.getList().isEmpty()){
			spotMap = new HashMap<Long, Set<Comment>>();
			List<Comment> comments = (List<Comment>) p.getList();
			for (Comment cmt : comments){
				if(cmt!=null && cmt.getPost()!=null){
					Set<Comment> cmts = spotMap.get(cmt.getPost().getId());
					if(cmts==null){
						cmts = new HashSet<Comment>();
						spotMap.put(cmt.getPost().getId(), cmts);
					}
					cmts.add(cmt);
				}
			}
		}
		if(spotMap != null && !spotMap.isEmpty()){
			hql = HQLContainer.getHql("get.spot.of.comment");
			List<Spot> spots = (List<Spot>)getSession()
				.createQuery(hql).setParameterList("ids", spotMap.keySet()).list();
			if(spots!=null && !spots.isEmpty()){
				for (Spot spot : spots){
					Set<Comment> cmts = spotMap.get(spot.getId());
					if(cmts!=null){
						for(Comment cmt: cmts){
							if(cmt!=null){
								cmt.setPost(spot);
							}
						}
					}
				}
			}
		}
		return p;
	}

	@Override
	public Pagination searchFollowingAggspotByUserName(MapRequestType reqType,
			String userName, int pageAt, int pageSize, LatLng sw, LatLng ne) {
		String hql;
		if (sw.getLng() < ne.getLng()) {
			hql = HQLContainer.getHql("search.following.aggspot.by.user.name.and.map.1");
		} else {
			hql = HQLContainer.getHql("search.following.aggspot.by.user.name.and.map.2");
		}
		
		Finder f = Finder.create(hql);
		f.setParam("username", userName);
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public Pagination searchUsers(String keyword, int pageSize, int pageAt) {
		String hql = HQLContainer.getHql("search.user.by.user.name");
		Finder f = Finder.create(hql);
		f.setParam("username", "%" + keyword + "%");
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public Pagination getWanttedUsers(Long aggSpotId, int at, int size) {
		String hql = HQLContainer.getHql("get.wantted.user.by.agg.spot.id");
		Finder f = Finder.create(hql);
		f.setParam("aggSpotId", aggSpotId);
		Pagination p = find(f,at,size);
		return p;
	}

	@Override
	public Pagination getNomedUsers(Long aggSpotId, int at, int size) {
		String hql = HQLContainer.getHql("get.nomed.user.by.agg.spot.id");
		Finder f = Finder.create(hql);
		f.setParam("aggSpotId", aggSpotId);
		Pagination p = find(f,at,size);
		return p;
	}
	
	@Override
	public Pagination getSpotsOnFollowingPeopleByUserId(Long userId, int at, int size){
		String hql = HQLContainer.getHql("get.spots.on.following.people.by.user.id");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		
		Pagination p = find(f, at, size);
		return p;
	}
	
	@Override
	public Pagination getSpotsOnFollowingPlaceByUserId(Long userId, int at, int size){
		String hql = HQLContainer.getHql("get.spots.on.following.place.by.user.id");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		
		Pagination p = find(f, at, size);
		return p;
	}

	@Override
	public Long getFollowTosCount(Long userId) {
		String hql = "select count(follow) from User user left join user.followTos follow where user.id=:userId";
		Query query = super.getSession().createQuery(hql);
		query.setLong("userId", userId);
		List scalar = query.list();
		Long result = (Long) scalar.get(0);
		return result;
	}
	
	@Override
	public Long getFollowingPlacesCount(Long userId) {
		String hql = "select count(place) from User user left join user.followingPlaces place where user.id=:userId";
		Query query = super.getSession().createQuery(hql);
		query.setLong("userId", userId);
		List scalar = query.list();
		Long result = (Long) scalar.get(0);
		return result;
	}

	@Override
	public List<String> retrieveAllUserNames() {
		Criteria crit = getSession().createCriteria(User.class); 
		ProjectionList projList = Projections.projectionList(); 
		projList.add(Projections.distinct(Projections.property("name")));
		crit.setProjection(projList);
		List<String> result = crit.list(); 						
		return result;
	}
	

	@Override
	public void evictCollection(String roleName, Long id) {
		this.sessionFactory.evictCollection(roleName, id);
	}

	public Pagination getMostSpottedUsers(Long uid, int at, int size){
		String hql = HQLContainer.getHql("get.most.spotted.user");
		Finder f = Finder.create(hql);
		f.setParam("userId", uid, new LongType());
		Pagination p = find(f, at, size);
		return p;
	}
}
