package com.landhightech.quartz.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.constant.PropsKey;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.thread.mq.AbstractRecordDbExec;
import com.landhightech.util.PropertiesUtil;

public class NotifyRecordDbRetryJob extends AbstractRecordDbExec{
	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void work() {
		IRedisQueueService mqMessageService = RedisQueueServiceImpl.getInstance();
		String failQueue = PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_NOTIFYRECORDRADD_QUEUE);
		long size = mqMessageService.getMessageSize(failQueue);
		logger.info("NotifyRecordDbRetryJob start;fail size:{}",size);
		long sucNum = 0;
		if(size>0){
			for(int i=0;i<size;i++){
				String message = mqMessageService.getErrorMessage(failQueue);
				if(message == null)
					break;
				boolean isSucess = doAddRecord(message,true);
				if(isSucess){
					sucNum++;
					logger.info("add success;message:{}",message);
				}else{
					logger.info("add fail;message:{}",message);
				}
					
			}
			logger.info("job end;execute size:{}",sucNum);
		}
	}
}
