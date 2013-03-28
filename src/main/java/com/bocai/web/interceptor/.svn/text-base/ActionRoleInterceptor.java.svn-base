package com.bocai.web.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class ActionRoleInterceptor extends AbstractInterceptor {

	public static Log log = LogFactory.getLog(ActionRoleInterceptor.class);
	public static final String GLOBAL_LOGIN = "login";
	public static final String GLOBAL_403 = "403";
	@Autowired
	private UserManager userManager;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionRole actionRole = getActionRole(invocation); 
		if (actionRole == null) {  
            return invocation.invoke();  
        }
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if(isAccessable(session, actionRole.value())){
			return invocation.invoke();
		}else if(actionRole.value() == ActionRoleType.USER){
			HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			if (isAjaxRequest(request)) {
				HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
				PrintWriter writer = response.getWriter(); 
				writer.println("{\"returnMsg:\",\"NOT_AUTHENTICATED\"}");  
			}
			return GLOBAL_LOGIN;
		}else if(actionRole.value() == ActionRoleType.ADMIN){
			return GLOBAL_403;
		}else{
			return null;
		}
	}
	
	private ActionRole getActionRole(ActionInvocation actionInvocation) {  
        ActionProxy actionProxy = actionInvocation.getProxy();  
        Object action = actionProxy.getAction();  
        String method = actionProxy.getMethod();  
        Method actionMethod = null;  
        try {  
            actionMethod = action.getClass().getMethod(method);  
        } catch (SecurityException se) {  
        	log.error(null, se);
        } catch (NoSuchMethodException e) {  
        	log.error(null, e);
        }  
        return actionMethod.getAnnotation(ActionRole.class);  
    }
	
	private boolean isAccessable(Map<String, Object> session, ActionRoleType roleType){
		ActionRoleType loginUserRoleType = ActionRoleType.ANONYMOUS;
		Long loginUserId = (Long) session.get(AppConstant.SESSION_LOGIN_USER_ID);
		if(loginUserId != null){
			User loginUser = userManager.getById(loginUserId);
			if(loginUser.getActionRole()==null) return false;
			loginUserRoleType = ActionRoleType.valueOf(loginUser.getActionRole());
		}
		if(roleType.compareTo(loginUserRoleType)>0){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean isAjaxRequest(HttpServletRequest request) {   
	    String header = request.getHeader("X-Requested-With");   
	    if (header != null && "XMLHttpRequest".equals(header))   
	        return true;   
	    else  
	        return false;   
	}

}
