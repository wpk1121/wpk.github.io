package com.landhightech.mail.test;

import java.util.Date;

import org.junit.Test;

import com.landhightech.bean.Mail;
import com.landhightech.mail.IMailHandle;
import com.landhightech.mail.impl.SendMail;

public class MailTest {
	@Test
	public void test(){
		IMailHandle sendMail = SendMail.getInstance();
		Mail mail = new Mail();
		mail.setSubject("你好，明天");
		mail.setBody("你好，明天");
		mail.setFrom("wpk861121@163.com");
		mail.setTo("1749947690@qq.com");
		mail.setSendTime(new Date());
		mail.setSmtpServer("smtp.163.com");
		mail.setUser("wpk861121");
		mail.setPassword("qq694150471");
		System.out.println(sendMail.sendTextMail(mail));
	}
}	
