package com.bocai.rest.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.rest.constant.RestConstants;
import com.bocai.rest.util.ObjectUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController
{

  @Autowired
  private UserManager userManager;
 
  public void setUserManager(UserManager userManager)
  {
    this.userManager = userManager;
  }
   
  //service for user register
@RequestMapping(method={RequestMethod.POST}, value={"/user_join.json"})
  public ModelAndView registerUser(@RequestBody Map<String,Object> body,HttpServletResponse response) {  
    ModelAndView view = new ModelAndView();
    view.setViewName(RestConstants.JSON_VIEW_NAME);
    String password = ObjectUtil.toString(body.get("password"));
    String passConfirm = ObjectUtil.toString(body.get("passConfirm"));
    String email =  ObjectUtil.toString(body.get("email"));
    
    if(!password.equals(passConfirm)){
    	 view.addObject("success", Boolean.valueOf(false));
    	 view.addObject("errorMsg", "password does not equal passwor confirm!");
    	 return view;
    }
    User user = new User();
    user.setFirstEmail(email);
    user.setPassword(password);
    user = this.userManager.registerUser(user);
  
    if (user == null) {
      view.addObject("success", Boolean.valueOf(false));
      view.addObject("errorMsg", "register fail,user already exist!");
    } else {
    	  view.addObject("success", Boolean.valueOf(true));
          view.addObject("id", user.getId());
          view.addObject("email", user.getFirstEmail());
          view.addObject("name", user.getName());
          view.addObject("avatar",user.getAvatar());
     //   view.addObject("avatar","http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
        //return the cookie  
          
        response.addCookie(new Cookie("userId",user.getId().toString()));
        response.addCookie(new Cookie("email",user.getFirstEmail()));
        response.addCookie(new Cookie("name",user.getName()));
    }
    return view;
  }

	//service for user login
  @RequestMapping(method={RequestMethod.POST}, value={"/user_login.json"})
  public ModelAndView userLogin(@RequestBody Map<String,Object> body,HttpServletResponse response) {
	String email = ObjectUtil.toString(body.get("email"));
	String password = ObjectUtil.toString(body.get("password"));
    User user = this.userManager.login(email, password);
    ModelAndView view = new ModelAndView();
    view.setViewName(RestConstants.JSON_VIEW_NAME);
    
    if (user == null) {
      view.addObject("success", Boolean.valueOf(false));
      view.addObject("errorMsg", "password incorrect!");
    } else {
      view.addObject("success", Boolean.valueOf(true));
      view.addObject("id", user.getId());
      view.addObject("email", user.getFirstEmail());
      view.addObject("name", user.getName());
    
      view.addObject("avatar", user.getAvatar());
      
  //  view.addObject("avatar","http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
      
      //return the cookie
      
      response.addCookie(new Cookie("userId",user.getId().toString()));
      response.addCookie(new Cookie("email",user.getFirstEmail()));
      response.addCookie(new Cookie("name",user.getName()));
      
      if(user.sinaConnected()){
    	  response.addCookie(new Cookie("sinaConnected","true"));
    	  response.addCookie(new Cookie("sinaToken",user.sinaAccount().getToken()));
    	  response.addCookie(new Cookie("sinaTokenSecret",user.sinaAccount().getTokenSecret()));
      }
      
    }
    return view;
  }
  
  //service to get detail info for user
  @RequestMapping(method={RequestMethod.GET}, value={"/people/{uid}"})
  public ModelAndView loadUser(@PathVariable("uid") long uid) {
	
	  User user = userManager.getById(uid);
	  ModelAndView view = new ModelAndView();
	  view.setViewName(RestConstants.JSON_VIEW_NAME);
	  if(user == null){
		  view.addObject("success", Boolean.valueOf(false));
	      view.addObject("errorMsg", "User with id'" + uid + "' does not exists!");
	      return view;
	  }
	  view.addObject("id", user.getId());
	  view.addObject("name", user.getName());
	  
	  view.addObject("avatar_50", user.getAvatar());
	  view.addObject("avatar_300", user.getAvatar());  
	  
//	  view.addObject("avatar_50", "http://tp4.sinaimg.cn/2082026727/50/5616926419/1");
//	  view.addObject("avatar_300", "http://tp4.sinaimg.cn/2082026727/50/5616926419/1");  
	  
	  view.addObject("location", user.getAddress());
	  view.addObject("noms_count", user.getNomCount());
	  view.addObject("tips_count", user.getMyComments().size());
	  view.addObject("sightings_count", user.getTotalSpotCount());
	  view.addObject("wants_count", user.getWantedCount());
	  view.addObject("notifications_count", 0); //none in bocai
	  view.addObject("recent_notifications_count", 0); //none in bocai
	  view.addObject("followings_count", user.getFollowedByNumber());
	  view.addObject("guides_count", 0); //NA
	  view.addObject("following", false);
	  return view;

  }
  
  
}