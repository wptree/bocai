package com.bocai.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.common.page.Pagination;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;
import com.bocai.manager.CommentManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.rest.constant.RestConstants;
import com.bocai.rest.util.Tag2Text;


@Controller
public class ReviewController {

	@Autowired 
	private SpotManager spotManager;
	
	@Autowired
	private CommentManager commentManager;
	
	@Autowired 
	private UserManager userManager;
	
	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}

	//get spot aggSpot by id
	@RequestMapping(method=RequestMethod.GET,value="/reviews/{spotId}")
	public ModelAndView listReview(@PathVariable("spotId") long spotId,@CookieValue(value="userId",defaultValue = "-1") long userId ){
		
		Spot spot = spotManager.getById(spotId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		if(spot == null){
			mv.addObject("success", false);
			List<String> errors = new ArrayList<String>();
			mv.addObject("errors", errors);
			return mv;
		}
		mv.addObject("id", spot.getId());
		
	//	mv.addObject("note", spot.getDescription());
		
		mv.addObject("note", Tag2Text.stripTag2Text(spot.getDescription()));
		
		mv.addObject("created_at", spot.getCreatedAt());
		mv.addObject("updated_at", spot.getUpdatedAt());
		mv.addObject("taken_at", spot.getCreatedAt());
		mv.addObject("person_id", spot.getCreatedBy().getId());
		mv.addObject("sighting_id", spot.getId());
		mv.addObject("ribbons_count", spot.getGoodCount());
		mv.addObject("wants_count", spot.getGreatCount());
		if(userId == -1){
		  mv.addObject("wanted", false);
		  mv.addObject("nommed", false);
		  
		}else{
		 
		   mv.addObject("wanted", userManager.isWantedByUser(userId, spot.getAggSpot().getId()));
		   mv.addObject("nommed", userManager.isNommedByUser(userId, spot.getAggSpot().getId()));
		}
		mv.addObject("thumb_280", SystemConfig.imageContext()+"/"+ spot.getSpotImgPath(180));
		mv.addObject("thumb_90", SystemConfig.imageContext()+"/" + spot.getSpotImgPath(90));	
		mv.addObject("thumb_32", SystemConfig.imageContext()+"/" + spot.getSpotImgPath(32));
		
//		mv.addObject("thumb_280", "http://www.bocai007.com"+"/"+ spot.getSpotImgPath(280));
//		mv.addObject("thumb_90", "http://www.bocai007.com"+"/" + spot.getSpotImgPath(90));
//		mv.addObject("thumb_32", "http://www.bocai007.com"+"/" + spot.getSpotImgPath(32));	
		
		mv.addObject("great_shots_count", spot.getGreatCount());
	
		Map<String,Object> person = new HashMap<String,Object>();
		person.put("name",spot.getCreatedBy().getName());
		person.put("id",spot.getCreatedBy().getId());
		person.put("avatar",spot.getCreatedBy().getAvatar());
		
//		person.put("avatar","http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
		
		person.put("name",spot.getCreatedBy().getName());
	    
		mv.addObject("person", person);
		mv.addObject("location", spot.getPlace().getLocation().getCity());
		mv.addObject("sightings_count", spot.getAggSpot().getViewedNum());
	
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("id",spot.getDish().getId());
		item.put("name", spot.getDish().getName());
		mv.addObject("item", item);
		
		Map<String,Object> place = new HashMap<String,Object>();
		place.put("name", spot.getPlace().getFullName());
		place.put("id", spot.getPlace().getId());
		place.put("phone_number", spot.getPlace().getPhone());
		place.put("latitude", spot.getPlace().getLocation().getLat());
		place.put("longitude", spot.getPlace().getLocation().getLng());
		place.put("street_address", spot.getPlace().getLocation().getStreet());
		place.put("city", spot.getPlace().getLocation().getCity());
		place.put("state", spot.getPlace().getLocation().getProvince());
		place.put("country", spot.getPlace().getLocation().getCountry());
        
		mv.addObject("place", place);
		mv.addObject("followings_count",spot.getPlace().getFollowerCount()==null?0:spot.getPlace().getFollowerCount()); //TODO:
		mv.addObject("sightings_count", spot.getViewTimes());
		mv.addObject("following", false);
		
		return mv;
	}

	//get all comments for the aggSpot
	@SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.GET,value="/reviews/{spotId}/comments.json")
	public ModelAndView listComments(@PathVariable("spotId") long spotId){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
		Pagination pagination = commentManager.getCommentsOnSpot(spotId, 10, 0);
		
		List list = pagination.getList();
		List<Map> data = new ArrayList<Map>();
	
		for(Object obj : list){
			Map<String,Object> element = new HashMap<String,Object>();
			Map<String,Object> person = new HashMap<String,Object>();
			Comment comment = (Comment)obj;
			element.put("id",comment.getId());
			element.put("text", comment.getContent());
			element.put("created_at",comment.getCreatedAt());
			person.put("name",comment.getCreatedBy().getName());
			person.put("id",comment.getCreatedBy().getId());
			
			person.put("small_thumb",comment.getCreatedBy().getAvatar());
			
	//		person.put("small_thumb", "http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
			
			element.put("person",person);
			data.add(element);
		}
		mv.addObject("data",data);
		mv.addObject("success",true);
		return mv;
	}
	
	
	
	
}
