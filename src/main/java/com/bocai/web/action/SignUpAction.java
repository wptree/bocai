package com.bocai.web.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.Invitation;
import com.bocai.dao.domain.User;
import com.bocai.manager.InvitationManager;
import com.bocai.manager.UserManager;
import com.bocai.service.MailService;
import com.bocai.vo.UserVo;
import com.bocai.web.util.CookieUtils;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "signup_input", location = "login.jsp"),
	@Result(name = "signup_success", location = "main.bc", type = "redirect"),
	@Result(name = "signup_error", location = "login.bc", type="redirect"),
	@Result(name = "ajax_signup_result", type = "json", 
		params = {"includeProperties","signUpResult,returnMsg"}
	),
	@Result(name = "ajax_check_result", type = "json", 
			params = {"includeProperties","checkResult"}
	)
})
public class SignUpAction extends BaseAction<User> {

	private static final String SIGNUP_INPUT = "signup_input";
	private static final String SIGNUP_SUCCESS = "signup_success";
	private static final String SIGNUP_ERROR = "signup_error";
	private static final String AJAX_SIGNUP_RESULT = "ajax_signup_result";
	private static final String AJAX_CHECK_RESULT = "ajax_check_result";
	@Autowired 
	private UserManager userManager;
	@Autowired 
	private MailService mailService;
	@Autowired
	private InvitationManager invitationManager;
	
	private User model;
	private String authCode;
	private boolean checkResult;
	private boolean signUpResult;
	private String returnMsg;
	private int type;
	private Long inviteFromId;
	
	@Action("sign_up")
	public String input(){
		return SIGNUP_INPUT;
	}
	
	@Validations(
		emails = {
			@EmailValidator(fieldName = "model.firstEmail", message = "邮箱格式不正确!")
		},
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "model.password", message = "密码不能为空!")
		}
	)
	@InputConfig(resultName = SIGNUP_ERROR )
	public String signUp(){
		try{
			model.setFirstEmail(model.getFirstEmail().toLowerCase());
			model = userManager.registerUser(model);
			mailService.signUpValidation(UserVo.fromUserMin(model));
			if(model!=null){
				signUpResult = true;
				session.put(AppConstant.SESSION_LOGIN_USER_ID, model.getId());
			}
			
			setCookie(model);
		}catch(Exception e){
			addActionError(e.getMessage());
			return SIGNUP_ERROR;
		}
		return SIGNUP_SUCCESS;
	}
	
	private void setCookie(User user){
//		if(CookieUtils.getCookie(request, UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + user.getId())==null){
//			CookieUtils.addCookie(request, response, UserConfig.COOKIE_USER_FEED_TIMELINE_PREFIX + user.getId(), System.currentTimeMillis()+"",UserConfig.COOKIE_MAX_AGE);
//		}
		if(CookieUtils.getCookie(request, UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + user.getId())==null){
			CookieUtils.addCookie(request, response, UserConfig.COOKIE_USER_NOTIFY_TIMELINE_PREFIX + user.getId(), System.currentTimeMillis()+"",UserConfig.COOKIE_MAX_AGE);

		}
	}
	
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "model.firstEmail", message = "注册邮箱不能为空!"),
			@RequiredStringValidator(fieldName = "model.password", message = "密码不能为空!")
		},
		emails = {
			@EmailValidator(fieldName = "model.firstEmail", message = "邮箱格式不正确!")
		},
		stringLengthFields = {
			@StringLengthFieldValidator(fieldName="model.password", minLength="3", maxLength="20", message="密码长度须为3-20位！")	
		}
	)
	@InputConfig(resultName = AJAX_SIGNUP_RESULT )
	public String ajaxSignUp() {
		try {
			model.setFirstEmail(model.getFirstEmail().toLowerCase());
			checkResult = userManager.exist(model.getFirstEmail(),false) == null ? false : true;
			if(checkResult){
				signUpResult = false;
				returnMsg = "邮箱"+model.getFirstEmail()+"已经被注册了";
			}else{
				model = userManager.registerUser(model);
				
				//been invited to register, don't need to verify email
				if(inviteFromId != null){
					Invitation inv = new Invitation();
					inv.setFromUid(inviteFromId);
					inv.setTargetName(model.getName());
					inv.setTargetEmail(model.getFirstEmail());
					inv.setInviteTime(new Date());
					inv.setStatus(1);
					inv.setTargetId(model.getId());
					
					invitationManager.save(inv);
					
					User from = userManager.getById(inviteFromId);
					from.setScore(from.getScore()+100);
					userManager.makeFollow(model, from);
					userManager.update(from);
					
				}else{
					mailService.signUpValidation(UserVo.fromUserMin(model));
				}
				
				
				if(model!=null){
					signUpResult = true;
					session.put(AppConstant.SESSION_LOGIN_USER_ID, model.getId());
				}
				setCookie(model);
				
				
			}
		} catch (Exception e) {
			signUpResult = false;
			returnMsg = "服务器出小差啦，请稍候再试~";
			addActionError(e.getMessage());
		}
		return AJAX_SIGNUP_RESULT;
	}
	
	@Action("check_auth_code")
	public String checkAuthCode() {
		String authCodeInSession = (String) session
				.get(AppConstant.SESSION_AUTH_CODE);
		if (authCode != null && 
				!authCode.equalsIgnoreCase(authCodeInSession)) {
			checkResult = true;
		} else {
			checkResult = false;
		}
		return AJAX_CHECK_RESULT;
	}
	
	@Action("check_user_mail")
	public String checkUserExist(){
		checkResult = userManager.exist(model.getFirstEmail(),false) == null ? false : true;
		return AJAX_CHECK_RESULT;
	}
	
	
	@Override
	protected void prepareModel() throws Exception {
		model = new User();
		type = 2;
	}
	
	@Override
	public User getModel() {
		return model;
	}
	public void setModel(User user) { this.model = user; }

	public UserManager getUserManager() { return userManager;}
	public void setUserManager(UserManager userManager) { this.userManager = userManager;}

	public boolean isSignUpResult() { return signUpResult; }
	public void setSignUpResult(boolean signUpResult) { this.signUpResult = signUpResult;}

	public String getAuthCode() {return authCode;}
	public void setAuthCode(String authCode) {this.authCode = authCode;}

	public boolean isCheckResult() { return checkResult; }
	public void setCheckResult(boolean checkResult) { this.checkResult = checkResult; }
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setInviteFromId(Long inviteFromId) {
		this.inviteFromId = inviteFromId;
	}

	public Long getInviteFromId() {
		return inviteFromId;
	}

}
