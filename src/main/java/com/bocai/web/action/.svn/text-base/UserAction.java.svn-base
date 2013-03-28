/*
 * Licensed to the bocai007.com under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The bocai.com licenses this file to You under the bocai.com License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.bocai007.com/licenses/LICENSE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bocai.web.action;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.exception.ManagerException;
import com.bocai.manager.UserManager;
import com.bocai.service.SinaService;

/**
 * <p>
 * Action class for User.
 * </p>
 * 
 * @author Shi,Tao
 * @since 0.1
 * @version 0.1
 * 
 * Revision History
 * DATE            | REVISER      | REASON
 * ---------------------------------------
 * 2011.03.20      | Shi,Tao      | Creation.
 * 
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction<User>{
	
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	private static final String LOGIN = "login";
	private static final String LOGIN_SUCCESS = "login_succ";
	private static final String LOGIN_ERROR = "login_error";
	private static final String LOGOUT_SUCCESS = "logout_succ";
	private static final String JSON_SUCC = "json_succ";
	private static final String JSON_SIGNUP_SUCC = "json_signup_succ";
	private static final String JSON_BACKURL="json_backUrl";
	private static final String MY_SET="myset";
	
	@Autowired
	private UserManager userManager;
	private Long id;
	private boolean rememberMe;
	private String returnMsg;
	private User user;
	private String passwordRecon;
	private String authCode;
	private Boolean exist;
	
	private String firstEmail;
	
	
	
	public String home(){
		return "success";
	}


	public String login() {
		// login user
		try{
			if(user.getFirstEmail()!=null){
				user = userManager.login(user.getFirstEmail(), user.getPassword());
			}else{
				returnMsg = "登录邮箱不能为空。";
				return LOGIN_ERROR;
			}
		}catch(ManagerException me){
			returnMsg = me.getMessage();
			return LOGIN_ERROR;
		}
		// save user into cookie
		if (user != null) {
			session.put(AppConstant.SESSION_LOGIN_USER, user);
			if(rememberMe){
				saveCookie(user);
			}else{
				clearCookie(AppConstant.COOKIE_LOGIN_USER_ID);
			}
			returnMsg = "登录成功！";
			return LOGIN_SUCCESS;
		}
		return LOGIN_ERROR;
	}
	
	public String logout(){
		//clear cookie
		clearCookie(AppConstant.COOKIE_LOGIN_USER_ID);
		//delete user from session
		if (session.containsKey(AppConstant.SESSION_LOGIN_USER)) {
			User user = getSessionUser();
			userManager.endThirdPartySession(user);
			logger.debug("remove user " + user.getFirstEmail() + " from session");
			session.remove(AppConstant.SESSION_LOGIN_USER);
		}
		return LOGIN;
	}
	
	
	public String authCode(){
		String authCodeInSession = (String) this.session.get("Auth_Code");
		if(!authCode.equals(authCodeInSession)){
			exist = true;
		}else{
			exist = false;
		}
		return JSON_SUCC;
	}

	
	
	public String saveDetail(){
		userManager.update(user);
		returnMsg = "资料更新成功。";
		return JSON_SUCC;
	}
	
	public String signUp(){
		try{
			String authCodeInSession = (String) this.session.get("Auth_Code");
			exist = false;
			if(!authCode.equals(authCodeInSession)){
				returnMsg = "验证码不正确。";
			}
			if(user.getFirstEmail()==null){
				returnMsg = "登录邮箱不能为空。";
			}else if(user.getPassword() == null){
				returnMsg = "密码不能为空。";
			}else if(!user.getPassword().equals(passwordRecon)){
				returnMsg = "重复密码不一致。";
			}
			else{
				user.setFirstEmail(user.getFirstEmail().toLowerCase());
				userManager.registerUser(user);
				returnMsg = "注册成功！";
				exist = true;
			}
		}catch(ManagerException me){
			returnMsg = me.getMessage();
		}
		return JSON_SIGNUP_SUCC;
	}
	
	public String exist(){
		exist = userManager.exist(getFirstEmail(),false)==null?false:true;
		return JSON_SUCC;
	}
	
	public void refreshSessionUser(){
		User user = getSessionUser();
		if(user!=null){
//			user = userManager.getUserById(user.getId());
//			user = userManager.reload(user);
			session.put(AppConstant.SESSION_LOGIN_USER, user);
		}
	}
	
	public String detail() throws Exception{
		return DETAIL;
	}

	@Override
	public String delete() throws Exception {
		//userManager.deleteUser(user);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	public String loginInput() throws Exception{
		return LOGIN;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id!=null && id!=0L){
			user = userManager.getById(id);
		}else{
			user = new User();
		}
	}
	
	public String getMyset(){
		refreshSessionUser();
		return MY_SET;
	}

	@Override
	public String save() throws Exception {
		userManager.save(user);
		this.session.put(AppConstant.SESSION_LOGIN_USER, user);
		return DETAIL;
	}

	public User getModel() {
		return user;
	}
	


	protected void saveCookie(User userVO) {
		Cookie cookie = new Cookie(AppConstant.SESSION_LOGIN_USER,
				String.valueOf(userVO.getId()));
		cookie.setMaxAge(60 * 60 * 24 * 100);
		response.addCookie(cookie);
	}
	
	protected void clearCookie(String cookieName){
		Cookie[] cookies = request.getCookies();
		Cookie clearCookie = null;
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(cookieName)){
					clearCookie = cookie;
				}
			}
		}
		
		if(clearCookie!=null){
			clearCookie.setValue("");
			clearCookie.setMaxAge(-1);
			response.addCookie(clearCookie);
		}
		
		logger.info("clear cookie for user "
				+ ((getSessionUser() != null) ? getSessionUser().getFirstEmail()
						: null));
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
		
	public String getPasswordRecon() {
		return passwordRecon;
	}
	public void setPasswordRecon(String passwordRecon) {
		this.passwordRecon = passwordRecon;
	}

	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public Boolean getExist() {
		return exist;
	}
	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public String getErrorMsg() {
		return returnMsg;
	}
	
	
	public String getFirstEmail() {
		return firstEmail;
	}

	public void setFirstEmail(String firstEmail) {
		this.firstEmail = firstEmail;
	}

	
}
