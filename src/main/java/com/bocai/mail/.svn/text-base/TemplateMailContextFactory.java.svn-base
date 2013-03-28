package com.bocai.mail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bocai.common.constant.UserTokenType;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserToken;
import com.bocai.exception.ManagerException;
import com.bocai.manager.UserManager;
import com.bocai.manager.UserTokenManager;
import com.bocai.vo.MailContextVo;
import com.bocai.vo.UserVo;

@Component("mailContextFactory")
public class TemplateMailContextFactory {
	
	public static final String USER = "user";
	public static final String MAIL = "mail";
	
	@Resource private UserTokenManager userTokenManager;
	@Resource private UserManager userManager;
	
	public Map<String, Object> getSignUpSuccessfulContext(UserVo userVo){
		if(userVo == null) return null;
		
		//generate alert context map
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(USER, userVo);
		return context;
	}
	
	public Map<String, Object> getSignUpValidationContext(UserVo userVo){
		if(userVo == null) return null;
		User user = userManager.getById(userVo.getId());
		if(user==null){
			throw new ManagerException("Can not find user with id:" + userVo.getId());
		}
		UserToken userToken = userTokenManager
				.fetchToken(user.getId(), UserTokenType.SIGN_UP_VALIDATION);
		
		String link = new StringBuilder()
				.append(SystemConfig.getDomainUrl())
				.append("/")
				.append(SystemConfig.getContext())
				.append("/")
				.append("secure.bc?")
				.append("uid=").append(user.getId()).append("&")
				.append("token=").append(userToken.getToken())
				.toString();
		String web = new StringBuilder()
				.append(SystemConfig.getDomainUrl())
				.append("/")
				.append(SystemConfig.getContext())
				.append("/")
				.toString();
		MailContextVo mailContextVo = new MailContextVo();
		mailContextVo.setName(user.getName());
		mailContextVo.setLink(link);
		mailContextVo.setWeb(web);
		mailContextVo.setCreateAt(new Date());
		//generate alert context map
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(MAIL, mailContextVo);
		return context;
	}
	
	public Map<String, Object> getForgetPasswordContext(UserVo userVo){
		if(userVo == null) return null;
		User user = userManager.getById(userVo.getId());
		if(user==null){
			throw new ManagerException("Can not find user with id:" + userVo.getId());
		}
		UserToken userToken = userTokenManager.fetchToken(user.getId(),
				UserTokenType.FORGET_PWD);
		String link = new StringBuilder().append(SystemConfig.getDomainUrl())
				.append("/").append(SystemConfig.getContext()).append("/")
				.append("retrieve_pwd.bc?").append("uid=").append(user.getId())
				.append("&").append("token=").append(userToken.getToken())
				.toString();
		String web = new StringBuilder().append(SystemConfig.getDomainUrl())
				.append("/").append(SystemConfig.getContext()).append("/")
				.toString();
		MailContextVo mailContextVo = new MailContextVo();
		mailContextVo.setName(user.getName());
		mailContextVo.setLink(link);
		mailContextVo.setWeb(web);
		mailContextVo.setCreateAt(new Date());
		// generate alert context map
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(MAIL, mailContextVo);
		return context;
	}
	
	public Map<String, Object> getInvitationContext(UserVo userVo, String targetEmail){
		if(userVo == null) return null;
		User user = userManager.getById(userVo.getId());
		if(user==null){
			throw new ManagerException("Can not find user with id:" + userVo.getId());
		}
		MailContextVo mailContextVo = new MailContextVo();
		String link;
		try {
			link = new StringBuilder().append(SystemConfig.getDomainUrl())
					.append("/").append(SystemConfig.getContext()).append("/")
					.append("user/invite.bc?").append("email=").append(URLEncoder.encode(targetEmail,"US-ASCII"))
					.append("&").append("id=").append(userVo.getId())
					.toString();
			String web = new StringBuilder().append(SystemConfig.getDomainUrl())
			.append("/").append(SystemConfig.getContext()).append("/")
			.toString();
			
			mailContextVo.setName(user.getName());
			mailContextVo.setLink(link);
			mailContextVo.setWeb(web);
			
			System.out.println(link.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// generate alert context map
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(MAIL, mailContextVo);
		return context;
	}

	public UserTokenManager getUserTokenManager() {
		return userTokenManager;
	}

	public void setUserTokenManager(UserTokenManager userTokenManager) {
		this.userTokenManager = userTokenManager;
	}
}
