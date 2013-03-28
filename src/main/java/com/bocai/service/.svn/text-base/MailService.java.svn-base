package com.bocai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocai.mail.ITemplateMailAgent;
import com.bocai.mail.TemplateMailContextFactory;
import com.bocai.mail.TemplateMailMessageFactory;
import com.bocai.vo.UserVo;

@Service
public class MailService {
	@Autowired
	private ITemplateMailAgent mailAgent;
	@Autowired
	private TemplateMailContextFactory mailContextFactory;
	@Autowired
	private TemplateMailMessageFactory mailMessageFactory;
	
	public void signUpValidation(UserVo user){
		mailAgent.sendMail( mailMessageFactory.getSignUpValidationMessage(user), 
							mailContextFactory.getSignUpValidationContext(user));
	}
	
	public void forgetPasswordValidation(UserVo user){
		mailAgent.sendMail( mailMessageFactory.getForgetPasswordMessage(user), 
							mailContextFactory.getForgetPasswordContext(user));
	}
	
	public void invite(UserVo user, String toEmail){
		mailAgent.sendMail( mailMessageFactory.getInvitationMessage(user, toEmail), 
							mailContextFactory.getInvitationContext(user, toEmail));
	}

	public ITemplateMailAgent getMailAgent() {
		return mailAgent;
	}

	public void setMailAgent(ITemplateMailAgent mailAgent) {
		this.mailAgent = mailAgent;
	}

	public TemplateMailContextFactory getMailContextFactory() {
		return mailContextFactory;
	}

	public void setMailContextFactory(TemplateMailContextFactory mailContextFactory) {
		this.mailContextFactory = mailContextFactory;
	}

	public TemplateMailMessageFactory getMailMessageFactory() {
		return mailMessageFactory;
	}

	public void setMailMessageFactory(TemplateMailMessageFactory mailMessageFactory) {
		this.mailMessageFactory = mailMessageFactory;
	}
}
