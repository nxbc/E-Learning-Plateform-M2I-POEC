package com.m2i.elearn.register;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import org.junit.Test;

import junit.framework.TestCase;

public class MailTest extends TestCase {

	/**
	 * Send mail to Gmail account.
	 * @throws AuthenticationFailedException
	 * @throws MessagingException
	 * @return void (mail sent to the specified email address)
	 */
	@Test
    public void testSendingGmail() throws AuthenticationFailedException, MessagingException {
		Mail mail = new Mail("elearningtest02@gmail.com", "test sujet du mail", "test pour le corps du mail");
		mail.sendMail();
    }

	
	/**
	 * Send mail to itself (and to an Outlook account)
	 * @throws AuthenticationFailedException
	 * @throws MessagingException
	 * @return void (mail sent to the specified email address)
	 */
	@Test
    public void testSendingOutlook() throws AuthenticationFailedException, MessagingException {
		Mail mail = new Mail("elearningtest02@outlook.fr", "test sujet du mail", "test pour le corps du mail");
		mail.sendMail();
    }
}