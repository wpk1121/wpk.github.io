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
 * @ClassName SendFailNotifyRecordJob
 * @author leobert.lan
 * @date 2015年10月26日
 * @desc : a timed job class who's function will be called timely, to process the message
 * which failed while send 
 */
public class SendFailNotifyRecordJob {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @author: leobert.lan
	 * @time: 2015年10月26日 上午10:29:37
	 * @desc: 定时任务调用方法
	 * <li>读取redis对应配置</li>
	 * <li>统计当前总量</li>
	 * <li>顺序发送</li>
	 * <li>TODO 发送成功的信息应当移除？</li>
	 * @param null
	 * @return void
	 */
	public void work() {
		IRedisQueueService mqMessageService = RedisQueueServiceImpl.getInstance();
		String failQueue = PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_NOTIFYRECORD_QUEUE);
		long size = mqMessageService.getMessageSize(failQueue);
		logger.info("job start;failUser size:{}",size);
		long sucNum = 0;
		if(size>0){
			//config the queue
			String queue = InterfacePropUti.getValue(MqKey.QUEUE_NOTIFY_RECORD);
			for(int i=0;i<size;i++){
				//get the message by a String-type key
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
