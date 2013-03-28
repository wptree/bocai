package com.bocai.manager;

import java.util.LinkedList;
import java.util.List;

import com.bocai.common.constant.MapRequestType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.vo.PlaceIndex;
import com.bocai.vo.AggregatedSpotVo;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;

public interface AggregatedSpotManager extends BaseManager<AggregatedSpot>{
	
	public AggregatedSpot getDetailById(Long id);
	
	public List<Comment> getBestSpotComments(AggregatedSpot bean);
	
	public boolean hasAggSpotOnMap(MapRequest request);
	
	public Pagination getAggSpotsByMap(MapRequest request);
	
	public Pagination getAggSpotsByLocation(Location location);
	
	
	/**
	 * Get agg spots by place
	 * @param placeId Place id
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as agg spots list, the total page and current page no.
	 *                    Returned agg spots list is which user has sighted on
	 *                    the place.
	 */
	public Pagination getAggSpotsByPlace(Long placeId, int pageAt);
	
	/**
	 * Aggregate spot by checking its Dish and Place.
	 * Create an AggregatedSpot domain object and persist it when a new spot inputed.
	 * @param spot
	 * @param user  session user
	 */
	public AggregatedSpot aggregateSpot(Spot spot);

	public AggregatedSpot getMostSpottedByPlace(Long placeId);
	
	public Pagination getAggSpotsByDish(Long dishId, int pageSize, int pageAt);
	
    public Pagination searchAggspotByDishName(MapRequestType reqType, String dishName, Long userId, int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchAggspotByPlaceName(MapRequestType reqType, String placeName, Long userId, int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchAggspotByDishNameAndUserID(long userID, MapRequestType reqType, String dishName,int pageAt, int pageSize, LatLng sw, LatLng ne);
	
	public Pagination searchAggspotByPlaceNameAndUserID(long userID, MapRequestType reqType, String placeName,int pageAt, int pageSize, LatLng sw, LatLng ne);
}
