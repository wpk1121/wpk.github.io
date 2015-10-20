package com.landhightech.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.quartz.user.AbstractUserDbJob;
/**
 * 
 * @ClassName: UserInfoCallable 
 * @Description: 处理用户注册
 * @author wangpk
 * @date 2015-10-13 下午2:51:05 
 *
 */
public class UserInfoCallable extends AbstractUserDbJob implements Runnable{
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(UserInfoCallable.class);
	private String message;
	public UserInfoCallable(String message) {
		this.message = message;
	}

	public void run() {
		doAddUser(message,false);
	}

}
