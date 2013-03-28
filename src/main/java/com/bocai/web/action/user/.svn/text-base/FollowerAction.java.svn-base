package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name="list",location="following.jsp")
})
public class FollowerAction extends BaseAction<User> {
	
	private User user;
	private Long id;
	private String type;
	@Autowired
	private UserManager userManager;
	
	@Action("follower")
	public String list(){
		if(id != null && id!=0L){
			user = userManager.getById(id);
			type = "follower";
		}else{
			throw new RuntimeException("User not found");
		}
		return LIST;
	}

	@Override
	public User getModel() {
		return user;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
