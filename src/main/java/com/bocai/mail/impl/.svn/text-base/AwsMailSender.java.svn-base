package com.bocai.mail.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.bocai.util.AWSJavaMailSample;

public class AwsMailSender extends JavaMailSenderImpl implements InitializingBean{
	
	private static final String HEADER_MESSAGE_ID = "Message-ID";
	private static final String FROM = "bocai.app@gmail.com";
	
	private Log log = LogFactory.getLog(AwsMailSender.class);
	private PropertiesCredentials credentials;
	private AmazonSimpleEmailService ses;
	
	protected Transport getTransport(Session session) throws NoSuchProviderException {
		return new AWSJavaMailTransport(session, null);
	}
	
	protected void doSend(MimeMessage[] mimeMessages, Object[] originalMessages) throws MailException {
		Map<Object, Exception> failedMessages = new LinkedHashMap<Object, Exception>();
		try {
			Transport transport = getTransport(getSession());
			transport.connect();
			try {
				for (int i = 0; i < mimeMessages.length; i++) {
					MimeMessage mimeMessage = mimeMessages[i];
					try {
						if (mimeMessage.getSentDate() == null) {
							mimeMessage.setSentDate(new Date());
						}
						String messageId = mimeMessage.getMessageID();
						mimeMessage.saveChanges();
						if (messageId != null) {
							// Preserve explicitly specified message id...
							mimeMessage.setHeader(HEADER_MESSAGE_ID, messageId);
						}
						transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
					}
					catch (MessagingException ex) {
						Object original = (originalMessages != null ? originalMessages[i] : mimeMessage);
						failedMessages.put(original, ex);
					}
				}
			}
			finally {
				transport.close();
			}
		}
		catch (AuthenticationFailedException ex) {
			ex.printStackTrace();
			//throw new MailAuthenticationException(ex);
		}
		catch (MessagingException ex1) {
			ex1.printStackTrace();
			//throw new MailSendException("Mail server connection failed", ex);
		}
		if (!failedMessages.isEmpty()) {
			//ex.printStackTrace();
			//throw new MailSendException(failedMessages);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		credentials= new PropertiesCredentials(
                AWSJavaMailSample.class
                        .getResourceAsStream("/AwsCredentials.properties"));
		ses = new AmazonSimpleEmailServiceClient(credentials);
		//verifyEmailAddress(ses, FROM);
		setJavaMailProperties(new Properties());
		getJavaMailProperties().setProperty("mail.transport.protocol", "aws");
		getJavaMailProperties().setProperty("mail.aws.user", credentials.getAWSAccessKeyId());
		getJavaMailProperties().setProperty("mail.aws.password", credentials.getAWSSecretKey());
	}
	
	private void verifyEmailAddress(AmazonSimpleEmailService ses, String address) {
		try {
			ListVerifiedEmailAddressesResult verifiedEmails = ses
					.listVerifiedEmailAddresses();
			if (!verifiedEmails.getVerifiedEmailAddresses().contains(address)) {
				throw new RuntimeException("Please check the email address "
						+ address + " to verify it");
			} else {
				ses.verifyEmailAddress(new VerifyEmailAddressRequest()
						.withEmailAddress(address));
			}
		} catch (Exception e) {
			log.error("verify error, please check you email address");
			throw new RuntimeException(e.getMessage());
		}
    }
}
