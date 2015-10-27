package com.landhightech.logger.test;

import org.junit.Test;

import com.landhightech.quartz.mq.UserDbJob;
import com.landhightech.thread.mq.AbstractUserDbExec;
import com.landhightech.thread.mq.impl.UserInfoCallable;

public class LoggerTest {
	@Test
	public void test(){
		UserInfoCallable exec = new UserInfoCallable("");
//		exec.doAddUser("bbb", false);
		new UserDbJob().work();
//		Message m = new Message("aaa".getBytes(), null);
//		new UserInfoReceiver().onMessage(m);
	}
}
