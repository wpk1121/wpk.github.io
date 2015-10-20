package com.landhightech.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: PropertiesInMemory 
 * @Description: 加载配置文件
 * @author wangpk
 * @date 2015-10-12 下午3:48:38 
 *
 */
public class PropertiesInMemory {
	
	private final Logger logger = LoggerFactory.getLogger(PropertiesInMemory.class);
	/**
	 * 
	 * @Title: inMemory 
	 * @Description: 加载配置文件保存到map中.
	 * @param path
	 * @param map
	 */
	public void inMemory(String path,Map<String,String> map) {
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream(path);
		Properties props = new Properties();
		try {
			props.load(input);
			Enumeration<?> en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				if(map.containsKey(key)){
					logger.error("find Repeat key in the start method!");
				}
				map.put(key, Property);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("input close err.",e.getMessage());
				}
			}
		}
	}
}
