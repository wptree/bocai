package com.bocai.manager;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Dish;

public interface DishManager extends BaseManager<Dish> {
	
	public void initiazation();
	
	public List<String> retrieveAllDishNames();
	
	public Pagination getAggSpotsByDish(Long dishId , int pageNo, int pageAt);
	
	public Pagination getSuggestedDish(Long userId);
	
	public Dish makeSure(String dishName);
	
	public List<Dish> getDishsMatchedByName(String queryStr) ;
}
