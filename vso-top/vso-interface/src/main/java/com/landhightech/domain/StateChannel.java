package com.landhightech.domain;

import com.rabbitmq.client.Channel;
/**
 * 
 * @ClassName: StateChannel 
 * @Description: 标记rabbimq channel是否使用中,由定时任务检查关闭
 * @author wangpk
 * @date 2015-10-20 上午9:23:08 
 *
 */
public class StateChannel {
	//rabbimq channel对象
	private Channel channel;
	//channel是否正在使用的标志，默认没有正在使用
	private boolean isBusy;
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	
}
