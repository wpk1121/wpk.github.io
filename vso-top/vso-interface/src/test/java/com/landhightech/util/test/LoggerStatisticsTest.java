package com.landhightech.util.test;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.landhightech.domain.Context;
import com.landhightech.mq.SendMessage;
import com.landhightech.quartz.mq.SendFailUserJob;
import com.landhightech.util.LoggerStatistics;

public class LoggerStatisticsTest {
	/**
	 * 
	 * @Title: testLog 
	 * @Description: 测试打印日志
	 */
	@Test
	public void testLog(){
		Context context = new Context();
		JSONObject obj = new JSONObject();
		obj.put("id", "1");
		obj.put("name", "xiaoming");
		obj.put("password", "123");
		obj.put("date", System.currentTimeMillis());
		context.setJsonRequest(obj.toString());
		context.setMethod("lottery");
		LoggerStatistics log = new LoggerStatistics(context);
		log.loginfo();
//		log.logger.error("111");
//		SendMessage.getInstance().logger.error("aaa");
//		new SendFailUserJob().work();
//		new SendFailUserJob().logger.error("bbb");
//		new RegisterUserExecute().logger.info("aaa1");
	}
}
