package com.bocai.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bocai.vo.UserVo;

public class MailServiceTester {
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext-beans.xml");
		MailService mailService = (MailService)ctx.getBean("mailService");
		UserVo user = new UserVo();
		user.setId(12L);
		mailService.signUpValidation(user);
	}
	
}
