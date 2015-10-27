package com.landhightech.bean;

import java.util.Date;
/**
 * 
 * @ClassName: Mail 
 * @Description: 邮件.
 * @author wangpk
 * @date 2015-10-23 下午2:21:18 
 *
 */
public class Mail {
	//主题
	private String subject;
	//发送人
	private String from;
	//收件人
	private String to;
	//抄送
//	private String[] cc;
//	//密送
//	private String[] bcc;
	//发送时间
	private Date sendTime;
	//正文
	private String body;
	//附件路径
//	private String[] Attach;
	//邮件大小
//	private int size;
	//郵件發送服務器
	private String smtpServer;
	//用戶名
	private String user;
	//密碼
	private String password;
	
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
