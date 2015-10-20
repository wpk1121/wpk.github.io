package com.landhightech.service;
/**
 * 
 * @ClassName: IMqMessageService 
 * @Description: 处理mq消息接口
 * @author wangpk
 * @date 2015-10-19 上午10:28:52 
 *
 */
public interface IRedisQueueService {
	/**
	 * 
	 * @Title: putErrorMessage 
	 * @Description: 发送失败的消息存储到redis
	 * @param queue
	 * @param value
	 * @return boolean
	 */
	public boolean putErrorMessage(String queue,String value);
	
	/**
	 * 
	 * @Title: getErrorMessage 
	 * @Description: 从redis中获取发送失败的消息
	 * @param queue
	 * @return String
	 */
	public String getErrorMessage(String queue);
	/**
	 * 
	 * @Title: getMessageSize 
	 * @Description: 从redis中获取发送失败消息的队列长度
	 * @param queue
	 * @return long
	 */
	public long getMessageSize(String queue);
}
