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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.User;
import com.bocai.service.IPSeeker;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * <p>
 * Base action class.
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
public abstract class BaseAction<T> extends ActionSupport implements ServletRequestAware, 
		ServletResponseAware, ModelDriven<T>, Preparable, SessionAware{

	private static final long serialVersionUID = 4270135652880555826L; 
	private static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	protected static final String RESULT_404 = "404";
	protected static final String DETAIL = "detail";
	protected static final String LIST = "list";
	protected static final String RELOAD = "reload";
	protected static final String AJAX = "ajax";
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception{
		return SUCCESS;
	}
	
	public String detail() throws Exception{
		return SUCCESS;
	}

	@Override
	public String input() throws Exception{
		return SUCCESS;
	}
	
	public String save() throws Exception{
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		return SUCCESS;
	}
	
	protected abstract void prepareModel() throws Exception;
	
	public void prepare() throws Exception{
		request.setAttribute("staticContext", SystemConfig.staticContext());
		request.setAttribute("imageContext", SystemConfig.imageContext());
		prepareModel();
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}
	
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
	
	public User getSessionUser() {
		if (session != null
				&& session.containsKey(AppConstant.SESSION_LOGIN_USER)) {
			return (User) session.get(AppConstant.SESSION_LOGIN_USER);
		}
		return null;
	}
	
	public Long getSessionUserId(){
		if(session != null 
				&& session.containsKey(AppConstant.SESSION_LOGIN_USER_ID)){
			return (Long) session.get(AppConstant.SESSION_LOGIN_USER_ID);
		}
		return null;
	}
	
	public String getContextPath(){
		return ServletActionContext.getServletContext().getRealPath("/");
	}
	
	public String getIpAddr() {
	       String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       if( ip != null && (ip.length() == 0 || "unknown".equalsIgnoreCase(ip))){
	    	   ip = null;
	       }
	       logger.debug("get ip address [" + ip + "] via http request.");
	       return ip;
	   } 

}
