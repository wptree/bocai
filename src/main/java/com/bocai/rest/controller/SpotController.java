package com.bocai.rest.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.User;
import com.bocai.dao.hibernate.UserDaoImpl;
import com.bocai.exception.SpotException;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.manager.impl.PlaceManagerImpl;
import com.bocai.manager.impl.SpotManagerImpl;
import com.bocai.rest.constant.RestConstants;
import com.bocai.util.MapUtil;
import com.bocai.util.StringUtil;
import com.bocai.vo.SpotUploadRequest;


@Controller
public class SpotController {

	@Autowired 
	private SpotManager spotManager;
	@Autowired 
	private PlaceManager placeManager;
	@Autowired 
	private UserManager userManager;
	@Autowired
	private DishManager dishManager;

	private static final Logger logger = Logger.getLogger(SpotController.class);
	
	private int[] imageSizeArray = SystemConfig.getSpotImgSizeDefineArray();

	//upload spot
	@RequestMapping(method=RequestMethod.POST, value="/reviews.json")
	public ModelAndView addSpot(MultipartHttpServletRequest request){
	
		logger.info("start to persist spot============");
		logger.info("content.contentLength=" + request.getContentLength());
		logger.info("content.contentType=" + request.getContentType());
		
		ModelAndView mv = new ModelAndView( );
		mv.setViewName(RestConstants.JSON_VIEW_NAME);
			
		String dishName = request.getParameter("itemName");
		
		String placeName = request.getParameter("placeName");   
	
		String placeSecName = request.getParameter("placeSecondName");  
		
		String placeStreetAddr = request.getParameter("placeStreetAddr"); 
		String placeLat = request.getParameter("placeLat");
		String placeLng = request.getParameter("placeLnt");
		String placeCity = request.getParameter("placeCity");
		
		//should be "Android" or "iPhone"
		String source = request.getParameter("reviewSource"); 
		String comment = request.getParameter("reviewNote");
		String userId = request.getParameter("reviewUserId"); 
		String price = request.getParameter("price");
		
		if(StringUtil.isNULL(userId)){
			mv.addObject("success", false);
			mv.addObject("returnMsg","请先登录，如果没有账户请注册");
			logger.info("==================user not logged in");
			return mv;
		}
		
		if(StringUtil.isNULL(dishName)){
			mv.addObject("success", false);
			mv.addObject("returnMsg","菜的名字不能少");
			logger.info("==================dish Name is required");
			return mv;
		}else{
			logger.info("Start to persist dish....");
			persistDish(dishName);
			logger.info("Persist dish finished....");
		}
		
		if(StringUtil.isNULL(placeName)){
			mv.addObject("success", false);
			mv.addObject("returnMsg","店名不能少");
			logger.info("==================place Name is required");
			return mv;
		}
		
		if(StringUtil.isNULL(placeStreetAddr)){
			mv.addObject("success", false);
			mv.addObject("returnMsg","店的地址不够详细呢");
			logger.info("==================place address is required");
			return mv;
		}
		
		if(StringUtil.isNULL(placeLat) || StringUtil.isNULL(placeLng)){

			logger.info("placeCity=" + placeCity);
			logger.info("placeStreetAddr=" + placeStreetAddr);
			String result = MapUtil.getLatlng(placeCity, placeStreetAddr);	
			logger.info("MapUtil.result=" + result);
			String[] arr = result.split(",");
			placeLat = arr[2];
			placeLng = arr[3];
				
		}
		
		boolean send2Sina = Boolean.valueOf(request.getParameter("send2Sina"));
		
		MultipartFile multipartFile = request.getFile("reviewPhoto");
		File dir = new File(SystemConfig.uploadTempDir());
		if(!dir.exists()){
			dir.mkdir();
		}
		Date d = new Date();
		File imageFile = new File(dir,d.getTime() + "_" + multipartFile.getOriginalFilename());
		try {
			logger.info("Start to transform file....");
			multipartFile.transferTo(imageFile);
			logger.info("Finished to transform file...");
		} catch (Exception e) {
			mv.addObject("success", false);
			mv.addObject("returnMsg","菜的图片上传失败，请稍后再试 ");
			System.out.println(e.getMessage());
			return mv;
		}
		logger.info("Get current user====");
		User currentUser = userManager.getById(Long.valueOf(userId));
		logger.info("Current user=" + currentUser.toString());
		SpotUploadRequest uploadRequest = new SpotUploadRequest();
		
		logger.info("Start to persist Place");
		Long placeId = persistPlace(placeCity, placeName, placeSecName, "", placeStreetAddr,placeLat,placeLng);
		logger.info("Persist place end");
		
		uploadRequest.setSpotPlaceId(placeId.toString());

		uploadRequest.setDishImageRoot(SystemConfig.getStaticRoot()+"/bocai/spot");
		
		uploadRequest.setSpotPrice(price);  
		uploadRequest.setCurrentUser(currentUser);
		uploadRequest.setSpotImg(imageFile);
		uploadRequest.setImgSizeArray(imageSizeArray);
		uploadRequest.setDescription(comment);
		uploadRequest.setSend2Sina(send2Sina);
		uploadRequest.setPostBy(source);
		uploadRequest.setSpotDishName(dishName);
		
		logger.info("begin to persist spot======");
		
		boolean success = spotManager.persistSpot(uploadRequest);
		logger.info("=================spot persist result=====" + success);
		imageFile.delete();
		mv.addObject("success", success);
		return mv;
	}
	
	private Long persistDish(String dishName){
		if(StringUtil.isNULL(dishName)){
			return 0L;
		}else{
			Dish dish = dishManager.getUniqueByProperty("name", dishName);
			if(dish!=null){
				return dish.getId();
			}else{
				dish = new Dish();
				dish.setName(dishName);
				return dishManager.save(dish);
			}
			
		}
		
	}
	
	private Long persistPlace(String city, String primaryName, String secondaryName, String phone, String address,String lat, String lng){
		List list = placeManager.checkPlaceExist(primaryName, secondaryName);
		Long id = 0L;
		if(list.size()>0){
			id = (Long) list.get(0);
		}else{
			Place place = new Place();
			place.setName(primaryName);
			place.setSecondaryName(secondaryName);
			StringBuilder sb =  new StringBuilder();
			sb.append(place.getName());
			if(!StringUtil.isNULL(place.getSecondaryName())){
				sb.append(AppConstant.LEFT_PTS)
					.append(place.getSecondaryName())
					.append(AppConstant.RIGHT_PTS);
			}
			place.setFullName(sb.toString());
			Location lc = new Location();
			lc.setCity(city);
			lc.setStreet(address);
			lc.setLat(Double.parseDouble(lat));
			lc.setLng(Double.parseDouble(lng));
			place.setLocation(lc);
			
			place.setPhone(phone);
			place.setVenueType("地方特色菜");
			
			id = placeManager.save(place);
		}
		return id;
	}
	
}
