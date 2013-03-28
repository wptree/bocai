package com.bocai.web.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.constant.FollowingType;
import com.bocai.common.page.Pagination;
import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Statistical;
import com.bocai.dao.domain.User;
import com.bocai.manager.CommentManager;
import com.bocai.manager.FeedManager;
import com.bocai.manager.NotificationManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.CommentVO;
import com.bocai.web.action.BaseAction;
import com.bocai.web.util.CookieUtils;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "input", location = "dashboard.jsp"),
	@Result(name = "ajax_get_data_page", type = "json", 
			params = {"includeProperties","lasttime,dataPage.*","excludeProperties",".*hibernateLazyInitializer"}
		),
	@Result(name = "ajax_get_data_list", type = "json", 
			params = {"includeProperties","dataList.*","excludeProperties",".*hibernateLazyInitializer"}
		),
	@Result(name = "ajax_get_new_count", type = "json", 
			params = {"includeProperties","newCount"}
		)
		
})
public class DashboardAction extends BaseAction<User>{
	private User model;
	private Statistical statistical;
	private String activityType;
	private int pageAt;
	private Pagination dataPage;
	private List dataList;
	private Long newCount;
	private Integer active;
	private String lasttime;
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private SpotManager spotManager;
	@Autowired
	private FeedManager feedManager;
	@Autowired
	private NotificationManager notificationManager;
	@Autowired
	private CommentManager commentManager;
	
	@ActionRole(ActionRoleType.USER)
	@Action("dashboard")
	public String dashboard(){
		statistical = userManager.getStatistical(getModel());
		if(statistical == null){
			statistical = new Statistical();
		}
		request.setAttribute("statistical", statistical);
		
		Pagination latestSpotPage = spotManager.getLatestSpotsByCity("", 10, 0);
		request.setAttribute("latestSpotPage", latestSpotPage);
		
		
		request.setAttribute("active", getActive()==null?1:getActive());
		
		request.setAttribute("user", getModel());
		
		return "input";
	}
	@ActionRole(ActionRoleType.USER)
	public String myFeedPage(){
//		Cookie cookie = CookieUtils.getCookie(request, UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + getModel().getId());
//		if(cookie != null){
//			lasttime = cookie.getValue();
//		}else{
//			lasttime = getModel().getFeedFetchTimeline().toString();
//		}
		
		if(activityType==null){
			dataPage = feedManager.fetch(getModel().getId(), 20, getPageAt());
		}else{
			ActivityType type = ActivityType.valueOf(activityType);
			dataPage = feedManager.fetch(getModel().getId(), type, 20, getPageAt());
		}
		
		userManager.updateFeedFetchTimeline(getModel());
		
		//CookieUtils.addCookie(request, response, UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + getModel().getId(), System.currentTimeMillis()+"",UserConfig.COOKIE_MAX_AGE);
			
		return "ajax_get_data_page";
	}
	
	
	@ActionRole(ActionRoleType.USER)
	public String myFeedList(){
		ActivityType type = ActivityType.valueOf(activityType);
		
		
		dataList = feedManager.fetch(getModel(), type);
		
		
		return "ajax_get_data_list";
	}
	@ActionRole(ActionRoleType.USER)
	public String newFeedNum(){
		Long timeline = null;
//		Cookie cookie = CookieUtils.getCookie(request, UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + getModel().getId());
//		if(cookie!=null){
//			timeline = Long.parseLong(cookie.getValue());
//		}
		
		newCount = feedManager.newFeedCount(getModel(),timeline);
		
		return "ajax_get_new_count";
	}
	
	@ActionRole(ActionRoleType.USER)
	public String myNotificationPage(){
		Cookie cookie = CookieUtils.getCookie(request, UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + getModel().getId());
		if(cookie != null){
			lasttime = cookie.getValue();
		}else{
			lasttime = getModel().getNotificatonFetchTimeline().toString();
		}
		
		dataPage = notificationManager.fetch(getModel().getId(), 20, getPageAt());
		userManager.updateNotificationFetchTimeline(getModel());
		
		CookieUtils.addCookie(request, response, UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + getModel().getId(), System.currentTimeMillis()+"",UserConfig.COOKIE_MAX_AGE);
		
		return "ajax_get_data_page";
	}
	@ActionRole(ActionRoleType.USER)
	public String newNotificationNum(){
		Long timeline = null;
		Cookie cookie = CookieUtils.getCookie(request, UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + getModel().getId());
		if(cookie!=null){
			timeline = Long.parseLong(cookie.getValue());
		}
		
//		newCount = notificationManager.newNotificationCount(getModel(),timeline);
		return "ajax_get_new_count";
	}
	@ActionRole(ActionRoleType.USER)
	public String myNotificationList(){
		dataList = notificationManager.fetch(getModel());
		
		return "ajax_get_data_list";
	}
	
	
	@ActionRole(ActionRoleType.USER)
	public String myCommentPage(){
		Pagination p = commentManager.getUserCommentPage(getModel().getId(), 20, getPageAt());
		dataPage = (Pagination)p.clone();
		if(dataPage.getList()!=null &&
				!dataPage.getList().isEmpty()){
			List list = dataPage.getList();
			List<CommentVO> voList = new ArrayList<CommentVO>();
			for(int i = 0 ; i< list.size(); i++){
				Comment cm = (Comment) list.get(i);
				CommentVO vo = CommentVO.fromComment(cm);
				voList.add(vo);
			}
			dataPage.setList(voList);
		}
		
		return "ajax_get_data_page";
	}
	
	@ActionRole(ActionRoleType.USER)
	public String myPublished(){
		dataPage = feedManager.fetch(getSessionUserId(), 20, 0);
		
		return "ajax_get_data_page";
	}
	
	
	@Override
	public User getModel() {
		if(model == null){
			model = getSessionUser();
		}
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = getSessionUser();
	}

	public void setModel(User model) {
		this.model = model;
	}


	public void setStatistical(Statistical statistical) {
		this.statistical = statistical;
	}


	public Statistical getStatistical() {
		return statistical;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setPageAt(int pageAt) {
		this.pageAt = pageAt;
	}

	public int getPageAt() {
		return pageAt;
	}

	public void setDataPage(Pagination dataPage) {
		this.dataPage = dataPage;
	}

	public Pagination getDataPage() {
		return dataPage;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public List getDataList() {
		return dataList;
	}

	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}

	public Long getNewCount() {
		return newCount;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Integer getActive() {
		return active;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getLasttime() {
		return lasttime;
	}

}
