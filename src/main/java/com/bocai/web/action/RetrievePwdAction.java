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
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "input", location = "forget_pwd.jsp"),
	@Result(name = "retrieve_input", location = "retrieve_pwd.jsp"),
	@Result(name = "token_invalid", location = "token_invalid.jsp"),
	@Result(name = "ajax", type = "json", 
		params = {"includeProperties","succeed,email,frontMsg"}
	),
	@Result(name = "ajax_verify_result", type = "json", 
			params = {"includeProperties","checkResult"}
	)
})
public class RetrievePwdAction extends BaseAction<User> {

	private static final String AJAX_VERIFY_RESULT = "ajax_verify_result";
	private static final String RETRIEVE_INPUT = "retrieve_input";
	private static final String TOKEN_INVALID = "token_invalid";
	private static final String V_ERROR = "error";
	private static final String V_SUCCESS = "success";
	private static final String V_INVALID = "invalid";

	private Long uid;
	private String token;
	private String email;
	private User model;
	private String frontMsg;
	private boolean checkResult;
	private boolean succeed;
	private String type;
	private String title;
	@Autowired
	private UserManager userManager;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserTokenManager userTokenManager;
	
	@Action("forget_pwd")
	public String input(){
		return "input";
	}
	
	@Action("mail_pwd")
	@Validations(
		emails = {
			@EmailValidator(fieldName="firstEmail", message="邮箱格式不正确！")
		},
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "firstEmail", message = "注册邮箱不能为空!")
		}
	)
	@InputConfig(resultName = AJAX)
	public String mail(){
		if(model.getFirstEmail()!=null && model.getFirstEmail().length()!=0){
			model = userManager.getUniqueByProperty("firstEmail", model.getFirstEmail());
			setEmail(model.getFirstEmail());
			if(model!=null){
				model.setStatus(UserStatus.PWD_RESET_REQUESTED);
				userManager.update(model);
				mailService.forgetPasswordValidation(UserVo.fromUserMin(model));
				setSucceed(true);
			}else{
				setFrontMsg("用户"+model.getFirstEmail()+"不存在！");
			}
		}else{
			setFrontMsg("注册邮箱不能为空！");
		}
		return AJAX;
	}
	
	@Action("retrieve_pwd")
	public String retrieve() {
		String result = RETRIEVE_INPUT;
		title = "密码重置";
		type = V_ERROR;

		if (uid != null && uid != 0L) {
			model = userManager.getById(uid);
			if (model != null
					&& !userTokenManager.needVerify(uid,
							UserTokenType.FORGET_PWD)) {
				type = V_INVALID;
			} else if (model != null) {
				UserToken ut = userTokenManager.verifyToken(uid, token);
				if (ut != null) {
					if (ut.getStatus() == UserTokenStatus.CLOSE) {
						type = V_INVALID;
					} else {
						ut.setStatus(UserTokenStatus.CLOSE);
						ut.setCloseAt(new Date());
						userTokenManager.update(ut);
						type = V_SUCCESS;
					}
				}
			}
		}
		
		if(V_ERROR.equals(type)){
			frontMsg = "密码重置链接不正确！";
			result = TOKEN_INVALID;
		}else if(V_INVALID.equals(type)){
			frontMsg = "密码重置链接已过期！";
			result = TOKEN_INVALID;
		}
				
		return result;
	}
	
	@Action("commit_pwd")
	@Validations(
		emails = {
			@EmailValidator(fieldName="firstEmail", message="邮箱格式不正确！")
		},
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "firstEmail", message = "注册邮箱不能为空!"),
			@RequiredStringValidator(fieldName = "password", message = "密码不能为空!")
		}
	)
	@InputConfig(resultName = AJAX)
	public String commit(){
		User user = userManager.getUniqueByProperty("firstEmail", model.getFirstEmail());
		if(user==null){
			setSucceed(false);
			setFrontMsg("用户" + model.getFirstEmail() + "不存在");
		}else{
			user.setPassword(model.getPassword());
			user.setStatus(UserStatus.NORMAL);
			userManager.update(user);
			setSucceed(true);
		}
		return AJAX;
	}
	
	@Action("verify_user_mail")
	public String verifyUserExist(){
		checkResult = userManager.exist(model.getFirstEmail(),false) == null ? true : false;
		return AJAX_VERIFY_RESULT;
	}
	
	@Override
	public User getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		model = new User();
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public void setModel(User model) {
		this.model = model;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getFrontMsg() {
		return frontMsg;
	}

	public void setFrontMsg(String frontMsg) {
		this.frontMsg = frontMsg;
	}

	public boolean isCheckResult() {
		return checkResult;
	}

	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
