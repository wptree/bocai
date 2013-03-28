package com.bocai.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bocai.common.security.ActionRoleType;
import com.bocai.config.SystemConfig;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.UserManager;
import com.bocai.manager.UserThirdpartyManager;
import com.bocai.manager.impl.UserThirdpartyManagerImpl;
import com.bocai.util.Md5Utils;
import com.bocai.util.XiaoneiOauthAccessTokenUtil;
import com.bocai.util.XiaoneiRestfulApiUtil;
import com.bocai.vo.RenrenAccessToken;

@Service
public class RenrenService extends ManagerHelper {
	private static Logger logger = LoggerFactory.getLogger(RenrenService.class);	
	
	@Resource private UserManager userManager; 
	@Resource private UserThirdpartyManager userThirdpartyManager;
	
	
	public String getSessionKey(RenrenAccessToken accessToken){
		return XiaoneiRestfulApiUtil.getXiaonei_SessionKey_info(accessToken.getAccess_token());
	}
	
	public RenrenAccessToken getAccessToken(String authorization_code, String redirect_url){
		return XiaoneiOauthAccessTokenUtil.getAccessToken(authorization_code, redirect_url);
	}
	
	public static void main(String[] args){
		RenrenService s = new RenrenService();
		//s.sendStatus("6.8e916accf427399808e9ac70b1e2062e.2592000.1309622400-373843392", "李娜V5");
	}
	
	public String sendStatus(String sessionKey, String content) throws Exception{
		
			long  time = System.currentTimeMillis();
			String strTime = URLEncoder.encode(String.valueOf(time), "UTF-8");  //系统的当前时间，作为call_id的值
			String secret = URLEncoder.encode(SystemConfig.getRenrenAppSecretKey(),"UTF-8");    //应用的Secret Key
			String apiKey = URLEncoder.encode(SystemConfig.getRenrenAppKey(),"UTF-8");     //应用的API Key
			String requestMethod = URLEncoder.encode("status.set","UTF-8");                //接口名称
			String v = URLEncoder.encode("1.0","UTF-8");                     
			content = URLEncoder.encode(content,"UTF-8");
			
	        List<String> paramList = new ArrayList<String>();
	        paramList.add("session_key="+sessionKey);
	        paramList.add("api_key="+apiKey);
	        paramList.add("call_id="+strTime);
	        paramList.add("method="+requestMethod);
	        paramList.add("status="+content);
	        paramList.add("v="+v);
	        paramList.add("format=JSON");
	        String signature = getSignature(paramList,secret);
	        
	        //content = URLEncoder.encode(content,"UTF-8");
			PostMethod method = new PostMethod(SystemConfig.RENREN_API_CALL_URL);
	        //将以上准备好的参数添加到method对象中
			method.addParameter("api_key", apiKey);
			method.addParameter("method", requestMethod);
			method.addParameter("call_id", strTime);
			method.addParameter("v", v);
			method.addParameter("session_key", URLEncoder.encode(sessionKey,"UTF-8"));
			method.addParameter("status",content);
			method.addParameter("sig", signature);
	        method.addParameter("format", "JSON");//返回结果的形式，支持XML或者JSON两种形式，默认为XML
			HttpClient client = new HttpClient();
			client.executeMethod(method);
			
			JSONObject jsonObject = new JSONObject(method.getResponseBodyAsString());
			System.out.println(jsonObject.toString());
			if(jsonObject.has("error_code")){
				return jsonObject.getString("error_msg");
			}else{
				return String.valueOf(jsonObject.getInt("result"));
			}
			
	}
	
	public User persistUser(String sessionkeyJson){
		User user = null;
		try {
			org.json.JSONObject renren_token = new org.json.JSONObject(sessionkeyJson);
			String sessionKey = XiaoneiRestfulApiUtil.getSessionKey(sessionkeyJson);
			JSONArray userInfo = XiaoneiRestfulApiUtil.getUserInfo(sessionKey, XiaoneiRestfulApiUtil.getRenrenUserId(sessionkeyJson));
			org.json.simple.JSONObject base = (org.json.simple.JSONObject) userInfo.get(0);
			//String fields = "uid,name,sex,birthday,headurl,hometown_location";
			user = new User();
			user.setName(base.get("name").toString());
			user.setSource(UserConfig.USER_SOURCE_RENREN);
			user.setOutSourceUserId(base.get("uid").toString());
			user.setActionRole(ActionRoleType.USER.name());
			user.setSexy(Integer.valueOf(base.get("sex").toString()));
			user.setBirthday(base.get("birthday").toString());
			user.setAvatar(base.get("headurl").toString());
			
			org.json.simple.JSONObject hometown = (org.json.simple.JSONObject) base.get("hometown_location");
			StringBuffer home = new StringBuffer();
			home.append(hometown.get("province"));
			home.append(hometown.get("city"));
			user.setCityName(home.toString());
			
			userManager.save(user);
			
			UserThirdparty userThirdPartyVO = new UserThirdparty();
			userThirdPartyVO.setUser(user);
			userThirdPartyVO.setOutSourceUserId(user.getOutSourceUserId());
			userThirdPartyVO.setName(user.getName());
			userThirdPartyVO.setSource(user.getSource());
			
			//userThirdPartyVO.setToken(renren_token.getString("oauth_token"));
			//userThirdPartyVO.setTokenSecret(sessionKey);
			userThirdpartyManager.save(userThirdPartyVO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot persist Renren user due to "+ e.getMessage());
		}
		return user;
	}
	
	 public String getSignature(List<String> paramList,String secret){

		 Collections.sort(paramList);
		 StringBuffer buffer = new StringBuffer();
		 for (String param : paramList) {
		     buffer.append(param);  //将参数键值对，以字典序升序排列后，拼接在一起
		 }
		 buffer.append(secret);  //符串末尾追加上应用的Secret Key
		 System.out.println(buffer.toString());
		 return Md5Utils.md5(buffer.toString());
    	}
	
	
	
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserThirdpartyManager(UserThirdpartyManagerImpl userThirdpartyManager) {
		this.userThirdpartyManager = userThirdpartyManager;
	}
	public UserThirdpartyManager getUserThirdpartyManager() {
		return userThirdpartyManager;
	}
}
