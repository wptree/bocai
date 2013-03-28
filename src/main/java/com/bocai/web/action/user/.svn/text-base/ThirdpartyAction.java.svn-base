package com.bocai.web.action.user;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.UserThirdpartyManager;
import com.bocai.service.RenrenService;
import com.bocai.service.SinaService;
import com.bocai.util.XiaoneiRestfulApiUtil;
import com.bocai.web.action.BaseAction;

@ParentPackage("user")
@Results({
	@Result(name = "send_input", location = "sendContent.jsp"),
	@Result(name = "ajax_send_result", type = "json", 
		params = {"includeProperties","returnMsg,sendResult"}
	)
})
public class ThirdpartyAction  extends BaseAction<User>{
	private static final long serialVersionUID = -6073848428561314220L;
	private User model;
	
	@Autowired
	private RenrenService renrenService;
	@Autowired
	private SinaService sinaService;
	@Autowired
	private UserThirdpartyManager thirdpartyManager;
	
	
	private String sendToSina;
	private String sendToRenren;
	private String content;
	private String returnMsg;
	private boolean sendResult;
	
	public String input(){
		return "send_input";
	}
	
	public String sendStatus(){
		User user = getModel();
		
		if("on".equals(getSendToRenren())){
			String sessionkeyJson = (String) session.get("renren_sessionkeyJson");
			if(sessionkeyJson!=null){
				try {
					String result = renrenService.sendStatus(XiaoneiRestfulApiUtil.getSessionKey(sessionkeyJson), content);
					if("1".equals(result)){
						setReturnMsg("发送成功！");
						setSendResult(true);
					}else{
						setReturnMsg(result);
						setSendResult(false);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					setReturnMsg("发送失败！");
					setSendResult(false);
				}
			}
		}
		
		if("on".equals(getSendToSina())){
			try {
				UserThirdparty thrdpty = user.sinaAccount();
				if(thrdpty.getToken()==null || thrdpty.getTokenSecret()==null){
					thirdpartyManager.getUserById(thrdpty.getId());
				}
				sinaService.sendTextMsg(thrdpty.getToken(), thrdpty.getTokenSecret(), content);
				setReturnMsg("发送成功！");
				setSendResult(true);
			} catch (Exception e) {
				e.printStackTrace();
				setReturnMsg(e.getMessage());
				setSendResult(false);
			}
		}
		
		return "ajax_send_result";
	}
	

	@Override
	public User getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = getSessionUser();
	}
	public String getSendToSina() {
		return sendToSina;
	}

	public void setSendToSina(String sendToSina) {
		this.sendToSina = sendToSina;
	}

	public String getSendToRenren() {
		return sendToRenren;
	}

	public void setSendToRenren(String sendToRenren) {
		this.sendToRenren = sendToRenren;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public void setRenrenService(RenrenService renrenService) {
		this.renrenService = renrenService;
	}


	public RenrenService getRenrenService() {
		return renrenService;
	}


	public void setSinaService(SinaService sinaService) {
		this.sinaService = sinaService;
	}


	public SinaService getSinaService() {
		return sinaService;
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

	public void setThirdpartyManager(UserThirdpartyManager thirdpartyManager) {
		this.thirdpartyManager = thirdpartyManager;
	}

	public UserThirdpartyManager getThirdpartyManager() {
		return thirdpartyManager;
	}

}
