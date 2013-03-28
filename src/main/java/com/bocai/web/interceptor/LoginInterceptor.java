package com.bocai.web.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.web.util.CookieUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	private static final String URL_KEY_LOGIN = "login";
	
	public static Log log = LogFactory.getLog(LoginInterceptor.class);

	@Autowired
	private UserManager userManager;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			Map<String, Object> session = invocation.getInvocationContext().getSession();
			HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
			// first, try to find login user id from session
			Long loginUserId = (Long) session.get(AppConstant.SESSION_LOGIN_USER_ID);
			// if not found, try cookie
			if (loginUserId == null) {
				Cookie rememberMe = CookieUtils.getCookie(request,AppConstant.COOKIE_LOGIN_USER_ID);
				if (rememberMe != null)
					loginUserId = Long.parseLong(rememberMe.getValue());
			}
			// still not found, clear cookie and put last visited url into
			// session
			if (loginUserId == null) {
				CookieUtils.cancleCookie(response, AppConstant.COOKIE_LOGIN_USER_ID, null);
				
				String redirectionUrl = request.getHeader("Referer");
				if(redirectionUrl==null||"".equals(redirectionUrl)){
					redirectionUrl = "";
					String path=request.getContextPath();  
                    String actionName=invocation.getProxy().getActionName();  
                    String nameSpace=invocation.getProxy().getNamespace();  
					if (StringUtils.isNotEmpty(nameSpace)) {
						redirectionUrl = redirectionUrl + path + nameSpace;

					}
					if (StringUtils.isNotEmpty(actionName)) {
						redirectionUrl = redirectionUrl + "/" + actionName+ ".bc";
						String queryString = request.getQueryString();
						if (StringUtils.isNotEmpty(queryString)) {
							redirectionUrl += "?" + queryString;
						}
					}

				}
				
				if(StringUtils.isNotEmpty(redirectionUrl) && !redirectionUrl.contains(URL_KEY_LOGIN)){
					session.put(AppConstant.SESSION_REDIRECTION_URL, redirectionUrl);
				}
			} else {
				// check whether loginUser is empty
				User loginUser = (User) session.get(AppConstant.SESSION_LOGIN_USER);
				if (loginUser == null) {
					loginUser = userManager.getById(loginUserId);
					session.put(AppConstant.SESSION_TASK_STATUS, userManager.getTaskStatusMap(loginUserId));
					session.put(AppConstant.SESSION_LOGIN_USER, loginUser);
					session.put(AppConstant.SESSION_LOGIN_USER_ID, loginUserId);
				}
			}
		} catch (Exception e) {
			log.error(null, e);
		}
		return invocation.invoke();
	}
	
}
