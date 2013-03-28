package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.manager.PlaceManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name="success",location="/WEB-INF/page/component/pod_active_boke_of_place.jsp"),
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","bokePager.*"}
	)
})
public class ListActiveBoke extends BaseAction<Object> {

	private static final int PAGE_SIZE = 11;
	private Long id;
	private int at;
	private Pagination bokePager;
	@Autowired
	private PlaceManager placeManger;
	
	@Override
	public Object getModel() {
		return this;
	}
	
	@Action("list_active_boke")
	public String list(){
		if(id!=null && id!=0L){
			bokePager = placeManger.getSpottedUserByPlace(id, PAGE_SIZE, at);
		}
		return SUCCESS;
	}
	
	@Action("ajax_list_active_boke")
	public String ajax(){
		if(id!=null && id!=0L){
			bokePager = placeManger.getSpottedUserByPlace(id, PAGE_SIZE, at);
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

	public Pagination getBokePager() {
		return bokePager;
	}

	public void setBokePager(Pagination bokePager) {
		this.bokePager = bokePager;
	}

}
