package com.landhightech.service;

import com.landhightech.bean.NotifyReturnData;

/**
 * @ClassName INotifyRecordService
 * @author leobert.lan
 * @date 2015年10月26日
 * @desc : used to provide method to insert log to table vso_notify_record
 */
public interface INotifyRecordService {
	
	/**
	 * @author: leobert.lan
	 * @time: 2015年10月26日 上午11:45:21
	 * @desc: insert the log/record data in to vso_notify_record
	 * @param 
	 * @return boolean: true if insert success,false otherwise
	 */
	public boolean insertRecord(NotifyReturnData data);

}
