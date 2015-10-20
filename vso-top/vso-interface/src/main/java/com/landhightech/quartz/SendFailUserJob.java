package com.landhightech.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.constant.PropsKey;
import com.landhightech.constant.InterfasePropsKey.MqKey;
import com.landhightech.rabbimq.SendMessage;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.servlet.Start;
import com.landhightech.util.PropertiesUtil;
/**
 * 
 * @ClassName: SendFailUserJob 
 * @Description: 定时检查发送用户消息错误并重试
 * @author wangpk
 * @date 2015-10-19 上午10:23:07 
 *
 */
public class SendFailUserJob {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @Title: work 
	 * @Description: 发送用户消息错误重试
	 */
	public void work() {
		logger.info("--------SendFailUserJob start-------");
		IRedisQueueService mqMessageService = RedisQueueServiceImpl.getInstance();
		String failQueue = PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USER_QUEUE);
		long size = mqMessageService.getMessageSize(failQueue);
		logger.info("SendFailUserJob failUser size:{}",size);
		long sucNum = 0;
		if(size>0){
			String queue = Start.props.get(MqKey.QUEUE_REGISTER_USER);
			for(int i=0;i<size;i++){
				String message = mqMessageService.getErrorMessage(failQueue);
				if(message == null)
					break;
				try {
					boolean isSuccess = SendMessage.getInstance().send(message,queue,failQueue,true);
					//打印发送成功的消息
					if(isSuccess){
						logger.info("SendFailUserJob send message:{}",message);
						sucNum++;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			logger.info("SendFailUserJob execute size:{}",sucNum);
		}
	}
}
