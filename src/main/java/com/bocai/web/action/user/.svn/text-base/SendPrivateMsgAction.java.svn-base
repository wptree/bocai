package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.dao.domain.User;
import com.bocai.exception.NeedLoginException;
import com.bocai.manager.DialogManager;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","succeed"}
	)
})
public class SendPrivateMsgAction extends BaseAction<Object> {

	private Long targetId;
	private String targetName;
	private String content;
	@Autowired
	private DialogManager dialogManager;
	@Autowired
	private UserManager userManager;
	private boolean succeed;
	
	@ActionRole(ActionRoleType.USER)
	@Action("send_private_msg")
	public String send(){
		if(getSessionUser()==null){
			throw new NeedLoginException();
		}
		if(content == null || (
			targetName == null && (
			  targetId==null || targetId==0L))){
			throw new RuntimeException("Invalid paramter");
		}
		
		if(targetId ==null || targetId==0L){
			User target = userManager.getUniqueByProperty("name",targetName);
			if(target==null){
				throw new RuntimeException("Can not find target user with name:" + targetName);
			}
			targetId = target.getId();
		}
		dialogManager.sendPrivateLetter(getSessionUserId(), targetId, content, 0);
		succeed = true;
		return AJAX;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	
	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
}
