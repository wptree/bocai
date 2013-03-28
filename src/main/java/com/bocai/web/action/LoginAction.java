package com.bocai.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.UserManager;
import com.bocai.service.RenrenService;
import com.bocai.service.SinaService;
import com.bocai.util.XiaoneiRestfulApiUtil;
import com.bocai.vo.RenrenAccessToken;
import com.bocai.vo.UserVo;
import com.bocai.web.util.CookieUtils;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
		@Result(name = "login_input", location = "login.jsp"),
		@Result(name = "login_success", location = "main.bc", type = "redirect"),
		@Result(name = "login_error", location = "login.jsp"),
		@Result(name = "logout_success", type = "redirect", location = "login.bc"),
		@Result(name = "ajax_login_result", type = "json", params = {
				"includeProperties", "loginUser.*" }),
		@Result(name = "ajax_thirdparty", type = "json", params = {
				"includeProperties", "backUrl,redirectionUrl" }) })
public class LoginAction extends BaseAction<User> {

	private static final String LOGIN_INPUT = "login_input";
	private static final String LOGIN_SUCCESS = "login_success";
	private static final String LOGOUT_SUCCESS = "logout_success";
	private static final String LOGIN_ERROR = "login_error";
	private static final String AJAX_LOGIN_RESULT = "ajax_login_result";

	@Autowired
	private UserManager userManager;
	private User user;
	private UserVo loginUser;
	private boolean rememberMe;
	private String redirectionUrl;
	private int type;
	@Autowired
	private SinaService sinaService;
	@Autowired
	private RenrenService renrenService;

	private String oauth_verifier;
	private String source;
	private String backUrl;
	private boolean needClean;
	// renren api call back parameter
	private String code;

	@Action("login")
	public String input() {
		return LOGIN_INPUT;
	}

	@Validations(emails = { @EmailValidator(fieldName = "firstEmail", message = "邮箱格式不正确！") }, requiredStrings = {
			@RequiredStringValidator(fieldName = "firstEmail", message = "登录邮箱不能为空！"),
			@RequiredStringValidator(fieldName = "password", message = "密码不能为空！") })
	@InputConfig(resultName = LOGIN_ERROR)
	public String login() {
		try {
			user = userManager.login(user.getFirstEmail(), user.getPassword());
			if (user != null) {
				addSessions(user);
			}
			if (rememberMe) {
				CookieUtils.addCookie(request, response,
						AppConstant.COOKIE_LOGIN_USER_ID, user.getId()
								+ "", UserConfig.COOKIE_MAX_AGE);
			}
			redirectionUrl = (String) session
					.get(AppConstant.SESSION_REDIRECTION_URL);
			session.remove(AppConstant.SESSION_REDIRECTION_URL);
			if (!StringUtils.isEmpty(redirectionUrl)) {
				response.sendRedirect(redirectionUrl);
				return null;
			}

			resetCookie(user);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return LOGIN_ERROR;
		}
		return LOGIN_SUCCESS;
	}

	@Validations(emails = { @EmailValidator(fieldName = "firstEmail", message = "邮箱格式不正确.") }, requiredStrings = {
			@RequiredStringValidator(fieldName = "firstEmail", message = "登录邮箱不能为空."),
			@RequiredStringValidator(fieldName = "password", message = "密码不能为空.") })
	@InputConfig(resultName = AJAX_LOGIN_RESULT)
	public String ajaxLogin() {
		user = userManager.login(user.getFirstEmail(), user.getPassword());
		addSessions(user);
		loginUser = UserVo.fromUserBasic(user);
		if (rememberMe) {
			CookieUtils.addCookie(request, response,
					AppConstant.COOKIE_LOGIN_USER_ID, user.getId()
							+ "", UserConfig.COOKIE_MAX_AGE);
			resetCookie(user);
		} else {
			CookieUtils.cancleCookie(response,
					AppConstant.COOKIE_LOGIN_USER_ID, null);
		}
		return AJAX_LOGIN_RESULT;
	}

	private void resetCookie(User user) {
		// if(CookieUtils.getCookie(request,
		// UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + user.getId()) == null){
		// CookieUtils.addCookie(request, response,
		// UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + user.getId(),
		// user.getFeedFetchTimeline()+"",UserConfig.COOKIE_MAX_AGE);
		// }
		if (CookieUtils.getCookie(request,
				UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + user.getId()) == null) {
			CookieUtils.addCookie(
					request,
					response,
					UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX
							+ user.getId(), user.getNotificatonFetchTimeline()
							+ "", UserConfig.COOKIE_MAX_AGE);

		}
	}

	public String thirdPartyRequest() {
		// for sina user
		if (UserConfig.USER_SOURCE_SINA.equals(getSource())) {
			if (needClean) {
				CookieUtils.cancleCookie(response,
						UserConfig.COOKIE_SINA_WEIBO, null);
			}

			RequestToken token = sinaService.getRequestToken(getBackUrl());
			session.put("SINA_REQUEST_TOKEN", token);
			setBackUrl(token.getAuthorizationURL());
			return "ajax_thirdparty";
		} else if (UserConfig.USER_SOURCE_RENREN.equals(getSource())) {
			StringBuffer authorizeurl = new StringBuffer();
			authorizeurl.append(SystemConfig.RENREN_OAUTH_AUTHORIZE_URL);
			authorizeurl.append("?client_id=" + SystemConfig.getRenrenAppKey());
			authorizeurl.append("&redirect_uri=" + getBackUrl());
			authorizeurl.append("&response_type=code");
			authorizeurl.append("&scope=status_update");
			setBackUrl(authorizeurl.toString());
			return "ajax_thirdparty";
		} else {
			return LOGIN_INPUT;
		}
	}

