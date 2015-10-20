package com.landhightech.redis.lock;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.landhightech.constant.PropsKey;
import com.landhightech.redis.RedisSupport;
import com.landhightech.util.PropertiesUtil;
/**
 * 
 * @ClassName: RedisLock 
 * @Description: 利用redis实现分布式锁.
 * @author wangpk
 * @date 2015-10-16 下午4:52:12 
 *
 */
public class RedisLock {
	private static final int DEFAULT_SINGLE_EXPIRE_TIME = Integer.parseInt(
			PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_LOCK_EXPIRE));  
	public final Logger logger = LoggerFactory.getLogger(RedisLock.class);
	private static RedisLock service = new RedisLock();

	public static RedisLock getInstance() {
		if (service == null) {
			service = new RedisLock();
		}
		return service;
	}
	/**
	 * 
	 * @Title: lock 
	 * @Description:  如果锁可用   立即返回true，  否则返回false
	 * @param lockKey
	 * @return
	 * @throws Exception 
	 */
	public boolean lock(String lockKey) throws Exception {  
		return lock(lockKey, 0, TimeUnit.NANOSECONDS);  
	}  
	/**
	 *   
	 * @Title: lock 
	 * @Description: 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false 
	 * @param lockKey
	 * @param timeout
	 * @param unit
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean lock(String lockKey, long timeout, TimeUnit unit) throws Exception {  
		Jedis jedis = null; 
		RedisSupport rs = RedisSupport.getInstance();
		try {  
			jedis = RedisSupport.getInstance().getJedis();
			long nano = System.nanoTime();  
			do {  
				Long i = jedis.setnx(lockKey, lockKey);  
				//若为1锁未被使用，加锁成功
				if (i == 1) {   
					jedis.expire(lockKey, DEFAULT_SINGLE_EXPIRE_TIME);  
					return Boolean.TRUE;  
				} else { // 存在锁  
					logger.debug("lock {} is used",lockKey);
				}  
				if (timeout == 0) {  
					break;  
				}  
//				Thread.sleep(300);  
			} while ((System.nanoTime() - nano) < unit.toNanos(timeout));  
			return Boolean.FALSE;  
		} catch (JedisConnectionException je) {  
			logger.error("redis lock error.",je.getMessage());
			rs.returnBrokenResource(jedis);
			throw new Exception(je);
		} catch (Exception e) { 
			logger.error("",e);
		} finally {  
			rs.returnResource(jedis);  
		}  
		return Boolean.FALSE;  
	} 
	    
	/**
	 * 
	 * @Title: unLock 
	 * @Description: 释放锁 
	 * @param lockKey
	 * @throws Exception 
	 */
	public void unLock(String lockKey) throws Exception {  
		RedisSupport rs = RedisSupport.getInstance();
		Jedis jedis = null;  
		try {  
            jedis = rs.getJedis();  
            jedis.del(lockKey);  
        } catch (JedisConnectionException je) {
        	logger.error("redis unlock error.",je.getMessage());
			rs.returnBrokenResource(jedis); 
			throw new Exception(je);
		} catch (Exception e) {  
			logger.error("",e);
        } finally {  
        	rs.returnResource(jedis);  
        }
	}
	    
}
