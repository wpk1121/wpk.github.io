package com.landhightech.thread.mq.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.thread.mq.AbstractUserDbExec;

/**
 * 
 * @ClassName: UserInfoCallable 
 * @Description: 处理用户注册
 * @author wangpk
 * @date 2015-10-13 下午2:51:05 
 *
 */
public class UserInfoCallable extends AbstractUserDbExec implements Runnable{
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
