package com.landhightech.json.test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landhightech.bean.NotifyReturnData;

public class JsonTest {
	@Test
	public void test(){
		String jsonRequest ="{\"notify_type\":\"0\",\"pay_type\":\"count\",\"pay_data\":\"123456\",\"pay_time\":\"11122233344\",\"ip\":\"192.168.1.1\"}";
//		String jsonRequest ="{\"notify_type\":\"0\",\"pay_type\":\"count\",\"pay_data\":\"123456\",\"pay_time\":11122234}";
		JSONObject json = JSON.parseObject(jsonRequest);
		System.out.println(json.toJSONString());
		NotifyReturnData pojo = JSON.parseObject(jsonRequest, NotifyReturnData.class);
		System.out.println(pojo.getPay_time());
//		System.out.println(pojo.getNotify_type());
	}
}
