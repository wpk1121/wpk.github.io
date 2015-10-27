package com.landhightech.mq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.domain.StateChannel;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
/**
 * 
 * @ClassName: SendMessage 
 * @Description: 发送mq消息类
 * @author wangpk
 * @date 2015-10-13 下午3:48:56 
 *
 */
public class SendMessage {
	public final Logger logger = LoggerFactory.getLogger(SendMessage.class);
	private static SendMessage sendMessage;

	private SendMessage() {
		super();
	}

	public static SendMessage getInstance() {
		if (sendMessage == null) {
			sendMessage = new SendMessage();
		}
		return sendMessage;
	}
	/**
	 * 
	 * @Title: send 
	 * @Description: 发送mq消息
	 * @param message 发送的消息
	 * @param queue mq.queue
	 * @param failQueue 发送失败保存到redis的queue名称
	 * @param isTimer 区分是否定时任务调用
	 * @return  
	 */
	public boolean send(String message,String queue,String failQueue,boolean isTimer){
		boolean isSuccess = false;
        try {
			StateChannel stateChannel =  ChannelFactory.getInstance().getChannel();
			Channel channel = null;
			if(stateChannel!=null)
				channel = stateChannel.getChannel();
			if(channel!=null){
				stateChannel.setBusy(true);
				channel.queueDeclare(queue, true, false, false, null);
				channel.basicPublish("", queue, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("utf-8"));
				stateChannel.setBusy(false);
				isSuccess = true;
				logger.info("send success;queue:{};message:{}",queue,message);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        if(!isSuccess){
        	if(!isTimer)
        		logger.info("send fail;queue:{};message:{}",queue,message);
        	IRedisQueueService mqMessageService = RedisQueueServiceImpl.getInstance();
        	mqMessageService.putErrorMessage(failQueue, message);
        }
        return isSuccess;
	}
}
