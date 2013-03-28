package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.UserManager;
import com.bocai.manager.UserThirdpartyManager;
import com.bocai.manager.impl.UserManagerImpl;
import com.bocai.service.SinaService;
import com.bocai.web.action.BaseAction;
import com.bocai.web.interceptor.LoginInterceptor;
import com.bocai.web.util.CookieUtils;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "myset_input", location = "myset.jsp"),
	@Result(name = "myset_avatar", location = "myset_avatar.jsp"),
	@Result(name = "ajax_save_result", type = "json", 
		params = {"includeProperties","saveResult,returnMsg"}
	),
	@Result(name = "ajax_check_result", type = "json", 
			params = {"includeProperties","checkResult"}
	)
})
public class MySetAction extends BaseAction<User> {
	@Autowired 
	private UserManager userManager;
	private User model;
	private Long id;
	private boolean checkResult;
	private boolean saveResult;
	private String returnMsg;
	private String date;
	private String isBirthdaySecret;
	private String isBlogUrlSecret;
	private String isChineseNameSecret;
	private String isMSNSecret;
	private String isPhoneNoSecret;
	private String isQQSecret;
	private String month;
	private String year;
	private Integer sexy;
	private String oldPassword;
	private String newPassword;
	private String newPasswordRetry;	
	
	private String oauth_verifier;
	private String backUrl;
	
	@Autowired
	private SinaService sinaService;
	@Autowired
	private UserThirdpartyManager userThirdpartyManager;
	
	@Override
	public User getModel() {
		return model;
	}
	
	@ActionRole( ActionRoleType.USER)
	@Action("my_set")
	public String input(){
		request.setAttribute("active", "1");
		
		if(getSessionUser().sinaConnected()){
			request.setAttribute("sinaConnected", true);
			request.setAttribute("sinaId", getSessionUser().sinaAccount().getName());
		}else{
			request.setAttribute("sinaConnected", false);
		}
		
		return "myset_input";
	}
	
	@Action("my_avatar")
	@ActionRole( ActionRoleType.USER)
	public String updateAvatar(){
		return "myset_avatar";
	}
	
	
	
