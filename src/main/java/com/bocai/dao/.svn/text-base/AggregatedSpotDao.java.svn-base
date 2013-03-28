package com.bocai.dao;

import com.bocai.common.constant.MapRequestType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;

public interface AggregatedSpotDao extends BaseDao<AggregatedSpot>{
	
	public Pagination getAggSpotsByMap(MapRequest request);
	
	public Pagination getAggSpotsByPlace(Long placeId);
	
	public Pagination searchAggspotByDishName(MapRequestType reqType, String dishName, Long userId, int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchAggspotByPlaceName(MapRequestType reqType, String placeName, Long userId, int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchAggspotByDishNameAndUserID(long userID, MapRequestType reqType, String dishName,int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchAggspotByPlaceNameAndUserID(long userID, MapRequestType reqType, String placeName,int pageAt, int pageSize, LatLng sw, LatLng ne);
	
}
