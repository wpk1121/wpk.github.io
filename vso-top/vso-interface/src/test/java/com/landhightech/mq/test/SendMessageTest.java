package com.landhightech.mq.test;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.landhightech.constant.InterfasePropsKey.MqKey;
import com.landhightech.constant.PropsKey;
import com.landhightech.thread.mq.SendMqCallable;
import com.landhightech.threadpool.SendMqThreadPool;
import com.landhightech.util.InterfacePropUti;
import com.landhightech.util.PropertiesUtil;

public class SendMessageTest {
	@Test
	public void test(){
		JSONObject json = new JSONObject();
		json.put("name", "aaa");
		json.put("password","aaa");
		json.put("money", 1.0f);
		json.put("createTime", System.currentTimeMillis());
		String message = json.toJSONString();
		try {
			SendMqThreadPool.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i =0;i<30000;i++){
//			SendMessage.getInstance().send(message, "registerUser1", "registerUser1", false);
		SendMqThreadPool.execute(new SendMqCallable(message,
				InterfacePropUti.getValue(MqKey.QUEUE_REGISTER_USER),
				PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USER_QUEUE)));
		}
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