	public String renrenUserLogin() {
		String redirect_url = SystemConfig.getRenrenBackUrl();
		String authorization_code = getCode();
		String sessionkey_json = "";

		RenrenAccessToken accesstoken = (RenrenAccessToken) session
				.get("renren_accessToken");

		if (accesstoken == null) {
			if (authorization_code == null) {
				System.out
						.println("Cannot get authorization code from renren api, place confirm the redirect url configuration.");
				return LOGIN_ERROR;
			}
			accesstoken = renrenService.getAccessToken(authorization_code,
					redirect_url);
			sessionkey_json = XiaoneiRestfulApiUtil
					.getXiaonei_SessionKey_info(accesstoken.getAccess_token());
			session.put("renren_accesstoTen", accesstoken);
			session.put("renren_sessionkeyJson", sessionkey_json);
		} else {
			sessionkey_json = XiaoneiRestfulApiUtil
					.getXiaonei_SessionKey_info(accesstoken.getAccess_token());
			/**
			 * 如果accesstoken过期，需要第三方引导用户重新登录
			 */
			if (sessionkey_json.equals("expired_token")) {
				request.getSession().getServletContext().setAttribute("", null);
				return LOGIN_INPUT;
			}
		}
		String renrenUserId = XiaoneiRestfulApiUtil
				.getRenrenUserId(sessionkey_json);
		user = userManager.exist(renrenUserId, true);
		if (user == null) {
			user = renrenService.persistUser(sessionkey_json);
		}

		if (user != null) {
			addSessions(user);
			return LOGIN_SUCCESS;
		}

		return LOGIN_INPUT;

	}

	public String sinaUserLogin() {
		try {
			String verifier = getOauth_verifier();
			if (verifier != null) {

				RequestToken resToken = (RequestToken) session
						.get("SINA_REQUEST_TOKEN");

				if (resToken != null) {
					AccessToken acess = sinaService.requstAccessToken(resToken,
							verifier);
					JSONObject json = sinaService.getUserInfo(acess.getToken(),acess.getTokenSecret());
					user = userManager.exist(json.getString("outSourceUserId"),
							true);
					if (user == null) {
						UserThirdparty userThirdPartyVO = sinaService.persistUser(acess.getToken(),acess.getTokenSecret());
						user = userThirdPartyVO.getUser();
						sinaService
								.createFollowShip(
										SystemConfig.getBocaiSinaId(),
										userThirdPartyVO);
						sinaService.sayHello(acess.getToken(),acess.getTokenSecret());
					} else {
						sinaService.updateUser(acess.getToken(),acess.getTokenSecret(), user);
					}
					// save user into session
					if (user != null) {
						addSessions(user);
						return LOGIN_SUCCESS;
					}
				} else {
					return LOGIN_ERROR;
				}
			} else {
				return LOGIN_ERROR;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return LOGIN;
	}

	@Action("logout")
	public String logout() {
		removeSessions();
		CookieUtils.cancleCookie(response, 
				AppConstant.COOKIE_LOGIN_USER_ID, null);
		CookieUtils.cancleCookie(response,
				UserConfig.COOKIE_SINA_WEIBO, null);
		return LOGOUT_SUCCESS;
	}

	private void addSessions(User user) {
		session.put(AppConstant.SESSION_LOGIN_USER, user);
		session.put(AppConstant.SESSION_LOGIN_USER_ID, user.getId());
		session.put(AppConstant.SESSION_TASK_STATUS,
				userManager.getTaskStatusMap(user.getId()));
	}

	private void removeSessions() {
		if (session != null
				&& session.containsKey(AppConstant.SESSION_LOGIN_USER)) {
			userManager.endThirdPartySession(getSessionUser());
		}
		session.remove(AppConstant.SESSION_LOGIN_USER);
		session.remove(AppConstant.SESSION_TASK_STATUS);
		session.remove(AppConstant.SESSION_MAP_QSTR);
		session.remove(AppConstant.SESSION_SIGN_UP_WIZARD);
		session.remove(AppConstant.SESSION_LOGIN_USER_ID);
		session.remove(AppConstant.SESSION_LOGIN_USER);
		session.remove(AppConstant.SESSION_USER_CITY);
	}

	@Override
	public User getModel() {
		return user;
	}

	@Override
	protected void prepareModel() throws Exception {
		user = new User();
		type = 1;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserVo getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(UserVo loginUser) {
		this.loginUser = loginUser;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getRedirectionUrl() {
		return redirectionUrl;
	}

	public void setRedirectionUrl(String redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setOauth_verifier(String oauth_verifier) {
		this.oauth_verifier = oauth_verifier;
	}

	public String getOauth_verifier() {
		return oauth_verifier;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setRenrenService(RenrenService renrenService) {
		this.renrenService = renrenService;
	}

	public RenrenService getRenrenService() {
		return renrenService;
	}

	public void setNeedClean(boolean needClean) {
		this.needClean = needClean;
	}

	public boolean isNeedClean() {
		return needClean;
	}

}