package com.bocai.manager;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.vo.FrontResultSet;
import com.bocai.vo.MapRequest;
import com.bocai.vo.SpotUploadRequest;
import com.bocai.vo.SpotVo;

/**
 * @author Shi,Tao
 * Manager interface for Spot domain, Manager layer is used to operate specific domain object,
 * include getting domain object detail from persistent layer, fetching a list, saving, updating and deleting... 
 */
public interface SpotManager extends BaseManager<Spot>{
	
	public boolean persistSpot(SpotUploadRequest request);
	
	public Long save(Spot bean);
	
	public Long update(Spot bean);
	
	public void delete(Spot bean);
	
	public Spot getDetailById(Long id);
	
	public List<Comment> getComments(Spot bean);
	
	public Pagination getSpotsByMap(MapRequest request);
	
	
	
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
	public FrontResultSet<Spot> getSpotsWithCommentsOnAggSpot(Long aggSpotId, int spotPageNo, int commentPageNo, int pageAt);
	
	/**
	 * Get spots list for a dish
	 * @param dishId Id for a specific dish 
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as spots list, the total page and current page no.
	 *                    Returned spots list is which user has sighted on
	 *                    the dish.
	 */
	public Pagination getSpotsByDish(Long dishId, int pageNo, int pageAt);
	
	/**
	 * Get spots list for a location
	 * @param placeId Id for a specific place
	 * @return Pagination Pagination object contain the paginating info,
	 *                    such as spots list, the total page and current page no.
	 *                    Returned spots list is which user has sighted on
	 *                    the place.
	 */
	public Pagination getSpotsByPlace(Long placeId, int pageNo, int pageAt);
	
	/**
	 * Check spot with specified dish and place already exist
	 * @param dishId
	 * @param placeId
	 * @return Spot object if exists or null 
	 */
	public Spot checkSpotByDishAndPlace(Long dishId,Long placeId);
	
	/**
	 * Get comments list for a spot
	 * @param spot
	 * @param pageNo
	 * @param pageAt
	 * @return
	 */
	public Pagination getComments(Spot spot, int pageNo, int pageAt);
	
	public Spot getLastSpotByUser(Long userId);
	
	public void removeSpot(User user, Spot spot);
	
	public Pagination getLatestSpotsByCity(String city, int pageNo, int pageAt);
	
	public void addSpot(Spot spot, File orgImg,double imgRotateDegree, String contextPath);

}
