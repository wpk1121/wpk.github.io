package com.landhightech.quartz.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.constant.InterfasePropsKey.MqKey;
import com.landhightech.constant.PropsKey;
import com.landhightech.mq.SendMessage;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.util.InterfacePropUti;
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
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @Title: work 
	 * @Description: 发送用户消息错误重试
	 */
	public void work() {
		IRedisQueueService mqMessageService = RedisQueueServiceImpl.getInstance();
		String failQueue = PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USER_QUEUE);
		long size = mqMessageService.getMessageSize(failQueue);
		logger.info("job start;failUser size:{}",size);
		long sucNum = 0;
		if(size>0){
			String queue = InterfacePropUti.getValue(MqKey.QUEUE_REGISTER_USER);
			for(int i=0;i<size;i++){
				String message = mqMessageService.getErrorMessage(failQueue);
				if(message == null)
					break;
				boolean isSuccess = false;
				try {
					isSuccess = SendMessage.getInstance().send(message,queue,failQueue,true);
					//打印发送成功的消息
					if(isSuccess){
						logger.info("send success;queue:{};message:{}",queue,message);
						sucNum++;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				if(!isSuccess)
					logger.info("send fail;queue:{};message:{}",queue,message);
			}
			logger.info("job end;execute size:{}",sucNum);
		}
	}
}
