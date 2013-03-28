package com.bocai.web.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.UserStatus;
import com.bocai.common.constant.UserTokenStatus;
import com.bocai.common.constant.UserTokenType;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserToken;
import com.bocai.manager.UserManager;
import com.bocai.manager.UserTokenManager;
import com.bocai.service.MailService;
import com.bocai.vo.UserVo;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "verify_success", location = "verify_success.jsp"),
	@Result(name = "verify_error", location = "verify_error.jsp"),
	@Result(name = "ajax_success", type = "json", 
			params = {"includeProperties","succeed"}
	)
})
public class SecureAction extends BaseAction<Object> {
	
	private static final String VERIFY_SUCCESS = "verify_success";
	private static final String AJAX_SUCCESS = "ajax_success";
	private static final String VERIFY_ERROR = "verify_error";
	private static final String V_SUCCESS = "success";
	private static final String V_REVALIDATION = "revalidation";
	
	private Long uid;
	private String token;
	private String email;
	private String type;
	private boolean succeed;
	
	@Autowired
	private UserTokenManager userTokenManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private MailService mailService;

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}
	
	@Action("secure")
	public String execute(){
		if(uid!=null && uid != 0L){
			User user = userManager.getById(uid);
			if(user==null){
				uid = null;
				return VERIFY_ERROR;
			}
			email = user.getFirstEmail();
			if(!userTokenManager.needVerify(uid, UserTokenType.SIGN_UP_VALIDATION)){
				type = V_REVALIDATION;
				return VERIFY_SUCCESS;
			}
			UserToken ut = userTokenManager.verifyToken(uid, token);
			if(ut!=null){
				if(ut.getStatus() == UserTokenStatus.CLOSE){
					type = V_REVALIDATION;
				}else{
					user.setStatus(UserStatus.NORMAL);
					userManager.update(user);
					ut.setStatus(UserTokenStatus.CLOSE);
					ut.setCloseAt(new Date());
					userTokenManager.update(ut);
					type = V_SUCCESS;
				}
				return VERIFY_SUCCESS;
			}else{
				uid = null;
				return VERIFY_ERROR;
			}
		}else{
			uid = null;
			return VERIFY_ERROR;
		}
	}
	
	@Action("reverify")
	public String reVerifyEmail(){
		if(uid!=null && uid != 0L){
			User user = userManager.getById(uid);
			if(user == null){
				uid = null;
				throw new RuntimeException("用户不存在，无法重发认证邮件");
			}
			UserVo userVo = UserVo.fromUserMin(user); 
			mailService.signUpValidation(userVo);
			setSucceed(true);
		}
		return AJAX_SUCCESS;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	
}
