package com.landhightech.service.test;

import org.junit.Test;

import com.landhightech.bean.NotifyReturnData;
import com.landhightech.service.INotifyRecordService;
import com.landhightech.service.impl.NotifyRecordServiceImpl;

public class NotifyRecordServiceTest {
	@Test
	public void test(){
		INotifyRecordService service = NotifyRecordServiceImpl.getInstance();
		NotifyReturnData data = new NotifyReturnData();
		data.setIp("123");
		data.setNotify_type("acc");
		data.setPay_data("ab");
		data.setPay_time(11111l);
		data.setPay_type("aa");
		service.insertRecord(data);
	}
}
