package com.landhightech.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.landhightech.constant.PropsKey;
import com.landhightech.util.PropertiesUtil;
/**
 * 
 * @ClassName: AbstractRedisSupport 
 * @Description: 初始化JedisPool.
 * @author wangpk
 * @date 2015-10-16 下午4:06:29 
 *
 */
public abstract class AbstractRedisSupport implements PropsKey{
	private static JedisPool jedisPool;
	public static final Logger logger = LoggerFactory.getLogger(AbstractRedisSupport.class);
	/**
	 * 加载redis配置文件初始化jedisPool
	 */
	 static
	    {
	        try {
				if (jedisPool == null){
				    JedisPoolConfig config = new JedisPoolConfig();
				    config.setMaxActive(Integer.parseInt(PropertiesUtil.getValue(RedisKey.REDIS_MAXACTIVE)));
				    config.setMaxIdle(Integer.parseInt(PropertiesUtil.getValue(RedisKey.REDIS_MAXIDLE)));
				    config.setMaxWait(Long.parseLong(PropertiesUtil.getValue(RedisKey.REDIS_MAXWAIT)));
				    config.setTestOnBorrow(Boolean.parseBoolean(PropertiesUtil.getValue(RedisKey.REDIS_TESTONBORROW)));
				    config.setTestOnReturn(Boolean.parseBoolean(PropertiesUtil.getValue(RedisKey.REDIS_TESTONRETURN)));
				    jedisPool = new JedisPool(config, PropertiesUtil.getValue(RedisKey.REDIS_IP), 
				    		Integer.parseInt(PropertiesUtil.getValue(RedisKey.REDIS_PORT)));
				}
			} catch (Exception e) {
				logger.error("init jedisPool fail.",e);
			}
	    }
	/**
	 * 
	 * @Title: getPool 
	 * @Description: 获取JedisPool.
	 * @return
	 */
	public JedisPool getPool(){
		return jedisPool;
	}
	/**
	 * 
	 * @Title: getJedis 
	 * @Description: 获取jedis.
	 * @return
	 */
	public Jedis getJedis(){
		return jedisPool.getResource();
	}
	
	/**
	 * 
	 * @Title: returnBrokenResource 
	 * @Description: 释放redis对象
	 * @param jedis
	 */
	public void returnBrokenResource(Jedis jedis) {  
        if (jedis != null) 
        	try {  
        		//容错  
        		jedisPool.returnBrokenResource(jedis);  
        	} catch (Exception e) { 
        		logger.error(e.getMessage());
        	}  
    }  
      
    /**
     *  
     * @Title: returnResource 
     * @Description: 归还连接
     * @param jedis
     */
	public void returnResource(Jedis jedis) {  
    	if (jedis != null) 
    		try {  
    			jedisPool.returnResource(jedis);  
    		} catch (Exception e) {  
    			logger.error(e.getMessage());
    		}  
    }  
}
