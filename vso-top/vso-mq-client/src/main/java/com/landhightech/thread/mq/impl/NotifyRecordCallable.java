package com.landhightech.thread.mq.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.thread.mq.AbstractRecordDbExec;

public class NotifyRecordCallable extends AbstractRecordDbExec implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String message;
	public NotifyRecordCallable(String message) {
		this.message = message;
	}

	public void run() {
		doAddRecord(message,false);
	}

}
