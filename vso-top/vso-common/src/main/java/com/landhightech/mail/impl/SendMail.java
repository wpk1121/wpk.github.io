package com.landhightech.mail.impl;

import com.landhightech.bean.Mail;
import com.landhightech.mail.IMailHandle;

public class SendMail extends AbstractMailHandle{
	private static IMailHandle handle = new SendMail();

	private SendMail() {
		super();
	}

	public static IMailHandle getInstance() {
		if (handle == null) {
			handle = new SendMail();
		}
		return handle;
	}
	@Override
	public boolean sendTextMail(Mail mail) {
		String smtpServer = null;
		String user = null;
		String password = null;
		if(mail.getSmtpServer()!=null)
			smtpServer = mail.getSmtpServer();
		if(mail.getUser()!=null)
			user = mail.getUser();
		if(mail.getPassword()!=null)
			password = mail.getPassword();
		return send(smtpServer, user, password, mail);
	}

}
