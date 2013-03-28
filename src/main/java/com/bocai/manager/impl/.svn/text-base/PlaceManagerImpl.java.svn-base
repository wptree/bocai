package com.bocai.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.PlaceManager;
import com.bocai.vo.LatLng;
import com.bocai.vo.PlaceIndex;
import com.bocai.vo.Token;
import com.googlecode.ehcache.annotations.Cacheable;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

@Service("placeManager")
@Transactional
public class PlaceManagerImpl extends ManagerHelper implements PlaceManager{
	public Long save(Place bean){
		if(bean!=null){
			placeDao.save(bean);
			return bean.getId();
		}else{
			return null;
		}
	}

	@Override
	public Long update(Place bean) {
		placeDao.updateByUpdater(new Updater<Place>(bean));
		return bean.getId();
	}

	@Override
	public void delete(Place bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Place> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Place getUniqueByProperty(String prop, Object value) {
		if(prop!=null&&!"".equals(prop)){
			return placeDao.findUniqueBy(prop, value);
		}else{
			return null;
		}
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Set<Place>> getAllGroupedByCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Place> loadAllByCity(String cityName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void initiazation() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Place> getPlacesMatchedByName(String queryStr) {
		String hql = HQLContainer.getHql("query.place.like.name");
		List<Place> placeList = placeDao.find(hql, "%"+queryStr+"%");
		return placeList;
	}

	@Override
	public Place getById(Long id) {
		return placeDao.findById(id);
	}

	@Override
	public Pagination getDishesByPlace(Place place, int pageNo, int pageAt) {
		return placeDao.getDishesByPlace(place, pageNo, pageAt);
	}

	@Override
	@Cacheable(cacheName="aggSpotsByPlaceCache")
	public Pagination getAggSpotsByPlace(Long placeId, int pageNo, int pageAt) {
		return placeDao.getAggSpotsByPlace(placeId, pageNo, pageAt);
	}

	@Override
	@Cacheable(cacheName="spotersOfPlaceCache")
	public Pagination getSpottedUserByPlace(Long placeID, int pageNo, int pageAt) {
		return placeDao.getSpottedUserByPlace(placeID, pageNo, pageAt);
	}

	@Override
	@Cacheable(cacheName="followersOfPlaceCache")
	public Pagination getFollowers(Long placeId, int pageNo, int pageAt) {
		return placeDao.getFollowers(placeId, pageNo, pageAt);
	}
	
	@Override
	@Cacheable(cacheName="myFollowingOnPlaceCache")
	public Pagination getFollowingFans(Long placeId, Long userId, int pageNo, int pageAt) {
		return placeDao.getFollowingFans(placeId, userId, pageNo, pageAt);
	}

	@Override
	public List checkPlaceExist(String name, String secondaryName) {
		String hql = HQLContainer.getHql("check.place.by.placename");
		List<Long> idList = placeDao.find(hql,name,secondaryName);
		return idList;
	}

	@Override
	@Cacheable(cacheName="allPlaceNameCache")
	public List<String> retrieveAllPlaceNames() {
		return placeDao.retrieveAllPlaceNames();
	}
	
	public Pagination getSuggestedPlaces(Long userId){
		return placeDao.getMostSpottedPlaces(userId, 0, 12);
	}

	@Override
	public Pagination getPlacesByMap(int pageAt,int pageSize, LatLng sw, LatLng ne) {
		return placeDao.getPlacesByMap(pageAt,pageSize,sw,ne);
	}

	@Override
	public Location getLocationOfPlace(Long placeId) {
		Place place = placeDao.findById(placeId);
		Location location = place.getLocation();
		location.getLng();
		return location;
	}

	@Override
	@Cacheable(cacheName = "buildPlaceIndex")
	public List<PlaceIndex> buildIndex(int pageSize, int pageAt) {
		return placeDao.buildIndex(pageSize, pageAt);
	}
}
