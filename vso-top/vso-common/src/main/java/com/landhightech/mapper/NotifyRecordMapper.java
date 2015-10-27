package com.landhightech.mapper;

import com.landhightech.bean.NotifyReturnData;

/**
 * @ClassName NotifyRecordMapper
 * @author leobert.lan
 * @date 2015年10月26日
 * @desc : vso_notify_record Dao </br>
 * 提供服务：
 * <li>insert</li>
 * <li></li>
 */
public interface NotifyRecordMapper {
	
	/**
	 * @author: leobert.lan
	 * @time: 2015年10月26日 上午11:45:21
	 * @desc: insert the log/record data in to vso_notify_record
	 * @param 
	 * @return boolean: true if insert success,false otherwise
	 */
	public boolean insertRecord(NotifyReturnData data);

}
