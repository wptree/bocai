package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "input", location = "mymsg.jsp")
})
public class MyMsgAction extends BaseAction<User> {

	@Autowired 
	private UserManager userManager;
	private User model;
	private Long id;
	
	@Action("my_msg")
	@ActionRole( ActionRoleType.USER )
	public String input(){
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = getSessionUser();
	}
	
	@Override
	public User getModel() {
		return model;
	}
	public void setModel(User user){
		this.model = user;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
