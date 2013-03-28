package com.bocai.mail.impl;

import java.io.StringWriter;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.bocai.mail.ITemplateMailAgent;
import com.bocai.mail.TemplateMailContextFactory;

public class VelocityTemplateMailAgent implements ITemplateMailAgent {
	
	public final static String DEFAULT_SENDER = "auto@bocai007.com";
	public final static String DEFAULT_ENCODING = "UTF-8";
	
	private JavaMailSender mailSender;
	private VelocityMerger velocityMerger;
//	private VelocityEngine velocityEngine;

	public void sendMail(SimpleMailMessage message,
			Map<String, Object> context) {
		if(message == null || context == null) return;
		final String subject = new StringBuilder()
				.append(message.getSubject())
				.toString();
		final String from;
		if(message.getFrom() == null || StringUtils.isEmpty(message.getFrom()))
			from = DEFAULT_SENDER;
		else
			from = message.getFrom();
		final String[] to = message.getTo();
		final String[] cc = message.getCc();
		final String[] bcc = message.getBcc();
		final String templateKey = message.getText();
		StringWriter writer = new StringWriter();
//		VelocityEngineUtils.mergeTemplate(velocityEngine, templateKey, 
//				getMailEncoding(), context, writer);
		velocityMerger.mergeToWriter(templateKey, getMailEncoding(), context, writer);
		final String body = writer.toString();
		
		getMailSender().send(new MimeMessagePreparator(){

			public void prepare(MimeMessage message) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(message, getMailEncoding());
				helper.setFrom(from);
				helper.setTo(to);
				if(cc!=null && cc.length > 0){
					helper.setCc(cc);
				}
				if(bcc!=null && bcc.length > 0){
					helper.setBcc(bcc);
				}
				helper.setSubject(subject);
				helper.setText(body, true);
			}
			
		});
	}
	
	private String getMailEncoding(){
		return DEFAULT_ENCODING;
	}
	
	public JavaMailSender getMailSender(){
		return this.mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityMerger(VelocityMerger velocityMerger) {
		this.velocityMerger = velocityMerger;
	}
	
//	public void setVelocityEngine(VelocityEngine velocityEngine) {
//		this.velocityEngine = velocityEngine;
//	}
}
