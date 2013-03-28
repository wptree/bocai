package com.bocai.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

@SuppressWarnings("serial")
public class ExceptionAjaxMappingInterceptor extends
		ExceptionMappingInterceptor {
	
	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result;

        try {
            result = invocation.invoke();
        } catch (Exception e) {
            if (isLogEnabled()) {
                handleLogging(e);
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            if(isAjaxRequest(request)){
            	response.setContentType("application/json;charset=UTF-8");
                response.setHeader("Pragma", "No-cache");
     			response.setHeader("Cache-Control", "no-cache");
     			response.setDateHeader("Expires", 0);
     			response.getWriter().print(" {\"isError\": true, \"errorMsg\": \"" + e.getMessage() + "\"} ");
            	result = Action.NONE;
            }else{
	            List<ExceptionMappingConfig> exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
	            String mappedResult = this.findResultFromExceptions(exceptionMappings, e);
	            if (mappedResult != null) {
	                result = mappedResult;
	                publishException(invocation, new ExceptionHolder(e));
	            } else {
	                throw e;
	            }
            }
        }

        return result;
    }

	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
}
