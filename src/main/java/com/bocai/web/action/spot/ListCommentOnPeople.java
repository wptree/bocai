package com.bocai.web.action.spot;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Comment;
import com.bocai.manager.CommentManager;
import com.bocai.vo.CommentVO;
import com.bocai.web.action.BaseAction;

@SuppressWarnings({"serial","unchecked"})
@ParentPackage("spot")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","pager.*"}
	)
})
public class ListCommentOnPeople extends BaseAction<Object> {

	private final int PAGE_SIZE = 20;
	private Long uid;
	private int at;
	private Pagination pager;
	@Autowired
	private CommentManager commentManager;
	
	@Action("ajax_list_cmt_on_people")
	public String ajaxList(){
		if(uid==null ||uid==0){
			throw new RuntimeException("Invalid paramerter uid");
		}
		Pagination p = commentManager.getCommentsOnPeople(uid, PAGE_SIZE, at);
		pager = (Pagination) p.clone();
		if(pager.getList()!=null &&
				!pager.getList().isEmpty()){
			List<Comment> cmts = (List<Comment>)pager.getList();
			List<CommentVO> cmtVos = new ArrayList<CommentVO>();
			for (Comment cmt : cmts){
				if(cmt!=null){
					cmtVos.add(CommentVO.fromComment(cmt));
				}
			}
			pager.setList(cmtVos);
		}
		return AJAX;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

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
}
