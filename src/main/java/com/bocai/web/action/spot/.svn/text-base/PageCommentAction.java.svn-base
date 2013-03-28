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
import com.bocai.dao.domain.Spot;
import com.bocai.manager.CommentManager;
import com.bocai.manager.SpotManager;
import com.bocai.vo.CommentVO;
import com.bocai.vo.SpotVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax_page_comment", type = "json", 
			params = {"includeProperties","pager.*,pageAt,succeed,pageNo"}
	),
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","vo.*, more, pageAt"}
	)
})
public class PageCommentAction extends BaseAction<Pagination> {

	private static final String AJAX_PAGE_COMMENT = "ajax_page_comment"; 
	@Autowired 
	private CommentManager commentManager;
	@Autowired
	private SpotManager spotManager;
	private Pagination pager;
	private Long spotId;
	private SpotVo vo;
	private int pageAt;
	private int pageNo;
	private boolean succeed;
	private boolean more;
	
	@Action("ajax_page_comment")
	public String ajaxPage(){
		try{
			if(pageAt<0) pageAt = 0;
			Pagination p = commentManager.getCommentsOnSpot(spotId, 8, pageAt);
			pager = (Pagination)p.clone();
			if(pager.getList() != null &&
					!pager.getList().isEmpty()){
				List<CommentVO> cmtVos = new ArrayList<CommentVO>();
				for (Object cmt : pager.getList()){
					cmtVos.add(CommentVO.fromComment((Comment)cmt));
				}
				pager.setList(cmtVos);
			}
			pageNo = pager.getTotalPage();
			succeed = true;
		}catch(Exception e){
			succeed = false;
			this.addActionError(e.getMessage());
		}
		return AJAX_PAGE_COMMENT;
	}
	
	@Action("ajax_page_comment_in_spot")
	public String ajaxPageInSpot(){
		if(pageAt<0) pageAt = 0;
		Spot spot = spotManager.getById(spotId);
		vo = SpotVo.fromSpotMin(spot);
		Pagination p = commentManager.getCommentsOnSpot(spotId, 8, pageAt);
		pager = (Pagination)p.clone();
		pageAt = pager.getPageNo();
		if(pager.getList() != null &&
				!pager.getList().isEmpty()){
			List<CommentVO> cmtVos = new ArrayList<CommentVO>();
			for (Object cmt : pager.getList()){
				cmtVos.add(CommentVO.fromCommentBase((Comment)cmt));
			}
			vo.setComments(cmtVos);
		}
		if(!pager.isLastPage()){
			more = true;
		}
		return AJAX;
	}
	
	@Override
	public Pagination getModel() {
		return pager;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public CommentManager getCommentManager() {
		return commentManager;
	}

	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}

	public Pagination getPager() {
		return pager;
	}

	public void setPager(Pagination pager) {
		this.pager = pager;
	}

	public Long getSpotId() {
		return spotId;
	}

	public void setSpotId(Long spotId) {
		this.spotId = spotId;
	}

	public int getPageAt() {
		return pageAt;
	}

	public void setPageAt(int pageAt) {
		this.pageAt = pageAt;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public SpotVo getVo() {
		return vo;
	}

	public void setVo(SpotVo vo) {
		this.vo = vo;
	}

	public boolean isMore() {
		return more;
	}

	public void setMore(boolean more) {
		this.more = more;
	}
}
