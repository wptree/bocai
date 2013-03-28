package com.bocai.web.action.user;

import javax.servlet.http.Cookie;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.User;
import com.bocai.exception.NeedLoginException;
import com.bocai.manager.DialogManager;
import com.bocai.manager.NotificationManager;
import com.bocai.vo.MsgCountVo;
import com.bocai.web.action.BaseAction;
import com.bocai.web.util.CookieUtils;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","vo.*","excludeProperties",".*hibernateLazyInitializer"})
})
public class CountMsgOfPeopleAction extends BaseAction<Object> {
	
	@Autowired
	private NotificationManager notificationManager;
	@Autowired
	private DialogManager dialogManager;
	private MsgCountVo vo;
	
	@Action("count_msg")
	@ActionRole(ActionRoleType.USER)
	public String count(){
		User user = getSessionUser();
		if(user == null){
			throw new NeedLoginException();
		}
//		Long reminderTimeline = null;
//		Long plTimeline = null;
//		Cookie cookie = CookieUtils.getCookie(request, 
//				UserConfig.COOKIE_USER_REMINDER_TIMELINE_PREFIX + user.getId());
//		if(cookie != null){
//			reminderTimeline = Long.parseLong(cookie.getValue());
//		}
//		if(reminderTimeline == null){
//			reminderTimeline = user.getReminderTimeline();
//		}
//		cookie = CookieUtils.getCookie(request, 
//				UserConfig.COOKIE_USER_PRIVATE_LETTER_TIMELINE_PREFIX + user.getId());
//		if(cookie != null){
//			plTimeline = Long.parseLong(cookie.getValue());
//		}
//		if(plTimeline == null){
//			plTimeline = user.getPrivateLetterTimeline();
//		}
		
		vo.setReminder(notificationManager.newNotificationCount(user.getId()));
		vo.setPrivateLetter(dialogManager.newPrivateLetterCount(user.getId()));
		vo.setTotal(vo.getPrivateLetter()+vo.getReminder());
		return AJAX;
	}

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		vo = new MsgCountVo();
	}

	public MsgCountVo getVo() {
		return vo;
	}

	public void setVo(MsgCountVo vo) {
		this.vo = vo;
	}

}
