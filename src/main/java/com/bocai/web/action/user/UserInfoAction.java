package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.vo.PopNameCardVo;
import com.bocai.vo.UserVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","model.*"}
		)
})
public class UserInfoAction extends BaseAction<PopNameCardVo> {

	private PopNameCardVo model;
	@Autowired
	private UserManager userManager;
	
	@Action("user_info")
	public String info(){
		if(model.getId()!=0L){
			User user = userManager.getById(model.getId());
			model.setId(user.getId());
			model.setName(user.getName());
			model.setAvatar(user.getAvatar());
			model.setCityName(user.getCityName());
			model.setFansNum(user.getFollowedByNumber()!=null?user.getFollowedByNumber():0);
			model.setFollowingNum(user.getFollowToNumber()!=null?user.getFollowToNumber():0);
			if(user.getSexy()!=null){
				model.setSexy(user.getSexy());
			}
			model.setSignature(user.getSignature());
			model.setTotalSpotCount(user.getTotalSpotCount()!=null?user.getTotalSpotCount():0);
			if(getSessionUserId()==null){
				model.setFollowed(false);
			}else{
				model.setFollowed(userManager.isFollowed(getSessionUserId(), user));
			}
		}
		return AJAX;
	}
	
	@Override
	public PopNameCardVo getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = new PopNameCardVo();
	}
	
	public void setModel(PopNameCardVo model) {
		this.model = model;
	}
}
