package com.bocai.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.common.constant.MapRequestType;
import com.bocai.common.constant.SearchKeywordType;
import com.bocai.common.constant.SearchTargetType;
import com.bocai.common.page.Pagination;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.hibernate.AggregatedSpotDaoImpl;
import com.bocai.dao.hibernate.SpotDaoImpl;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.SearchManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.manager.impl.AggregatedSpotManagerImpl;
import com.bocai.manager.impl.SearchManagerImpl;
import com.bocai.manager.impl.SpotManagerImpl;
import com.bocai.rest.constant.RestConstants;
import com.bocai.rest.util.LocationUtil;
import com.bocai.rest.util.ObjectUtil;
import com.bocai.rest.util.Tag2Text;
import com.bocai.vo.AggregatedSpotVo;
import com.bocai.vo.Area;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;


@Controller
public class SightingController {
	
	@Autowired 
	private AggregatedSpotManager aggSpotManager;
	@Autowired 
	private UserManager userManager;
	@Autowired 
	private SpotManager spotManager;
	@Autowired
	private SearchManager searchManager;
	
	private static Log logger = LogFactory.getLog(SightingController.class);

	//service for search latest,hostest,nearest spot,and spot query
	@RequestMapping(method=RequestMethod.GET, value={"/sightings.json"})
	public ModelAndView listSighting(@RequestParam Map<String,Object> params){
		
		ModelAndView mv = new ModelAndView( );
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		
	    String query = ObjectUtil.toDecodedString(params.get("query"));  //the dish to search
	    String sort = ObjectUtil.toString(params.get("sort"));
	    String type = ObjectUtil.toString(params.get("type")); //search type,default is dish
		int pageNum = ObjectUtil.toInt(params.get("page"));
		if(pageNum == Integer.MIN_VALUE){
			pageNum = 0;
		}

		int pageSize = ObjectUtil.toInt(params.get("per_page"));
		if(pageSize == Integer.MIN_VALUE){
			pageSize = 10;
		}
		
		double longitude = ObjectUtil.toDouble(params.get("longitude"));
		double latitude = ObjectUtil.toDouble(params.get("latitude"));
		double within = ObjectUtil.toDouble(params.get("within"));
		
		if(Double.isNaN(longitude) || Double.isNaN(latitude) || Double.isNaN(within)){
			mv.addObject("data",new ArrayList());
			mv.addObject("total", -1);
			mv.addObject("error", "location not specified.");
			return mv;
		}
		
		 MapRequestType requestType;
		 if(sort.equals("latest")){
			 requestType = MapRequestType.LATEST;
		 }else if (sort.equals("best")){
			 requestType = MapRequestType.HOTEST;
		 }else if(sort.equals("nearest")){
			 requestType = MapRequestType.LATEST; 
		 }
		 else{
			 requestType = MapRequestType.LATEST;
		}
		 
		 LatLng sw = new LatLng();
		 LatLng ne = new LatLng();
		 updateSwAndNe(sw,ne,latitude,longitude,within);
		 Pagination pagination;
		 List aggSpotList;
		 
		 if (type != null && type.equals("place")){
			 pagination = searchManager.searchAggspotByPlaceName(requestType, query, null, pageNum, pageSize, sw, ne);
			 aggSpotList = pagination.getList();
		 }else{
			 pagination = aggSpotManager.searchAggspotByDishName(requestType,query,null, pageNum, pageSize, sw, ne);
			 aggSpotList = pagination.getList();
			 if(aggSpotList.size() < 5){
				 updateSwAndNe(sw,ne,latitude,longitude,within * 2.0);
				 pagination = aggSpotManager.searchAggspotByDishName(requestType,query,null, pageNum, pageSize, sw, ne);
				 aggSpotList = pagination.getList();
			 }
		 }
		
		List<Object> data = null;
		if(sort.equals("nearest")){
			List[] spotAndDistList =  calcDistAndSort(aggSpotList,latitude,longitude);
			data = makeUpData(spotAndDistList[0],spotAndDistList[1]);
		}else{
			data = makeUpData(aggSpotList,null);
		}
	
		mv.addObject("data",data);
		mv.addObject("total",pagination.getTotalPage());
		return mv;
	}
	
	
	
