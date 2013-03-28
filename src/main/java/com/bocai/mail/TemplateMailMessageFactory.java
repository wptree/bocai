package com.bocai.mail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bocai.vo.UserVo;

@Component("mailMessageFactory")
public class TemplateMailMessageFactory {
	
	private static final String KEY_SIGN_UP_SUCCESS = "sign-up-successful.vm";
	private static final String KEY_SIGN_UP_VALIDATION = "sign-up-validation.vm";
	private static final String KEY_FORGET_PASSWORD = "forget-password.vm";
	private static final String KEY_INVITATION = "invitation.vm";
	
	private static final String SUBJECT_SIGN_UP_SUCCESS = "波菜-恭喜您注册成功";
	private static final String SUBJECT_SIGN_UP_VALIDATION = "欢迎使用波菜";
	private static final String SUBJECT_FORGET_PASSWORD = "波菜-请及时进行密码重置";
	private static final String SUBJECT_INVITATION = "邀请你加入波菜网";
	private static final String FROM_ADDR_BOCAI = "bocai.app@gmail.com";
	
	public SimpleMailMessage getSignUpSuccessfulMessage(List<UserVo> recipients,
			Long alertTypeId) {
		if (recipients == null || recipients.size() == 0)
			return null;
		List<String> tos = new ArrayList<String>();
		for (UserVo recipient : recipients) {
			if (recipient.getFirstEmail() != null)
				tos.add(recipient.getFirstEmail());
		}
		if (tos.size() == 0)
			return null;

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(tos.toArray(new String[] {}));
		smm.setSubject(SUBJECT_SIGN_UP_SUCCESS);
		smm.setText(KEY_SIGN_UP_SUCCESS);
		smm.setFrom(FROM_ADDR_BOCAI);
		return smm;
	}
	
	public SimpleMailMessage getSignUpValidationMessage(UserVo recipient) {
		if (recipient == null)
			return null;
		List<String> tos = new ArrayList<String>();
		tos.add(recipient.getFirstEmail());

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(tos.toArray(new String[] {}));
		smm.setSubject(SUBJECT_SIGN_UP_VALIDATION);
		smm.setText(KEY_SIGN_UP_VALIDATION);
		smm.setFrom(FROM_ADDR_BOCAI);
		return smm;
	}
	
	public SimpleMailMessage getForgetPasswordMessage(UserVo recipient) {
		if (recipient == null)
			return null;
		List<String> tos = new ArrayList<String>();
		tos.add(recipient.getFirstEmail());
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(tos.toArray(new String[] {}));
		smm.setSubject(SUBJECT_FORGET_PASSWORD);
		smm.setText(KEY_FORGET_PASSWORD);
		smm.setFrom(FROM_ADDR_BOCAI);
		return smm;
	}
	
	public SimpleMailMessage getInvitationMessage(UserVo from, String recipient) {
		if (recipient == null)
			return null;
		List<String> tos = new ArrayList<String>();
		tos.add(recipient);
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(tos.toArray(new String[] {}));
		smm.setSubject(from.getName()+" "+SUBJECT_INVITATION);
		smm.setText(KEY_INVITATION);
		smm.setFrom(FROM_ADDR_BOCAI);
		return smm;
	}

}
