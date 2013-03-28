package com.bocai.web.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.FollowingType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.vo.UserVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name="success",location="/WEB-INF/page/component/pod_fans_of_people.jsp"),
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","followerPager.*,followShip.*"}
	)
})
public class ListFollowedBokeAction extends BaseAction<User> {

	private static final int PAGE_SIZE = 13;
	private Long id;
	private int at;
	private User model;
	private Pagination followerPager;
	private Map<String, String> followShip;
	@Autowired
	private UserManager userManager;
	
	@Override
	public User getModel() {
		return model;
	}
	
	@Action("list_fans_of_boke")
	public String list(){
		if(id!=null && id!=0L){
			model = userManager.getById(id);
			followerPager = userManager.getUserFollow(id, PAGE_SIZE, at);
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	@Action("ajax_list_fans_of_boke")
	public String ajax(){
		if(id!=null && id!=0L){
			model = userManager.getById(id);
			Pagination p = userManager.getUserFollow(id, 20, at);
			followerPager = (Pagination) p.clone();
			followShip = new HashMap<String,String>();
			if(followerPager.getList()!=null &&
					!followerPager.getList().isEmpty()){
				List userlist = followerPager.getList();
				if(getSessionUser()!=null){
					for(int i = 0; i< userlist.size(); i++){
						User user = (User) userlist.get(i);
						String temp = userManager.isFollowed(getSessionUser().getId(), user)? "取消关注":"+关注";
						followShip.put(user.getId().toString(), temp);
					}
				}else{
					for(int i = 0; i< userlist.size(); i++){
						User user = (User) userlist.get(i);
						followShip.put(user.getId().toString(), "+关注");
					}
				}
				List<UserVo> voList = new ArrayList<UserVo>();
				for(int i = 0; i<userlist.size();i++){
					User user = (User) userlist.get(i);
					voList.add(UserVo.fromUserBasic(user));
				}
				followerPager.setList(voList);
			}
		}
		return AJAX;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Pagination getFollowerPager() {
		return followerPager;
	}

	public void setFollowerPager(Pagination followerPager) {
		this.followerPager = followerPager;
	}

	public void setModel(User model) {
		this.model = model;
	}

	public Map<String, String> getFollowShip() {
		return followShip;
	}

	public void setFollowShip(Map<String, String> followShip) {
		this.followShip = followShip;
	}
}