	private void updateSwAndNe(LatLng sw,LatLng ne,double latitude,double longitude,double within){
		 double latSpan = within / 111.0; // 1 degree equals 111.0km
		 double lngSpan = within / (111.0 * Math.cos(longitude));
		
		 if(latSpan > 0){
			 sw.setLat(latitude - latSpan);
			 ne.setLat(latitude + latSpan);
		 }else{
			 sw.setLat(latitude + latSpan);
			 ne.setLat(latitude - latSpan);
		 }
		 
		 if(lngSpan > 0){
			 sw.setLng(longitude - lngSpan);
			 ne.setLng(longitude + lngSpan);
		 }else{
			 sw.setLng(longitude + lngSpan);
			 ne.setLng(longitude - lngSpan);
		 }
	}
	
	
	private List[] calcDistAndSort(List<AggregatedSpot> aggList,double lat,double lng){
		List<AggregatedSpot> retAggList = new LinkedList<AggregatedSpot>();
		List<Double> distList = new LinkedList<Double>();
		if(aggList == null || aggList.size() == 0){
			return new List[]{retAggList,distList};
		}
		
		AggregatedSpot spot = aggList.remove(0);
		double d = LocationUtil.gps2m(spot.getPlace().getLocation().getLat(), spot.getPlace().getLocation().getLng(), lat, lng);
		retAggList.add(spot);
		distList.add(d);
		
		for(AggregatedSpot aggSpot : aggList){
			double dist = LocationUtil.gps2m(aggSpot.getPlace().getLocation().getLat(), aggSpot.getPlace().getLocation().getLng(), lat, lng);
			for(int i = 0 ; i< distList.size();i++){
				if(dist < distList.get(i)){
					retAggList.add(i,aggSpot);
					distList.add(i,dist);
					break;
				}else{
					if(i == distList.size() - 1){
						retAggList.add(aggSpot);
						distList.add(dist);
						break;
					}else{
						continue;
					}
				}
			}
		}
		
		return new List[]{retAggList,distList};
	}
	
	
	private List<Object> makeUpData(List<AggregatedSpot> list,List<Double> distList){
		List<Object> data = new LinkedList<Object>();
		 int index = 0;
		 for(AggregatedSpot aggSpot : list){
			 Map<String,Object> sighting = new HashMap<String,Object>();
			 sighting.put("id", aggSpot.getId());
			 sighting.put("created_at",aggSpot.getCreatedAt());
			 sighting.put("updated_at",aggSpot.getUpdatedAt()); 
			 sighting.put("reviews_count", aggSpot.getTastedNum());
			 sighting.put("latitude",aggSpot.getPlace().getLocation().getLat());
			 sighting.put("longitude", aggSpot.getPlace().getLocation().getLng());
			 sighting.put("ribbons_count", aggSpot.getNomNum());
			 sighting.put("last_review_at", aggSpot.getUpdatedAt());
			 sighting.put("wants_count", aggSpot.getWantedNum());

			 if(distList != null){
				 sighting.put("distance",distList.get(index));
			 }
			 index = index + 1;
			 sighting.put("creator_id", aggSpot.getCreatedBy().getId());
			
			 sighting.put("thumb_280", SystemConfig.imageContext()+"/"+ aggSpot.getSpotImgPath(280));		
			 sighting.put("thumb_90", SystemConfig.imageContext()+"/"+ aggSpot.getSpotImgPath(90));
			 sighting.put("thumb_32", SystemConfig.imageContext()+"/"+ aggSpot.getSpotImgPath(32));
			 
//			 sighting.put("thumb_280", "http://www.bocai007.com"+"/"+ aggSpot.getSpotImgPath(280));		
//			 sighting.put("thumb_90", "http://www.bocai007.com"+"/"+ aggSpot.getSpotImgPath(90));
//			 sighting.put("thumb_32", "http://www.bocai007.com"+"/"+ aggSpot.getSpotImgPath(32));
			 
			 sighting.put("review_id", aggSpot.getLastSpot().getId());
			 sighting.put("current_review_taken_at", aggSpot.getLastSpot().getCreatedAt());
			 
			 Map<String,Object> person = new HashMap<String,Object>();
			 person.put("id", aggSpot.getCreatedBy().getId());
			 person.put("name", aggSpot.getCreatedBy().getName());
			 person.put("avatar", aggSpot.getCreatedBy().getAvatar());
			 
		//	 person.put("avatar", "http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
			 
			 person.put("location", aggSpot.getCreatedBy().getAddress());
			 person.put("sightings_count", aggSpot.getCreatedBy().getTotalSpotCount());
			 sighting.put("person", person);
			 
			 Map<String,Object> item = new HashMap<String,Object>();
			 item.put("id", aggSpot.getLastSpot().getId());
			 item.put("name", aggSpot.getLastSpot().getDish().getName());
			 sighting.put("item", item);
			 
			 Map<String,Object> place = new HashMap<String,Object>();
			 place.put("id", aggSpot.getPlace().getId());
			 place.put("name", aggSpot.getPlace().getFullName());
			 place.put("phone_number", aggSpot.getPlace().getPhone());
			 place.put("street_address", aggSpot.getPlace().getLocation().getStreet());
			 place.put("street_address_2", aggSpot.getPlace().getLocation().getStreet());
			 place.put("city", aggSpot.getPlace().getLocation().getCity());
			 place.put("state", aggSpot.getPlace().getLocation().getProvince());
			 place.put("country", aggSpot.getPlace().getLocation().getCountry());
			 place.put("postal_code", aggSpot.getPlace().getLocation().getPostalCode());
			 place.put("latitude", aggSpot.getPlace().getLocation().getLat());
			 place.put("longitude", aggSpot.getPlace().getLocation().getLng());
			 place.put("sightings_count", aggSpot.getSharedNum());
			 place.put("following", false);
			 place.put("followings_count", 0);
			 sighting.put("place", place);
			 
			 data.add(sighting);
		 }
		  return data;
	}
	
