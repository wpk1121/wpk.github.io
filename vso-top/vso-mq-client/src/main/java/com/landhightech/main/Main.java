package com.landhightech.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.landhightech.threadpool.CommonThreadPool;
import com.landhightech.util.PropertiesInMemory;
/**
 * 
 * @ClassName: Main 
 * @Description: 处理mq消息服务入口类
 * @author wangpk
 * @date 2015-10-13 上午10:45:09 
 *
 */
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static final Map<String, String> poolProps = new HashMap<String, String>();
		
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//初始化配置
		PropertiesInMemory proper = new PropertiesInMemory();
		proper.inMemory("threadpool.properties", poolProps);
		//初始化线城池
		try {
			CommonThreadPool.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
		logger.info("client init ok.");
	}
}
