package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.manager.TagManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name="success",location="/WEB-INF/page/component/pod_fans_of_guide.jsp")
})
public class ListFansOfGuideAction extends BaseAction<Object> {

	private static final int PAGE_SIZE = 11;
	private Long id;
	private Pagination page;
	@Autowired
	private TagManager tagManager;
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}

	@Action("list_fans_of_guide")
	public String list(){
		if(id!=null && id!=0L){
			page = tagManager.getFollowers(id, PAGE_SIZE, 0);
		}
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}
}
