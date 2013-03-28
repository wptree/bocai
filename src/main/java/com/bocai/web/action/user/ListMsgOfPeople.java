package com.bocai.web.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.Dialog;
import com.bocai.dao.domain.Notification;
import com.bocai.dao.domain.PrivateLetter;
import com.bocai.exception.NeedLoginException;
import com.bocai.manager.DialogManager;
import com.bocai.manager.FeedManager;
import com.bocai.manager.NotificationManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.DialogVo;
import com.bocai.vo.NotificationVo;
import com.bocai.vo.PrivateLetterVo;
import com.bocai.web.action.BaseAction;
import com.bocai.web.util.CookieUtils;

@SuppressWarnings({"serial","unchecked"})
@ParentPackage("user")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","pager.*,unreadCount"}
	),
	@Result(name = "ajax_dialog_detail", type = "json", 
			params = {"includeProperties","pager.*,dialogVo.*,unreadCount"}
	)
})
public class ListMsgOfPeople extends BaseAction<Object> {

	private static final int PAGE_SIZE = 20; 
	private int at;
	@Autowired
	private UserManager userManager;
	@Autowired
	private DialogManager dialogManager;
	@Autowired
	private NotificationManager notificationManager;
	private Pagination pager;
	private Long targetId;
	private DialogVo dialogVo;
	private long unreadCount;
	private boolean full;
	
	@ActionRole(ActionRoleType.USER)
	@Action("ajax_list_reminder")
	public String ajaxListReminder(){
		if(getSessionUser()==null){
			throw new NeedLoginException();
		}
//		Cookie cookie = CookieUtils.getCookie(request, 
//				UserConfig.COOKIE_USER_REMINDER_TIMELINE_PREFIX + getSessionUserId());
//		Long timeline = null;
//		if(cookie == null){
//			timeline = getSessionUser().getReminderTimeline();
//		}else{
//			timeline = Long.parseLong(cookie.getValue());
//		}
		unreadCount = notificationManager.newNotificationCount(getSessionUserId());
		Pagination p = null;
		if(full){
			p = notificationManager.fetch(getSessionUserId(), PAGE_SIZE, at);
		}else{
			p = notificationManager.getUnreadNoti(getSessionUserId(), PAGE_SIZE, at);
		}
		pager = (Pagination)p.clone();
		// update user notification time line
		// userManager.updateNotificationFetchTimeline(getSessionUser());
		List<Long> newNotiIds = new ArrayList<Long>();
		if(pager.getList()!=null && !pager.getList().isEmpty()){
			List<Notification> notis = (List<Notification>) pager.getList();
			List<NotificationVo> notiVos = new ArrayList<NotificationVo>();
			int len = notis.size();
			for (int i=0; i<len; i++){
				NotificationVo notiVo = NotificationVo.toBasic(notis.get(i));
				if(notiVo!=null){
					notiVos.add(notiVo);
					if(notiVo.getStatus()==0){
						newNotiIds.add(notiVo.getId());
					}
				}
			}
			pager.setList(notiVos);
		}
		if(newNotiIds.size()!=0){
			notificationManager.updateNotiAsReaded(newNotiIds);
		}
//		CookieUtils.addCookie(request, response, 
//				UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + getSessionUserId(), 
//				System.currentTimeMillis()+"",
//				UserConfig.COOKIE_MAX_AGE);
		return AJAX;
	}
	
	@ActionRole(ActionRoleType.USER)
	@Action("ajax_list_private_msg")
	public String ajaxListPrivateMsg(){
		if(getSessionUser()==null){
			throw new NeedLoginException();
		}
		unreadCount = dialogManager.newPrivateLetterCount(getSessionUserId());
		if(full){
			pager = dialogManager.getDialogsOfPeople(getSessionUserId(), at, PAGE_SIZE);
			if(pager.getList()!=null && !pager.getList().isEmpty()){
				List<Dialog> dialogs = (List<Dialog>) pager.getList();
				List<DialogVo> dialogVos = new ArrayList<DialogVo>();
				int len = dialogs.size();
				for (int i=0; i<len; i++){
					DialogVo dialogVo = DialogVo.toBasic(getSessionUserId(), dialogs.get(i));
					if(dialogVo!=null){
						dialogVos.add(dialogVo);
					}
				}
				pager.setList(dialogVos);
			}
		}else{
			pager = dialogManager.getUnreadDialogsOfPeople(getSessionUserId(), at, PAGE_SIZE);
			if(pager.getList()!=null && !pager.getList().isEmpty()){
				List<Object[]> objects = (List<Object[]>) pager.getList();
				List<DialogVo> dialogVos = new ArrayList<DialogVo>();
				int len = objects.size();
				for (int i=0; i<len; i++){
					Object[] objs = objects.get(i);
					if(objs == null || objs.length < 2) continue;
					Dialog dialog = (Dialog) objs[0];
					if(dialog == null) continue;
					dialog.setUnreadCount((Long) objs[1]);
					DialogVo dialogVo = DialogVo.toBasic(getSessionUserId(), dialog);
					if(dialogVo!=null){
						dialogVos.add(dialogVo);
					}
				}
				pager.setList(dialogVos);
			}
		}
		
		return AJAX;
	}
	
	
	@ActionRole(ActionRoleType.USER)
	@Action("ajax_list_private_msg_detail")
	public String ajaxListPrivateMsgDetail(){
		if(getSessionUser()==null){
			throw new NeedLoginException();
		}
		if(targetId==null){
			throw new RuntimeException("Invalid parameter [targetId]");
		}
		Dialog dialog = dialogManager.getDialog(getSessionUserId(), targetId);
		if(dialog==null){
			throw new RuntimeException("Can not find the specific dialog");
		}else{
			dialogVo = DialogVo.toBasic(getSessionUserId(), dialog);
		}
		Pagination p = dialogManager.getDialogDetail(getSessionUserId(), targetId, at, PAGE_SIZE);
		pager = (Pagination)p.clone();
		List<Long> newPlIds = new ArrayList<Long>();
		if(pager.getList()!=null && !pager.getList().isEmpty()){
			List<PrivateLetter> pls = (List<PrivateLetter>) pager.getList();
			List<PrivateLetterVo> plVos = new ArrayList<PrivateLetterVo>();
			int len = pls.size();
			for (int i=0; i<len; i++){
				PrivateLetterVo plVo = PrivateLetterVo.toBasic(getSessionUserId(), pls.get(i));
				if(plVo!=null){
					plVos.add(plVo);
					if(plVo.getStatus()==0){
						newPlIds.add(plVo.getId());
					}
				}
			}
			pager.setList(plVos);
		}
		if(newPlIds.size()!=0){
			dialogManager.updatePlAsReaded(newPlIds);
		}
		return "ajax_dialog_detail";
	}
	
	@Override
	public Object getModel() {return this;}

	@Override
	protected void prepareModel() throws Exception {}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Pagination getPager() {
		return pager;
	}

	public void setPager(Pagination pager) {
		this.pager = pager;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public DialogVo getDialogVo() {
		return dialogVo;
	}

	public void setDialogVo(DialogVo dialogVo) {
		this.dialogVo = dialogVo;
	}

	public long getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(long unreadCount) {
		this.unreadCount = unreadCount;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}
}
