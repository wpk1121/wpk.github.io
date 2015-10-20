package com.landhightech.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName: PropertiesUtil 
 * @Description: 加载配置文件
 * @author wangpk
 * @date 2015-10-16 下午5:33:51 
 *
 */
public class PropertiesUtil {
	
	private static Map<String,String> props;
	static{
		if(props == null){
			props = new HashMap<String,String>();
			PropertiesInMemory proper = new PropertiesInMemory();
			proper.inMemory("mongodb.properties", props);
			proper.inMemory("redis.properties", props);
		}
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
	
	
}
