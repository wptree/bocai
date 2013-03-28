package com.bocai.web.action.spot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.PostStatus;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Reply;
import com.bocai.dao.domain.Spot;
import com.bocai.manager.CommentManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.CommentVO;
import com.bocai.web.action.BaseAction;
import com.bocai.web.util.ContentUtil;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","vo.*,"}
	)
})
public class AddCommentAction extends BaseAction<Comment> {

	@Autowired 
	private CommentManager commentManager;
	@Autowired 
	private UserManager userManager;
	@Autowired 
	private SpotManager spotManager;
	private Comment model;
	private CommentVO vo;
	private Long spotId;
	private String content;
	private String replyStr;
	
	@Action("ajax_add_comment")
	public String ajaxAdd(){
		if(spotId!=null  && spotId!=0L){
			Spot spot = spotManager.getById(spotId);
			if(spot==null) throw new RuntimeException("找不到指定的分享！");
			if(!StringUtils.isEmpty(replyStr)){
				String[] strs = replyStr.split(":");
				if(strs.length>2){
					Long id = Long.valueOf(strs[0]);
					String name = strs[1];
					Long cmtId = Long.valueOf(strs[2]);
					if(!StringUtils.isEmpty(content) && 
							content.indexOf("@"+name) != -1){
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("content", content);
						params.put("name", name);
						params.put("id", id);
						params.put("contextPath", request.getContextPath());
						content = ContentUtil.htmlReply(params);
						model = new Reply();
						((Reply)model).setReplyTo(userManager.getById(id));
						((Reply)model).setOnComment(commentManager.getById(cmtId));
					}
				}
			}
			model.setPost(spot);
			model.setStatus(PostStatus.NORMAL);
			model.setContent(content);
			model.setCreatedAt(new Date());
			model.setUpdatedAt(model.getCreatedAt());
			model.setCreatedBy(getSessionUser());
			model.setCommentBy(getSessionUser());
			commentManager.save(model, request.getContextPath());

			vo = CommentVO.fromComment(model);
		}
		return AJAX;
	}
	
	@Override
	public Comment getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = new Comment();
		vo = new CommentVO();
	}

	public CommentManager getCommentManager() {
		return commentManager;
	}

	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public Long getSpotId() {
		return spotId;
	}

	public void setSpotId(Long spotId) {
		this.spotId = spotId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setModel(Comment model) {
		this.model = model;
	}

	public CommentVO getVo() {
		return vo;
	}

	public void setVo(CommentVO vo) {
		this.vo = vo;
	}

	public String getReplyStr() {
		return replyStr;
	}

	public void setReplyStr(String replyStr) {
		this.replyStr = replyStr;
	}
}
