package com.bocai.rest.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.UserManager;
import com.bocai.rest.constant.RestConstants;
import com.bocai.service.SinaService;

@Controller
public class ThirdpartyController {
	
	@Autowired
	private SinaService sinaService;
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method=RequestMethod.POST, value="/thirdparty/sina_login.json")
	public ModelAndView sinaLogin(@RequestBody Map<String,Object> body,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);	
		String outSourceUserId = body.get("uid").toString();
	    String token = body.get("token").toString();
	    String tokenSecret = body.get("tokenSecret").toString();
	    
	    User user = userManager.exist(outSourceUserId,true);
		if (user == null) {
			UserThirdparty userThirdPartyVO = sinaService.persistUser(token,tokenSecret);
			user = userThirdPartyVO.getUser();
			sinaService.createFollowShip(SystemConfig.getBocaiSinaId(),userThirdPartyVO);
		} else {
			sinaService.updateUser(token,tokenSecret, user);
		}
		
		if (user == null) {
		     	mv.addObject("success", Boolean.valueOf(false));
		    } else {
		    	mv.addObject("success", Boolean.valueOf(true));
		    	mv.addObject("id", user.getId());

		    	response.addCookie(new Cookie("userId",user.getId().toString()));
//		    	response.addCookie(new Cookie("email",user.getFirstEmail()));
//		    	response.addCookie(new Cookie("name",user.getName()));
//		    	response.addCookie(new Cookie("sinaConnected","true"));
//		    	response.addCookie(new Cookie("sinaToken",user.sinaAccount().getToken()));
//		    	response.addCookie(new Cookie("sinaTokenSecret",user.sinaAccount().getTokenSecret()));
		    }
		
		return mv;
	}
	

}