	public String bundleToSina(){
		request.setAttribute("active", "3");
		try {
			String verifier = getOauth_verifier();
			if(verifier!=null){
				
				RequestToken resToken=(RequestToken) session.get("SINA_REQUEST_TOKEN");
			
				if(resToken!=null)
				{
					AccessToken acess = sinaService.requstAccessToken(resToken,verifier);
					JSONObject json = sinaService.getUserInfo(acess.getToken(),acess.getTokenSecret());
					User userVo = getSessionUser();
					
					UserThirdparty userThirdPartyVO = userThirdpartyManager.getUniqueByProperty("outSourceUserId", json.getString("outSourceUserId"));
					
					boolean existFlag = true;
					if(userThirdPartyVO == null){
						existFlag = false;
						userThirdPartyVO = new UserThirdparty();
					}
										
					userVo.setSource(json.getString("source"));
					if(userVo.getBlogUrl()==null){
						userVo.setBlogUrl(json.getString("blogurl"));
					}
					if("地理位置未知".equals(userVo.getCityName())){
						userVo.setCityName(json.getString("locaiton"));
					}
					
					if(SystemConfig.getUserDefaultAvatar().equals(userVo.getAvatar())){
						userVo.setAvatar(json.getString("photoPath"));
					}
					userVo.setOutSourceUserId(json.getString("outSourceUserId"));
					if("这个家伙很懒什么也没留下".equals(userVo.getSelfDescription())){
						userVo.setSelfDescription(json.getString("selfDescription"));
					}
					userManager.save(userVo);
					
					userThirdPartyVO.setUser(userVo);
					userThirdPartyVO.setOutSourceUserId(json.getString("outSourceUserId"));
					userThirdPartyVO.setName(json.getString("name"));
					userThirdPartyVO.setSource(userVo.getSource());
					userThirdPartyVO.setToken(acess.getToken());
					userThirdPartyVO.setTokenSecret(acess.getTokenSecret());
					userThirdPartyVO.setUrl(json.getString("blogurl"));
					if(existFlag){
						userThirdpartyManager.update(userThirdPartyVO);
					}else{
						userThirdpartyManager.save(userThirdPartyVO);
					}
					
					sinaService.createFollowShip(SystemConfig.getBocaiSinaId(), userThirdPartyVO);
					request.setAttribute("sinaConnected", true);
					request.setAttribute("sinaId", userThirdPartyVO.getName());
				}else{
					return "myset_input";
				}
			}else{
				return "myset_input";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "myset_input";
	}
	
	@Validations(
			requiredStrings = { 
				@RequiredStringValidator(fieldName = "oldPassword", message = "旧密码不能为空!"),
				@RequiredStringValidator(fieldName = "newPassword", message = "新密码不能为空!"),
				@RequiredStringValidator(fieldName = "newPasswordRetry", message = "新密码二次输入不能为空!")
			}
		)
		@InputConfig(resultName = "ajax_save_result" )
	public String ajaxResetPassword(){
		if(oldPassword.equals(model.getPassword())){
			if(newPassword.equals(newPasswordRetry)){
				model.setPassword(newPassword);
				userManager.update(model);
				returnMsg = "密码更新成功，请您牢记！";
				saveResult = true;
			}else{
				saveResult = false;
				returnMsg = "您两次输入的新密码不一致，请重新输入！";
			}
		}else{
			saveResult = false;
			returnMsg = "您原来的密码输入错误！";
		}
		return "ajax_save_result";
	}
	


	@InputConfig(resultName = "ajax_save_result" )
	public String ajaxSave(){
		try{
			StringBuffer birthday = new StringBuffer();
			if(!"".equals(year)){
				
				birthday.append(year).append("-");
				
				if(!"".equals(month)){
					birthday.append(month).append("-");
				}else{
					birthday.append("01").append("-");
				}
				if(!"".equals(date)){
					birthday.append(date);
				}else{
					birthday.append("01");
				}
				model.setBirthday(birthday.toString());
			}
			JSONObject secretFieldjson = new JSONObject();
			secretFieldjson.put("birthday", isBirthdaySecret);
			secretFieldjson.put("blogUrl", isBlogUrlSecret);
			secretFieldjson.put("chineseName", isChineseNameSecret);
			secretFieldjson.put("msn", isMSNSecret);
			secretFieldjson.put("phoneNo", isPhoneNoSecret);
			secretFieldjson.put("qq", isQQSecret);
			model.setSecretFields(secretFieldjson.toString());

			userManager.update(model);
			saveResult = true;
			setReturnMsg("更新成功！");
			saveResult = true;
			
			birthday = null;
		}catch(Exception e){
			saveResult = false;
			addActionError(e.getMessage());
		}
		return "ajax_save_result";
	}
	
	public String checkNickName(){
		return "ajax_check_result";
	}

	@Override
	protected void prepareModel() throws Exception {
		model = getSessionUser();
	}

	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setModel(User model) {
		this.model = model;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCheckResult() {
		return checkResult;
	}
	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}

	public boolean isSaveResult() {
		return saveResult;
	}
	public void setSaveResult(boolean saveResult) {
		this.saveResult = saveResult;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIsBirthdaySecret() {
		return isBirthdaySecret;
	}

	public void setIsBirthdaySecret(String isBirthdaySecret) {
		this.isBirthdaySecret = isBirthdaySecret;
	}

	public String getIsBlogUrlSecret() {
		return isBlogUrlSecret;
	}

	public void setIsBlogUrlSecret(String isBlogUrlSecret) {
		this.isBlogUrlSecret = isBlogUrlSecret;
	}

	public String getIsChineseNameSecret() {
		return isChineseNameSecret;
	}

	public void setIsChineseNameSecret(String isChineseNameSecret) {
		this.isChineseNameSecret = isChineseNameSecret;
	}

	public String getIsMSNSecret() {
		return isMSNSecret;
	}

	public void setIsMSNSecret(String isMSNSecret) {
		this.isMSNSecret = isMSNSecret;
	}

	public String getIsPhoneNoSecret() {
		return isPhoneNoSecret;
	}

	public void setIsPhoneNoSecret(String isPhoneNoSecret) {
		this.isPhoneNoSecret = isPhoneNoSecret;
	}

	public String getIsQQSecret() {
		return isQQSecret;
	}

	public void setIsQQSecret(String isQQSecret) {
		this.isQQSecret = isQQSecret;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRetry() {
		return newPasswordRetry;
	}

	public void setNewPasswordRetry(String newPasswordRetry) {
		this.newPasswordRetry = newPasswordRetry;
	}

	public void setOauth_verifier(String oauth_verifier) {
		this.oauth_verifier = oauth_verifier;
	}

	public String getOauth_verifier() {
		return oauth_verifier;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setSexy(Integer sexy) {
		this.sexy = sexy;
	}

	public Integer getSexy() {
		return sexy;
	}
	
}
