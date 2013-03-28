package com.bocai.web.action.user;

import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.User;
import com.bocai.manager.DialogManager;
import com.bocai.manager.NotificationManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.MsgCountVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "dashboard_following", location = "dashboard_following.jsp"),
	@Result(name = "dashboard_comment", location = "dashboard_comment_me.jsp"),
	@Result(name = "dashboard_msg", location = "dashboard_msg.jsp")
})
public class MyDashboardAction extends BaseAction<Object> {
	
	private final String DASHBOARD_FOLLOWING = "dashboard_following";
	private final String DASHBOARD_COMMENT = "dashboard_comment";
	private final String DASHBOARD_MSG = "dashboard_msg";
	private String cat;
	private User user;
	private Long followPlaceCount;
	private Long followPeopleCount;
	private MsgCountVo msgCountVo;
	@Autowired
	private UserManager userManager;
	@Autowired
	private NotificationManager notificationManager;
	@Autowired
	private DialogManager dialogManager;
	
	@ActionRole(ActionRoleType.USER)
	@Action("mydashboard")
	public String list(){
		user = getSessionUser();
		if(user == null){
			return LOGIN;
		}
		if(cat==null || "following".equals(cat)){
			followPlaceCount = userManager.getFollowingPlacesCount(user.getId());
			followPeopleCount = userManager.getFollowTosCount(user.getId());
			if(followPeopleCount==null) followPeopleCount = 0L;
			if(followPlaceCount==null) followPlaceCount = 0L;
			return DASHBOARD_FOLLOWING;
		}else if("comment".equals(cat)){
			return DASHBOARD_COMMENT;
		}else if("msg".equals(cat)){
			msgCountVo = new MsgCountVo();
			msgCountVo.setReminder(notificationManager.newNotificationCount(user.getId()));
			msgCountVo.setPrivateLetter(dialogManager.newPrivateLetterCount(user.getId()));
			msgCountVo.setTotal(msgCountVo.getPrivateLetter()+msgCountVo.getReminder());
			return DASHBOARD_MSG;
		}else{
			return DASHBOARD_FOLLOWING;
		}
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getFollowPlaceCount() {
		return followPlaceCount;
	}

	public void setFollowPlaceCount(Long followPlaceCount) {
		this.followPlaceCount = followPlaceCount;
	}

	public Long getFollowPeopleCount() {
		return followPeopleCount;
	}

	public void setFollowPeopleCount(Long followPeopleCount) {
		this.followPeopleCount = followPeopleCount;
	}

	public MsgCountVo getMsgCountVo() {
		return msgCountVo;
	}

	public void setMsgCountVo(MsgCountVo msgCountVo) {
		this.msgCountVo = msgCountVo;
	}
	
}
