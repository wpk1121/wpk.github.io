package com.landhightech.mail;

import com.landhightech.bean.Mail;
/**
 * 
 * @ClassName: IMailHandle 
 * @Description: 郵件處理接口
 * @author wangpk
 * @date 2015-10-23 下午2:59:11 
 *
 */
public interface IMailHandle {
	/**
	 * 
	 * @Title: sendTextMail 
	 * @Description: 發送普通文本郵件
	 * @param message
	 * @return boolean true成功/false失敗
	 */
	public boolean sendTextMail(Mail mail);
	
	
}
