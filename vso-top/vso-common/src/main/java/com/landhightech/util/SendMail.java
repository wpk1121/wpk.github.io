package com.landhightech.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	/**
	* @param args
	* @throws Exception 
	*/
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");//发送邮件协议
		properties.setProperty("mail.smtp.auth", "true");//需要验证
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
		Session session = Session.getInstance(properties);
		//true开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(false);
		//2、通过session得到transport对象
		Transport ts = session.getTransport();
		//3、连上邮件服务器
		ts.connect("smtp.163.com", "wpk861121", "qq694150471");
		//4、创建邮件
		Message message = createMixedMail(session);
		message.writeTo(new FileOutputStream("E:/1.eml"));
		//5、发送邮件
//		ts.sendMessage(message, message.getAllRecipients());
		Message message1 = new MimeMessage(session, new FileInputStream("E:/1.eml"));
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		}
	  
	/**
	* @Method: createMixedMail
	* @Description: 生成一封带附件和带图片的邮件
	* @param session
	* @return
	* @throws Exception
	*/ 
	public static MimeMessage createMixedMail(Session session) throws Exception {
		//创建邮件
		MimeMessage message = new MimeMessage(session);
		//设置邮件的基本信息
		message.setFrom(new InternetAddress("wpk861121@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1749947690@qq.com"));
		message.setSubject("发送给x的邮件");
		//正文
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("xxx这是女的xxxx<br/><img src='cid:aaa.jpg'>","text/html;charset=UTF-8");
		//图片
		MimeBodyPart image = new MimeBodyPart();
		image.setDataHandler(new DataHandler(new FileDataSource("D:\\1.png")));
		image.setContentID("aaa.jpg");
		//附件1
//		MimeBodyPart attach = new MimeBodyPart();
//		DataHandler dh = new DataHandler(new FileDataSource("D:\\2.zip"));
//		attach.setDataHandler(dh);
//		attach.setFileName(dh.getName());
		//描述关系:正文和图片
		MimeMultipart mp1 = new MimeMultipart();
		mp1.addBodyPart(text);
		mp1.addBodyPart(image);
		mp1.setSubType("related");
		//描述关系:正文和附件
		MimeMultipart mp2 = new MimeMultipart();
//		mp2.addBodyPart(attach);
		//代表正文的bodypart
		MimeBodyPart content = new MimeBodyPart();
		content.setContent(mp1);
		mp2.addBodyPart(content);
		mp2.setSubType("mixed");
		message.setContent(mp2);
		message.saveChanges();
		
		message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
		//返回创建好的的邮件
		return message;
	}
 }
