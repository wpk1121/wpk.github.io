package com.landhightech.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.landhightech.constant.InterfasePropsKey;
import com.landhightech.threadpool.SendMqThreadPool;
import com.landhightech.util.PropertiesInMemory;

/**
 * 
 * @ClassName: Start 
 * @Description: Application Lifecycle Listener implementation class Start
 * @author wangpk
 * @date 2015-10-12 下午3:36:44 
 *
 */
@WebListener
public class Start implements ServletContextListener,InterfasePropsKey {

	private final Logger logger = LoggerFactory.getLogger(Start.class);
	/**
	 *  @see ServletContextListener#contextInitialized
	 */
	@SuppressWarnings("resource")
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("start load Properties...");
		//加载execute配置文件.
//		PropertiesInMemory proper = new PropertiesInMemory();
//		proper.inMemory("executeMapper.properties", executes);
//		proper.inMemory("threadpool.properties", props);
//		proper.inMemory("rabbitmq.properties", props);
		//初始化线城池
		try {
			SendMqThreadPool.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
		
		/*//定时器检查mq连接是否使用
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new CheckChannelJob(),
				Integer.parseInt(props.get(MqKey.CHECK_REGISTER_USEr_START)), 
				Integer.parseInt(props.get(MqKey.CHECK_REGISTER_USER_TIME)), 
				TimeUnit.SECONDS);*/
		logger.info("load Properties end.");
	}


	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("shutdown...");
		logger.info("shutdown Success.");
	}

}
