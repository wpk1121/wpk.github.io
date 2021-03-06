package com.landhightech.receive;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.landhightech.thread.mq.impl.UserInfoCallable;
import com.landhightech.threadpool.CommonThreadPool;
/**
 * 
 * @ClassName: UserInfoReceiver 
 * @Description: 监听消息（用户信息接收）
 * @author wangpk
 * @date 2015-10-13 上午10:46:22 
 *
 */
public class UserInfoReceiver implements  MessageListener{
	//测试统计用
	public int num;
	Lock lock = new ReentrantLock();
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	public void addNum(){
		lock.lock();
		num++;
		lock.unlock();
	}
    //接收用户信息
    public void onMessage(Message message){  
       
    	try {
    		logger.info("registerUser2;{}", new String(new String(message.getBody(),"utf-8"))); 
    		addNum();
    		logger.info("receive:{}", num); 
			CommonThreadPool.execute(new UserInfoCallable(new String(message.getBody(),"utf-8")));
			System.out.println(new Date());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
    }  
}
