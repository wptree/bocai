package com.bocai.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bocai.common.constant.UserStatus;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.SystemConfig;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.UserManager;
import com.bocai.manager.UserThirdpartyManager;

import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.ImageItem;
import weibo4j.http.RequestToken;

/**
 * SinaService for Sina Weibo user login authentication and user information persistent
 * @author wpeng
 *
 */
@Service
public class SinaService extends ManagerHelper{
	private static Logger logger = LoggerFactory.getLogger(SinaService.class);	
	
	
	static{
		System.setProperty("weibo4j.oauth.consumerKey", SystemConfig.getSinaAppKey());
		System.setProperty("weibo4j.oauth.consumerSecret", SystemConfig.getSinaAppSecretKey());
	}
	
	/**
	 * Get RequestToken object using specified backUrl
	 * @param backUrl
	 * @return
	 */
	public RequestToken getRequestToken(String backUrl) {
		try {
			Weibo weibo = new Weibo();
			RequestToken requestToken = weibo.getOAuthRequestToken(backUrl);
			return requestToken;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * After get request token, sina api will send back an verifier. 
	 * We should send this verifier and request token together back to sina api to get Access Token
	 * @param requestToken
	 * @param verifier
	 * @return
	 */
	public AccessToken requstAccessToken(RequestToken requestToken,String verifier) {
		try {
			Weibo weibo = new Weibo();
			AccessToken accessToken = weibo.getOAuthAccessToken(requestToken.getToken(), requestToken.getTokenSecret(), verifier);
			return accessToken;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * We can retrieve user information from Access Token.
	 * Package all required user information into JSON object
	 * @param access
	 * @return @JSONObject
	 */
	public JSONObject getUserInfo(String token, String tokenSecret){
		try {
			JSONObject json = new JSONObject();
			Weibo weibo = new Weibo();
			weibo.setToken(token, tokenSecret);
			weibo4j.User u = weibo.verifyCredentials();
			json.put("source", UserConfig.USER_SOURCE_SINA);
			json.put("name", u.getScreenName());  
			json.put("blogurl", u.getURL()==null?"":u.getURL().toString());
			json.put("photoPath", u.getProfileImageURL().toString());
			json.put("outSourceUserId", String.valueOf(u.getId()));
			json.put("selfDescription", u.getDescription());
			json.put("locaiton", u.getLocation());
			return json;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException("Cannot retrieve user info from sina weibo api due to "+ e.getMessage());
		}
	}
	
	public void updateUser(String token, String tokenSecret,User user){
		if(user!=null && user.sinaAccount()!=null){
			try {
				JSONObject json = getUserInfo(token,tokenSecret);
				user.setSource(json.getString("source"));
				user.setName(json.getString("name"));   
				user.setBlogUrl(json.getString("blogurl"));
				user.setCityName(json.getString("locaiton"));
				user.setAvatar(json.getString("photoPath"));
				user.setOutSourceUserId(json.getString("outSourceUserId"));
				user.setActionRole(ActionRoleType.USER.name());
				user.setSelfDescription(json.getString("selfDescription"));
				user.setCreatedAt(new Date());
				//user.setUpdatedAt(new Date());
				userDao.updateByUpdater(new Updater<User>(user));
				
				UserThirdparty userThirdPartyVO = user.sinaAccount();
				userThirdPartyVO.setOutSourceUserId(json.getString("outSourceUserId"));
				userThirdPartyVO.setName(json.getString("name"));
				userThirdPartyVO.setSource(user.getSource());
				userThirdPartyVO.setToken(token);
				userThirdPartyVO.setTokenSecret(tokenSecret);
				userThirdPartyVO.setUrl(json.getString("blogurl"));
				userThirdpartyDao.updateByUpdater(new Updater<UserThirdparty>(userThirdPartyVO));
			} catch (JSONException e) {
				logger.error(e.toString());
				throw new RuntimeException("Cannot update user info from sina weibo api due to "+ e.getMessage());
			}
		}
	}

	/**
	 * Persist user information into DB
	 * @param access
	 * @return
	 */
	public UserThirdparty persistUser(String token, String tokenSecret){
		try {
			User userVo = new User();
			JSONObject json = getUserInfo(token,tokenSecret);
			userVo.setSource(json.getString("source"));
			userVo.setName(json.getString("name"));   
			userVo.setBlogUrl(json.getString("blogurl"));
			userVo.setCityName(json.getString("locaiton"));
			userVo.setAvatar(json.getString("photoPath"));
			userVo.setOutSourceUserId(json.getString("outSourceUserId"));
			userVo.setActionRole(ActionRoleType.USER.name());
			userVo.setCreatedAt(new Date());
			userVo.setUpdatedAt(new Date());
			userVo.setSelfDescription(json.getString("selfDescription"));
			userVo.setStatus(UserStatus.NORMAL);
			userDao.save(userVo);
			
			UserThirdparty userThirdPartyVO = new UserThirdparty();
			userThirdPartyVO.setUser(userVo);
			userThirdPartyVO.setOutSourceUserId(userVo.getOutSourceUserId());
			userThirdPartyVO.setName(userVo.getName());
			userThirdPartyVO.setSource(userVo.getSource());
			userThirdPartyVO.setToken(token);
			userThirdPartyVO.setTokenSecret(tokenSecret);
			userThirdPartyVO.setUrl(userVo.getBlogUrl());
			userThirdpartyDao.save(userThirdPartyVO);
			
			return userThirdPartyVO;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException("Cannot persist sina weibo user due to "+ e.getMessage());
		}
		
		
	}
	
	/**
	 * Weibo user logout
	 * @param thirdpartyAccount
	 */
	public void endSession(UserThirdparty thirdpartyAccount){
		if(thirdpartyAccount!=null){
			try {
				Weibo weibo = new Weibo();
				weibo.setToken(thirdpartyAccount.getToken(), thirdpartyAccount.getTokenSecret());
				weibo.endSession();
			} catch (WeiboException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Required each user follow our official weibo account
	 * @param toWhoId
	 * @param thirdpartyAccount
	 */
	public void createFollowShip(String toWhoId, UserThirdparty thirdpartyAccount){
		try {
			Weibo weibo = new Weibo();
			if(thirdpartyAccount.getToken()!=null&&thirdpartyAccount.getTokenSecret()!=null){
				weibo.setToken(thirdpartyAccount.getToken(),thirdpartyAccount.getTokenSecret());
				weibo.createFriendshipByUserid(toWhoId);
			}
			
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	
	public void sayHello(String token, String tokenSecret){
		StringBuffer bf = new StringBuffer("我刚刚加入了 波菜网 ");
		bf.append(SystemConfig.getDomainUrl()).append(" ").append("一个旅游美食社区，在那里，可以很方便的找到我想去旅游的那个城市的美食。大家快来围观吧！@波菜网");
		sendImgwithText(token,tokenSecret,bf.toString(),SystemConfig.getSinaWeiboHelloImg());
		bf = null;
	}

	/**
	 * Send text message to sina weibo api
	 * @param access
	 * @param content
	 * @throws WeiboException 
	 * @throws UnsupportedEncodingException 
	 */
	public void sendTextMsg(String accessToken,String accessTokenSecret, String content) throws UnsupportedEncodingException, WeiboException {
		Weibo weibo = new Weibo();
		weibo.setToken(accessToken, accessTokenSecret);
		weibo.updateStatus(URLEncoder.encode(content, "UTF-8"));
	}
	
	/**
	 * Send IMG to sina weibo api
	 * @param access
	 * @param txtContent
	 * @param imgPath
	 */
	public void sendImgwithText(String accessToken,String accessTokenSecret, String txtContent, String imgUri){
		try {
			HttpClient client = new HttpClient();
			GetMethod getMethod = new GetMethod(imgUri);
			client.executeMethod(getMethod);
			byte[] image = getMethod.getResponseBody();
			ImageItem item = new ImageItem(image);
			Weibo weibo = new Weibo();
			weibo.setToken(accessToken, accessTokenSecret);
			weibo.uploadStatus(URLEncoder.encode(txtContent, "UTF-8"),item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send rich message to sina weibo api
	 * @param access
	 * @param txtContent
	 * @param imgPath
	 */
	public void sendImgwithText(String accessToken,String accessTokenSecret, String txtContent, File img){
		try {
			ImageItem item = new ImageItem("pic", FileUtils.readFileToByteArray(img));
			Weibo weibo = new Weibo();
			weibo.setToken(accessToken, accessTokenSecret);
			weibo.uploadStatus(URLEncoder.encode(txtContent, "UTF-8"),item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		System.setProperty("weibo4j.oauth.consumerKey", SystemConfig.getSinaAppKey());
		System.setProperty("weibo4j.oauth.consumerSecret", SystemConfig.getSinaAppSecretKey());
		SinaService s = new SinaService();
		//s.sendTextMsg("2c34109e710c9c97a9be01d35aefbf97","960881260d6ec513bb65855ee9fdc6cc","李娜V5");
	
	}
	
	
}
