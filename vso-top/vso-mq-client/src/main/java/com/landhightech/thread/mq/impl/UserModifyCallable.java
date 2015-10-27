package com.landhightech.thread.mq.impl;

import com.landhightech.thread.mq.AbstractUserDbExec;


public class UserModifyCallable extends AbstractUserDbExec implements Runnable{
	
	private String mMessage;

	public UserModifyCallable(String string) {
		this.mMessage = string;
	}

	public void run() {
		System.out.println("tag  "+mMessage);
	}

}
