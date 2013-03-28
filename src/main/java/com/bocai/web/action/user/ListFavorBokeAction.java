package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.FollowingType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.UserManager;
import com.bocai.util.StringUtil;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name="success",location="/WEB-INF/page/component/pod_fav_boke.jsp"),
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","nomedByPager.*, wanttedByPager.*"}
	)
})
public class ListFavorBokeAction extends BaseAction<Object> {

	private static final int PAGE_SIZE = 11;
	private String id;
	private int at;
	private Pagination nomedByPager;
	private Pagination wanttedByPager;
	@Autowired
	private UserManager userManager;
	
	@Action("list_favor_boke")
	public String list(){
		if(!StringUtil.isNULL(id)){
			nomedByPager = userManager.getNomedUsers(Long.parseLong(id), at, PAGE_SIZE);
			wanttedByPager = userManager.getWanttedUsers(Long.parseLong(id), at, PAGE_SIZE);
		}
		return SUCCESS;
	}
	
	@Action("ajax_list_favor_boke")
	public String ajax(){
		if(!StringUtil.isNULL(id)){
		}
		return AJAX;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Pagination getNomedByPager() {
		return nomedByPager;
	}

	public void setNomedByPager(Pagination nomedByPager) {
		this.nomedByPager = nomedByPager;
	}

	public Pagination getWanttedByPager() {
		return wanttedByPager;
	}

	public void setWanttedByPager(Pagination wanttedByPager) {
		this.wanttedByPager = wanttedByPager;
	}

}
