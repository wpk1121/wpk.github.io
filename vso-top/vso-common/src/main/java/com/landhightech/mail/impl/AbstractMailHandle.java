package com.landhightech.mail.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.bean.Mail;
import com.landhightech.mail.IMailHandle;

/**
 * 
 * @ClassName: AbstractMailHandle 
 * @Description: 處理郵件抽象類
 * @author wangpk
 * @date 2015-10-23 下午2:58:50 
 *
 */
public abstract class AbstractMailHandle implements IMailHandle {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @Title: createTextMail 
	 * @Description: 創建普通文本郵件.
	 * @param mail
	 * @return
	 */
	protected Message createTextMail(Mail mail,Session session){
		MimeMessage message = null;
		if(mail == null)
			return message;
		message = new MimeMessage(session);
		//指明邮件的发件人
		try {
			message.setFrom(new InternetAddress(mail.getFrom()));
			//指明邮件的收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
			//邮件的标题
			message.setSubject(mail.getSubject());
			//邮件的文本内容
			message.setContent(mail.getBody(), "text/html;charset=UTF-8");
			
			message.setSentDate(mail.getSendTime());
		} catch (AddressException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//返回创建好的邮件对象
		return message;
	}
	/**
	 * 
	 * @Title: send 
	 * @Description: 發送郵件
	 * @param smtpServer
	 * @param user
	 * @param password
	 * @param mail
	 * @return
	 */
	protected boolean send(String smtpServer,String user,String password,Mail mail){
		boolean bool = false;
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");//发送邮件协议
		properties.setProperty("mail.smtp.auth", "true");//需要验证
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
		Session session = Session.getInstance(properties);
		//true开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(false);
		//2、通过session得到transport对象
		Transport ts = null;
		try {
			ts = session.getTransport();
			//3、连上邮件服务器
			ts.connect(smtpServer, user, password);
			//4、创建邮件
			Message message = createTextMail(mail,session);
			//5、发送邮件
			ts.sendMessage(message, message.getAllRecipients());
			bool = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{
			if(ts != null)
			try {
				ts.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return bool;
	}
}
