package com.bocai.dao;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Spot;

public interface SpotDao extends BaseDao<Spot>{
	/**
	 * Get spots list on a specific aggregated spot
	 * @param aggSpotId Id for the aggregated spot
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as spots list, the total page and current page no.
	 */
	public Pagination getSpotsOnAggSpot(Long aggSpotId, int pageNo, int pageAt);
	
	/**
	 * Get spots list on a specific aggregated spot
	 * @param aggSpotId Id for the aggregated spot
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as spots list, the total page and current page no.
	 */
//	public Pagination getSpotsWithCommentsOnAggSpot(Long aggSpotId, int pageAt);
	
	/**
	 * Get spots list for a dish
	 * @param dishId Id for a specific dish 
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as spots list, the total page and current page no.
	 *                    Returned spots list is which user has sighted on
	 *                    the dish.
	 */
	public Pagination getSpotsByDish(Long dishId, int pageNo,int pageAt);
	
	/**
	 * Get spots list for a location
	 * @param placeId Id for a specific place
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as spots list, the total page and current page no.
	 *                    Returned spots list is which user has sighted on
	 *                    the place.
	 */
	public Pagination getSpotsByPlace(Long placeId, int pageNo,int pageAt);
	
	/**
	 * Get the latest spot list by city
	 * @param city
	 * @param pageNo
	 * @param pageAt
	 * @return
	 */
	public Pagination getLatestSpotsByCity(String city,int pageNo, int pageAt);
}