	//load all spots that was aggregated, the aggSpot id is sightingId
	//get cookies from client to determin if user want,nom the dish
	@RequestMapping(method=RequestMethod.GET, value={"/sightings/{sightingId}/reviews.json"})
	public ModelAndView getSightingInfo(@PathVariable("sightingId") long sightingId,@CookieValue(value="userId",defaultValue = "-1") long userId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		
		List<Object> data = new LinkedList<Object>();
		
		AggregatedSpot aggSpot	= aggSpotManager.getById(sightingId);
		if(aggSpot == null){
			mv.addObject("data",data);
			mv.addObject("total",0);
			return mv;
		}
		
		for(Spot spot : aggSpot.getSpots()){
			Map<String,Object> sighting = new HashMap<String,Object>();
			sighting.put("id", spot.getId());
		//	sighting.put("note", spot.getDescription());
			sighting.put("note", Tag2Text.stripTag2Text(spot.getDescription()));
			sighting.put("created_at", spot.getCreatedAt());
			sighting.put("updated_at", spot.getUpdatedAt());
			sighting.put("taken_at", spot.getCreatedAt());
			sighting.put("person_id", spot.getCreatedBy().getId());
			sighting.put("sighting_id", spot.getId());
			sighting.put("ribbons_count", spot.getGreatCount()); //how many people love this dish
			sighting.put("wants_count", spot.getGoodCount());  //how many people want to taste this dish
			sighting.put("wanted", userManager.isWantedByUser(userId, sightingId));  
			sighting.put("nommed", userManager.isNommedByUser(userId, sightingId)); 
			
			sighting.put("thumb_280", SystemConfig.imageContext() + "/" + aggSpot.getSpotImgPath(280));	
			sighting.put("thumb_90", SystemConfig.imageContext()+"/" + aggSpot.getSpotImgPath(90));
			sighting.put("thumb_32", SystemConfig.imageContext()+"/" + aggSpot.getSpotImgPath(32));
			
//			sighting.put("thumb_280",  "http://www.bocai007.com/" + spot.getSpotImgPath(280));
//			sighting.put("thumb_90", "http://www.bocai007.com/" + aggSpot.getSpotImgPath(90));
//			sighting.put("thumb_32", "http://www.bocai007.com/" + aggSpot.getSpotImgPath(32));
			
			sighting.put("comments_count", spot.getCommentNum());
			
		    Map<String,Object> person = new HashMap<String,Object>();
		    person.put("id", spot.getCreatedBy().getId());
		    person.put("name", spot.getCreatedBy().getName());
		    
		    person.put("avatar", spot.getCreatedBy().getAvatar());
		   
	//	    person.put("avatar", "http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
		    
		    person.put("location", spot.getCreatedBy().getCityName());
		    person.put("sightings_count", spot.getCreatedBy().getTotalSpotCount());
		    sighting.put("person", person);
		 
		    Map<String,Object> item = new HashMap<String,Object>();
		    item.put("id", spot.getId());
		    item.put("name", spot.getDish().getName());
		    sighting.put("item", item);
		 
		    Map<String,Object> place = new HashMap<String,Object>();
		    place.put("id", spot.getPlace().getId());
		    place.put("name", spot.getPlace().getFullName());
		    place.put("phone_number", spot.getPlace().getPhone());
		    place.put("latitude", spot.getPlace().getLocation().getLat());
		    place.put("longitude", spot.getPlace().getLocation().getLng());
		    place.put("street_address", spot.getPlace().getLocation().getStreet());
		    place.put("city", spot.getPlace().getLocation().getCity());
		    place.put("state",spot.getPlace().getLocation().getProvince());  
		    place.put("country", spot.getPlace().getLocation().getCountry());
		    place.put("postal_code", spot.getPlace().getLocation().getPostalCode());
		    place.put("street_address_2", aggSpot.getPlace().getLocation().getStreet());
		    sighting.put("place", place);
		    
		    data.add(sighting);
		}
		 
		mv.addObject("data",data);
		mv.addObject("total",data.size());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST, value={"/sightings/{sightingId}/want.json"})
	public ModelAndView userWantDish(@PathVariable("sightingId") long sightingId,@CookieValue(value="userId",defaultValue = "-1") long userId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		
		if(userId == -1){
			 mv.addObject("success",false);
			 mv.addObject("error","please login first");
			 return mv;
		}
			
		long aggSpotId = spotManager.getById(sightingId).getAggSpot().getId();
		if(!userManager.isWantedByUser(userId, aggSpotId)){
			userManager.addWantedSpot(userId, aggSpotId);
		}
        
		mv.addObject("success",true);
      
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST, value={"/sightings/{sightingId}/nom.json"})
	public ModelAndView userNomDish(@PathVariable("sightingId") long sightingId,@CookieValue(value="userId",defaultValue = "-1") long userId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		if(userId == -1){
			 mv.addObject("success",false);
			 mv.addObject("error","please login first");
			 return mv;
		}
		
		long aggSpotId = spotManager.getById(sightingId).getAggSpot().getId();
		
		if(!userManager.isNommedByUser(userId, aggSpotId)){
			userManager.addNomedSpot(userId, aggSpotId);
		}
        
		mv.addObject("success",true);
        
        return mv;
	}
	
}
