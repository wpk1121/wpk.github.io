package com.landhightech.receive;

//无用文件，可以在一个合适的时候把这个功能给去了


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class UserModifyReceiver implements MessageListener {

	// 测试统计用
	public int num;
	Lock lock = new ReentrantLock();
	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void addNum() {
		lock.lock();
		num++;
		lock.unlock();
	}

	// 接收用户信息
	public void onMessage(Message message) {

//		try {
//			logger.info("registerUser2;{}",
//					new String(new String(message.getBody(), "utf-8")));
//			addNum();
//			logger.info("receive:{}", num);
//			CommonThreadPool.execute(new UserInfoCallable(new String(message
//					.getBody(), "utf-8")));
//			System.out.println(new Date());
//		} catch (UnsupportedEncodingException e) {
//			logger.error(e.getMessage());
//		}
		
		logger.info("MR"+message.getBody());
	}

}
