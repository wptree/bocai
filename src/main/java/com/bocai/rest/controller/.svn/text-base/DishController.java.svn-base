package com.bocai.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Place;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.impl.PlaceManagerImpl;
import com.bocai.rest.constant.RestConstants;
import com.bocai.rest.util.ObjectUtil;
import com.bocai.search.engine.IndexCache;
import com.bocai.search.engine.IndexConstant;
import com.bocai.search.engine.TokenIndex;
import com.bocai.util.StringUtil;
import com.bocai.vo.Token;

@Controller
public class DishController {

	private static final Logger logger = Logger.getLogger(DishController.class);
	
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private DishManager dishManager;
	@Autowired 
	private IndexCache indexCache;
	private TokenIndex dishIndex = null ;
	
	/**
	 * 
	 * @param placeId,获取一家店所有的菜
	 * 返回的数据格式为mockData方法中的list
	 * @return
	 */
	
	@RequestMapping(method=RequestMethod.GET, value="/places/{placeId}/items.json")
	public ModelAndView getDishListByPlace(@PathVariable("placeId") long placeId){
		

		Place place = placeManager.getById(placeId);
		Pagination page = placeManager.getAggSpotsByPlace(placeId, 20, 0);
		List list = new ArrayList();
		for(Object ob : page.getList()){
			AggregatedSpot aggSpot = (AggregatedSpot)ob;
			Map<String,Object> dishMap = new HashMap<String,Object>();
			Dish dish = aggSpot.getDish();
			dishMap.put("id", dish.getId());
			dishMap.put("name", dish.getName());
			list.add(dishMap);
		
		}
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		mv.addObject("items",list);
		return mv;
	}
	

	
	
	/**
	 * 
	 * @param query,搜索菜的关键字
	 * @return
	 */
	
	
	@RequestMapping(method=RequestMethod.GET, value="/items/search.json")
	public ModelAndView getDishListMatchedByName(@RequestParam("query") String query){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);	
		List list = new ArrayList();
		if(!StringUtil.isNULL(query)){
			List<Dish> dishList = dishManager.getDishsMatchedByName(ObjectUtil.toDecodedString(query));
			for(Dish dish : dishList){
				Map<String,Object> dishMap = new HashMap<String,Object>();
				dishMap.put("id", dish.getId());
				dishMap.put("name", dish.getName());
				list.add(dishMap);
			  
			}
		}
		
	  mv.addObject("items",list);
	   
	  return mv;

	}
	
	
	
	
}
