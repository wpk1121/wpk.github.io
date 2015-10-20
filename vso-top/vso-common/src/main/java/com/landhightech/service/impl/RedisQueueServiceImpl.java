package com.landhightech.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.redis.RedisSupport;
import com.landhightech.service.IRedisQueueService;

public class RedisQueueServiceImpl implements IRedisQueueService{
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static IRedisQueueService service = new RedisQueueServiceImpl();

	private RedisQueueServiceImpl() {
		super();
	}

	public static IRedisQueueService getInstance() {
		if (service == null) {
			service = new RedisQueueServiceImpl();
		}
		return service;
	}
	
	public boolean putErrorMessage(String queue, String value) {
		return RedisSupport.getInstance().rpush(queue, value);
	}

	public String getErrorMessage(String queue) {
		return RedisSupport.getInstance().lpop(queue);
	}

	public long getMessageSize(String queue) {
		return RedisSupport.getInstance().llen(queue);
	}
}
