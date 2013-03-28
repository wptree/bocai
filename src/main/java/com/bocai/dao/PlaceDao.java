package com.bocai.dao;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Place;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;
import com.bocai.vo.PlaceIndex;

public interface PlaceDao extends BaseDao<Place>{
	
	public List<PlaceIndex> buildIndex(int pageSize, int pageAt);
	
	public Pagination getDishesByPlace(Place place, int pageNo, int pageAt);
	
	public Pagination getAggSpotsByPlace(Long placeId , int pageNo, int pageAt);
	
	public Pagination getSpottedUserByPlace(Long placeID, int pageNo, int pageAt);

	public Pagination getFollowers(Long placeId, int pageNo, int pageAt);
	
	public Pagination getFollowingFans(Long placeId, Long userId, int pageNo, int pageAt);

	public List<String> retrieveAllPlaceNames();
	
	public Pagination getMostSpottedPlaces(Long uid, int at, int size);
	
	public Pagination getPlacesByMap(int pageAt,int pageSize, LatLng sw, LatLng ne);
}
