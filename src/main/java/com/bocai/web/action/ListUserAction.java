package com.bocai.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.vo.UserVo;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "user_list", location = "component/user_list.jsp"),
	@Result(name = "ajax_get_user", type = "json", 
			params = {"includeProperties","userInfo.*"}
		)
})
public class ListUserAction extends BaseAction<Object> {
	private static final String USER_LIST = "user_list";
	private static final int LIST_BEST = 1;
	private static final int LIST_LATEST = 2;
	@Autowired 
	private UserManager userManager;
	private List<User> users;
	private int type;
	
	private UserVo userInfo;
	private Long userId;
	
	@Action("get_user")
	public String ajaxUserInfo(){
		User u = userManager.getById(userId);
		if(u!=null){
			userInfo = UserVo.fromUserMin(u);
		}
		return "ajax_get_user";
	}

	@SuppressWarnings("unchecked")
	@Action("list_user")
	public String list(){
		try{
			switch(type){
				case LIST_BEST:
					users = (List<User>) userManager.getBestUsers(10,0).getList();
					break;
				case LIST_LATEST:
					users = (List<User>) userManager.getLatestUsers(10,0).getList();
					break;
				default:
					break;
			}
		}catch(Exception e){
			addActionError(e.getMessage());
		}
		return USER_LIST;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		users = null;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserInfo(UserVo user) {
		this.userInfo = user;
	}

	public UserVo getUserInfo() {
		return userInfo;
	}

}
