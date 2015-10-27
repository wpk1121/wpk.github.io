package com.landhightech.thread.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.mq.SendMessage;
/**
 * 
 * @ClassName: UserInfoCallable 
 * @Description: 处理用户注册
 * @author wangpk
 * @date 2015-10-13 下午2:51:05 
 *
 */
public class SendMqCallable implements Runnable{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String message;
	private String queue;
	private String failSave;
	public SendMqCallable(String message,String queue,String failSave) {
		this.message = message;
		this.queue = queue;
		this.failSave = failSave;
	}

	public void run() {
		try {
			SendMessage.getInstance().send(message,queue,failSave,false);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
