package com.landhightech.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterfacePropUti {
	private static final Logger logger = LoggerFactory.getLogger(InterfacePropUti.class);
	//保存execute名称与path对应关系
	public static final Map<String, String> executes = new HashMap<String, String>();
	//保存配置文件key-valuse
	public static Map<String, String> props = new HashMap<String, String>();
	static{
		PropertiesInMemory proper = new PropertiesInMemory();
		proper.inMemory("executeMapper.properties", executes);
		proper.inMemory("threadpool.properties", props);
		proper.inMemory("rabbitmq.properties", props);
		logger.info("Executes Size:{}" , executes.size());
	}
	/**
	 * 
	 * @Title: getValue 
	 * @Description: 获取配置文件value.
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return props.get(key);
	}
	
	public static String getExcute(String key){
		return executes.get(key);
	}
	
}
