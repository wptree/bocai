package com.bocai.manager;

import java.util.List;
import java.util.Set;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.vo.LatLng;
import com.bocai.vo.PlaceIndex;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

public interface PlaceManager extends BaseManager<Place>{
	
	public List<PlaceIndex> buildIndex(int pageSize, int pageAt);
	
	/**
	 * Load all the records to cache
	 * @return e.g {"杭州":"{place1,place2,place3}"}
	 */
	public Map<String, Set<Place>> getAllGroupedByCity();
	
	/**
	 * Get all the places entities by city
	 * @return
	 */
	public Set<Place> loadAllByCity(String cityName);
		
	public void initiazation();
	
	public List<Place> getPlacesMatchedByName(String name);
	
	public Pagination getDishesByPlace(Place place, int pageNo, int pageAt);
	
	public Pagination getAggSpotsByPlace(Long placeId , int pageNo, int pageAt);

	public Pagination getSpottedUserByPlace(Long placeId, int pageNo, int pageAt);
	
	/**
	 * 获取关注了这个店的人（粉丝团）
	 * @param placeId
	 * @param pageNo
	 * @param pageAt
	 * @return
	 */
	public Pagination getFollowers(Long placeId, int pageNo, int pageAt);
	
	public Pagination getFollowingFans(Long placeId, Long userId, int pageNo, int pageAt) ;
	
	/**
	 * To check if the same place already exist
	 * @param name
	 * @param secondaryName
	 * @return true if exists
	 */
	public List checkPlaceExist(String name, String secondaryName);
	
	public List<String> retrieveAllPlaceNames();
	
	public Pagination getSuggestedPlaces(Long userId);
	
	public Pagination getPlacesByMap(int pageAt,int pageSize, LatLng sw, LatLng ne);
	
	public Location getLocationOfPlace(Long placeId);
}
