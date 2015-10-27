package com.landhightech.thread.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.landhightech.bean.User;
import com.landhightech.constant.PropsKey;
import com.landhightech.redis.lock.RedisLock;
import com.landhightech.service.IRedisQueueService;
import com.landhightech.service.IUserService;
import com.landhightech.service.impl.RedisQueueServiceImpl;
import com.landhightech.service.impl.UserServiceImpl;
import com.landhightech.util.PropertiesUtil;
/**
 * 
 * @ClassName: AbstractUserDbJob 
 * @Description: TODO
 * @author wangpk
 * @date 2015-10-19 下午3:27:34 
 *
 */
public abstract class AbstractUserDbExec {
	public static final Logger logger = LoggerFactory.getLogger(AbstractUserDbExec.class);
	public boolean doAddUser(String message,boolean isTimer){
		User user = null;
		Long id = null;
		boolean isSuccess = false;
		try {
			if(message != null)
				user = JSON.parseObject(message,User.class);
			if(user != null){
				IUserService userService = UserServiceImpl.getInstance();
				RedisLock lock = RedisLock.getInstance();
				String lockKey = user.getName();
				//如果因为redis连接异常 忽略分布式锁,直接插入数据库
				/*try {
					//如果锁正在被使用,线程等待一段时间.
					while(!lock.lock(lockKey)){
						Thread.sleep(50);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}*/
				
				id = userService.addUser(user);
				/*try {
					lock.unLock(lockKey);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}*/
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		if(id!=null){
			logger.info("user add success id:{}",id);
			isSuccess = true;
		}
		if(id == null){
			if(!isTimer)
				logger.info("user register fail;message:{}",message);
			IRedisQueueService queueService = RedisQueueServiceImpl.getInstance();
			queueService.putErrorMessage(PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USERADD_QUEUE), message);
			
		}
		return isSuccess;
	}
}
