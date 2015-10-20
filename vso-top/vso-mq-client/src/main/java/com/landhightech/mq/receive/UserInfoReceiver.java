package com.landhightech.mq.receive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.landhightech.thread.UserInfoCallable;
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
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //接收用户信息
    public void onMessage(Message message){  
        logger.info("receive:{}", new String(message.getBody())); 
    	CommonThreadPool.execute(new UserInfoCallable(new String(message.getBody())));
    }  
}
