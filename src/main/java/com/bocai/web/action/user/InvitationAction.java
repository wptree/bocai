package com.bocai.web.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.User;
import com.bocai.manager.InvitationManager;
import com.bocai.service.MailService;
import com.bocai.vo.UserVo;
import com.bocai.web.action.BaseAction;


@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "invite_input",type = "redirect", location = "/sign_up.bc",
			params = {"email","${email}","id","${id}"}
	),
	@Result(name = "ajax_get_data_page", type = "json", 
			params = {"includeProperties","dataPage.*","excludeProperties",".*hibernateLazyInitializer"}
		),
	@Result(name = "ajax_send_result", type = "json", 
		params = {"includeProperties","sendResult,returnMsg"}
	)
})
public class InvitationAction extends BaseAction<User>{
	private User model;
	private boolean sendResult;
	private String returnMsg;
	private String emailList;
	private Pagination dataPage;
	private String email;
	private Long id;
	
	@Autowired
	private MailService mailService;
	@Autowired
	private InvitationManager invitationManager;
	
	@Action("invite")
	public String invite(){
		return "invite_input";
	}
	
	public String send(){
		try {
			if (emailList.indexOf(";") > 0) {
				String[] list = emailList.split(";");
				for (String email : list) {
					mailService.invite(UserVo.fromUserMin(model), email);
				}
			} else {
				mailService.invite(UserVo.fromUserMin(model), emailList);
			}
			sendResult = true;
			returnMsg = "邀请发送成功！";
		} catch (Exception e) {
			e.printStackTrace();
			sendResult = false;
			returnMsg = "邀请暂时没有发送成功，请稍后再试，或者用其它方式邀请...";
		}
		
		return "ajax_send_result";
	}
	
	public String fetchInvited(){
		
		dataPage = invitationManager.fetchInvited(id, 10, 0);
		
		return "ajax_get_data_page";
	}

	@Override
	public User getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = getSessionUser();
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public String getEmailList() {
		return emailList;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setSendResult(boolean sendResult) {
		this.sendResult = sendResult;
	}

	public boolean isSendResult() {
		return sendResult;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setDataPage(Pagination dataPage) {
		this.dataPage = dataPage;
	}

	public Pagination getDataPage() {
		return dataPage;
	}

}
