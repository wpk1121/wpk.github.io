package com.landhightech.quartz.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.constant.PropsKey;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.util.PropertiesUtil;

public class UserDbJob extends AbstractUserDbJob{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void work() {
		logger.info("--------UserDbJob start-------");
		IRedisQueueService mqMessageService = RedisQueueServiceImpl.getInstance();
		String failQueue = PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USERADD_QUEUE);
		long size = mqMessageService.getMessageSize(failQueue);
		logger.info("UserDbJob failUser size:{}",size);
		long sucNum = 0;
		if(size>0){
			for(int i=0;i<size;i++){
				String message = mqMessageService.getErrorMessage(failQueue);
				boolean isSucess = doAddUser(message,true);
				if(isSucess)
					sucNum++;
			}
			logger.info("UserDbJob execute size:{}",sucNum);
		}
	}
}
