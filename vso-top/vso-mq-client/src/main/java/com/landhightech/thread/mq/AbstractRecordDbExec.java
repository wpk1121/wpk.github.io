package com.landhightech.thread.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.landhightech.bean.NotifyReturnData;
import com.landhightech.constant.PropsKey;
import com.landhightech.service.INotifyRecordService;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.impl.NotifyRecordServiceImpl;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.util.PropertiesUtil;

public class AbstractRecordDbExec {

	public static final Logger logger = LoggerFactory
			.getLogger(AbstractRecordDbExec.class);

	/**
	 * @author: leobert.lan
	 * @time: 2015年10月26日 下午3:36:38
	 * @desc: 
	 * @param <li>message String</li>
	 * 				<br>the String typed message body </br>
	 * <li>isTimer boolean</li>
	 * 				<br>to distinguish between timer-job or origin-call</br>
	 * 				true represents timer-job
	 * @return boolean
	 */
	public boolean doAddRecord(String message, boolean isTimer) {
		NotifyReturnData data = null;
		boolean isSuccess = false;
		try {
			if (message != null)
				data = JSON.parseObject(message, NotifyReturnData.class);
			if (data != null) {
				INotifyRecordService service = NotifyRecordServiceImpl.getInstance();
				isSuccess = service.insertRecord(data);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		if (isSuccess) {
			logger.info("notify record insert sucess;message:{}", message);
		} else {
			if (!isTimer)
				logger.info("notify record insert fail;message:{}", message);
			IRedisQueueService queueService = RedisQueueServiceImpl
					.getInstance();
			queueService.putErrorMessage(PropertiesUtil
					.getValue(PropsKey.RedisKey.REDIS_FAIL_NOTIFYRECORDRADD_QUEUE),
					message);

		}
		return isSuccess;
	}

}
