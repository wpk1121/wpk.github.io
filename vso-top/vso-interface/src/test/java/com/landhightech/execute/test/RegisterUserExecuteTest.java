package com.landhightech.execute.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.landhightech.domain.Context;
import com.landhightech.execute.BaseInteExecute;
import com.landhightech.execute.user.RegisterUserExecute;
import com.landhightech.servlet.Start;
import com.landhightech.threadpool.SendMqThreadPool;
import com.landhightech.util.PropertiesInMemory;

public class RegisterUserExecuteTest {
	@Test
	/**
	 * 
	 * @Title: test 
	 * @Description: TODO
	 */
	public void test(){
		//加载execute配置文件.
//				PropertiesInMemory proper = new PropertiesInMemory();
//				proper.inMemory("executeMapper.properties", Start.executes);
//				proper.inMemory("threadpool.properties",  Start.props);
//				proper.inMemory("rabbitmq.properties",  Start.props);
//				try {
//					SendMqThreadPool.init();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
		/*JSONObject json = new JSONObject();
		json.put("os", "android");
//		JSONObject user = new JSONObject();
		json.put("name", "xiaogang");
		json.put("password", "123456");
//		json.put("user", user);
		Context context = new Context();
		context.setMethod("registerUser");
		context.setJsonRequest(json.toJSONString());
		BaseInteExecute execute = new RegisterUserExecute();
		String str = execute.doProcess(context);
		System.out.println(str);*/
	}
}
