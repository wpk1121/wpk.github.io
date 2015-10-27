package com.landhightech.bean;

/**
 * @ClassName NotifyReturnData
 * @author leobert.lan
 * @date 2015年10月23日
 * @desc : 支付log信息类，字段与成员变量名一致
 */
public class NotifyReturnData {
	private String notify_type;
	
	private String pay_type;
	
	private String pay_data;
	
	//支付时间，Unix时间戳 10位 数组库int(11),
	private Long pay_time;
	
	private String ip;

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_data() {
		return pay_data;
	}

	public void setPay_data(String pay_data) {
		this.pay_data = pay_data;
	}

	public Long getPay_time() {
		return pay_time;
	}

	public void setPay_time(Long pay_time) {
		this.pay_time = pay_time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
