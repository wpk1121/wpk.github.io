package com.landhightech.receive;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.landhightech.thread.mq.impl.NotifyRecordCallable;
import com.landhightech.threadpool.CommonThreadPool;

public class NotifyRecordReceiver implements MessageListener {

	// 测试统计用
	public int num;
	Lock lock = new ReentrantLock();
	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void addNum() {
		lock.lock();
		num++;
		lock.unlock();
	}

	// 参数中使用@Header获取mesage
	public void onMessage(Message message) {
		try {
			logger.info("notifyRecord;{}", new String(new String(
					message.getBody(), "utf-8")));
			addNum();
			logger.info("receive:{}", num);
			CommonThreadPool.execute(new NotifyRecordCallable(new String(message
					.getBody(), "utf-8")));
			System.out.println(new Date());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
	}
}