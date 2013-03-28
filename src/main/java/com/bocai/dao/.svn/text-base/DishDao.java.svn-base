package com.bocai.dao;
import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Dish;

public interface DishDao extends BaseDao<Dish>{
	
	List<String> retrieveAllDishNames();
	
	public Pagination getAggSpotsByDish(Long dishId, int pageNo, int pageAt);
	
	public Pagination getMostSpottedDishs(Long userId, int at, int size);
}
