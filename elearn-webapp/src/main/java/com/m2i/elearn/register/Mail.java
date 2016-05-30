package com.m2i.elearn.register;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

	private String from;
	private String to;
	private String subjectMail;
	private String contentMail;
	
	public Mail(String to, String subjectMail, String contentMail) {
		this.to = to;
		this.subjectMail = subjectMail;
		this.contentMail = contentMail;
	}
	
	/**
	 * Send a mail to the email address specified when we instantiate a new Mail object
	 * @throws AuthenticationFailedException
	 * @throws MessagingException
	 * @result mail sent
	 */
	public void sendMail() throws AuthenticationFailedException, MessagingException  {
		
		from = "elearningtest02@outlook.fr";
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.host", "smtp-mail.outlook.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    
	    Session session = Session.getInstance(props, null);
	    MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress = {new InternetAddress(to)};
	    message.addRecipients(Message.RecipientType.TO, toAddress);
	    message.setSubject(subjectMail);  
	    message.setText(contentMail);

	    try {
	    	Transport transport = session.getTransport("smtp");
	    	transport.connect("smtp-mail.outlook.com", 587, "elearningtest02@outlook.fr", "testeLearn");
	    	transport.sendMessage(message, message.getAllRecipients());
	    	transport.close();
	    } catch (MessagingException mex) {
			 mex.printStackTrace();
	    } 
	}
}
