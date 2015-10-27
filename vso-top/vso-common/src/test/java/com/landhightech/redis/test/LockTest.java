package com.landhightech.redis.test;

import org.junit.Test;

import com.landhightech.constant.PropsKey;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.util.PropertiesUtil;

public class LockTest {
	@Test
	public void test() throws Exception{
//		for(int i=0;i<100;i++){
//			RedisLock.getInstance().lock(String.valueOf(i));
//			System.out.println(i);
//			RedisLock.getInstance().unLock(String.valueOf(i));
//		}
		RedisQueueServiceImpl r = (RedisQueueServiceImpl) RedisQueueServiceImpl.getInstance();
//		r.putErrorMessage("a", "2");
		String failQueue = PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USERADD_QUEUE);
		long size = r.getMessageSize(failQueue);
		System.out.println(size);
		System.out.println(r.getErrorMessage(failQueue));
//		while(r.getErrorMessage("a")!=null){
//			
//		}
		
	}
}
