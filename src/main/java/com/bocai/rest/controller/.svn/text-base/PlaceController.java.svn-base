package com.bocai.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.common.page.Pagination;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Place;
import com.bocai.manager.PlaceManager;
import com.bocai.rest.constant.RestConstants;
import com.bocai.rest.util.LocationUtil;
import com.bocai.rest.util.ObjectUtil;
import com.bocai.vo.LatLng;

@Controller
public class PlaceController {

	@Autowired
	private PlaceManager placeManager;
	
	private static final Logger logger = Logger.getLogger(PlaceController.class);
	

	@RequestMapping(method=RequestMethod.GET, value="/places/search.json")
	public ModelAndView queryPlaceByMap(@RequestParam Map<String,Object> params){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		
		List<Place> placeList = null;
		
		double longitude = ObjectUtil.toDouble(params.get("longitude"));
		double latitude = ObjectUtil.toDouble(params.get("latitude"));
		if(Double.isNaN(longitude) || Double.isNaN(latitude)){
			mv.addObject("results",new ArrayList());
			mv.addObject("total",0);
			mv.addObject("error", "location not specified.");
			return mv;
		}
		
		Object queryObj = params.get("query");
		if(!ObjectUtil.isNull(queryObj)){
			String query = ObjectUtil.toDecodedString(queryObj);
			placeList = placeManager.getPlacesMatchedByName(query);
			
		}else{
			
			int pageAt = 0;
			int pageSize = 10;
			double within = 5.0;
			double latSpan = within / 111.0; // 1 degree equals 111.0km
			double lngSpan = within / (111.0 * Math.cos(longitude));
			
			 LatLng sw = new LatLng();
			 LatLng ne = new LatLng();
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
			 		 
			Pagination pagination = placeManager.getPlacesByMap(pageAt, pageSize, sw, ne);
			placeList = (List<Place>) pagination.getList();
			 
		}
		
		List[] placeAndDistList = calcDistAndSort(placeList,latitude,longitude);
		List<Place> newPlaceList = placeAndDistList[0];
		List<Double> distList = placeAndDistList[1];
		
		List data = new ArrayList();
		int index = 0;
		for(Place place : newPlaceList){
			Map<String,Object> placeElement = new HashMap<String,Object>();
			placeElement.put("id",place.getId());
			placeElement.put("name",place.getName());
			placeElement.put("secondName", place.getSecondaryName());
			placeElement.put("phone_number",place.getPhone());
			placeElement.put("street_address",place.getLocation().getStreet());
			placeElement.put("full_address",place.getLocation().getFullAddress());
			placeElement.put("city",place.getLocation().getCity());
			placeElement.put("state",place.getLocation().getProvince());
			placeElement.put("country",place.getLocation().getCountry());
			placeElement.put("postal_code",place.getLocation().getPostalCode());
			placeElement.put("latitude",place.getLocation().getLat());
			placeElement.put("longitude",place.getLocation().getLng());
			placeElement.put("sightings_count",place.getSpottedCount());
			placeElement.put("following",false);  //not used
			placeElement.put("distance",distList.get(index));
			index ++;
			data.add(placeElement);
		}
		
		mv.addObject("results",data);
		mv.addObject("total", 0);
		
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/places/{placeId}/sightings.json")
	public ModelAndView listAggSpotByPlaceId(@PathVariable("placeId") long placeId,@RequestParam Map<String,Object> params){
		
		ModelAndView mv = new ModelAndView( );
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		
		int pageNum = ObjectUtil.toInt(params.get("page"));
		int pageSize = ObjectUtil.toInt(params.get("per_page"));
		
		if(pageNum < 0 || pageSize < 0){
			mv.addObject("error", "page number or pageSize not specified.");
			return mv;
		}
		
		Pagination pagination = placeManager.getAggSpotsByPlace(placeId, pageSize, pageNum);
		List list = pagination.getList();
		List data = new ArrayList();
		
		for(Object obj : list){
			AggregatedSpot aggSpot = (AggregatedSpot)obj;
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
			 
//			 person.put("avatar", "http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
			 
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
		
		mv.addObject("data",data);
		mv.addObject("total",pagination.getTotalPage());
		
		return mv;
	}
	
	/*
	 * list, place list
	 * return: 1.sorted list
	 * 		   2.distance list
	 */
	
	private List[] calcDistAndSort(List<Place> list ,double lat,double lng){
		List<Place> placeList = new LinkedList<Place>();
		List<Double> distList = new LinkedList<Double>();
		if(list == null || list.size() == 0){
			return new List[]{placeList,distList};
		}
		
		Place p = list.remove(0);
		double d = LocationUtil.gps2m(p.getLocation().getLat(), p.getLocation().getLng(), lat, lng);
		distList.add(d);
		placeList.add(p);
		
		for(Place obj : list){
			Place place = obj;
			double dist = LocationUtil.gps2m(place.getLocation().getLat(),place.getLocation().getLng(),lat,lng);
			for(int i = 0 ; i< distList.size();i++){
				if(dist < distList.get(i)){
					placeList.add(i, place);
					distList.add(i, dist);
					break;
				}else {
					if(i == distList.size() - 1){
						placeList.add(place);
						distList.add(dist);
						break;
					}else{
						continue;
					}
				}
			}
		}
		
		return new List[]{placeList,distList};
		
	}
	
}
