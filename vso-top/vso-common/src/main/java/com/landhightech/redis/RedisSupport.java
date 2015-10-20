package com.landhightech.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 
 * @ClassName: RedisSupport 
 * @Description: 
 * @author wangpk
 * @date 2015-10-16 下午4:43:54 
 *
 */
public class RedisSupport extends AbstractRedisSupport{
	private static RedisSupport service = new RedisSupport();

	public static RedisSupport getInstance() {
		if (service == null) {
			service = new RedisSupport();
		}
		return service;
	}
	
	/**
	 * 
	 * @Title: rpush 
	 * @Description: 向队列中添加一条数据
	 * @param queue
	 * @param value
	 * @return
	 */
	public boolean rpush(String queue,String value){
		boolean isSucess = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.rpush(queue, value);
			isSucess = true;
		} catch (JedisConnectionException je) {  
			logger.error("redis connect error.",je.getMessage());
			returnBrokenResource(jedis);  
		} catch (Exception e) {  
			logger.error(e.getMessage());
		} finally {  
			returnResource(jedis);  
		}  
		return isSucess;
	} 
	/**
	 * 
	 * @Title: lpop 
	 * @Description: 从队列中获取一条数据
	 * @param queue
	 * @return
	 */
	public String lpop(String queue){
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getJedis();
			value = jedis.lpop(queue);
		} catch (JedisConnectionException je) {  
			logger.error("redis connect error.",je.getMessage());
			returnBrokenResource(jedis);  
		} catch (Exception e) {  
			logger.error(e.getMessage());
		} finally {  
			returnResource(jedis);  
		}  
		return value;
	} 
	/**
	 * 
	 * @Title: llen 
	 * @Description: 获取队列大小
	 * @param queue
	 * @return
	 */
	public long llen(String queue){
		Jedis jedis = null;
		long length = 0l;
		try {
			jedis = getJedis();
			length = jedis.llen(queue);
		} catch (JedisConnectionException je) {  
			logger.error("redis connect error.",je.getMessage());
			returnBrokenResource(jedis);  
		} catch (Exception e) {  
			logger.error(e.getMessage());
		} finally {  
			returnResource(jedis);  
		}  
		return length;
	}
}
