package com.landhightech.domain;
/**
 * 
 * @ClassName: LogBean 
 * @Description: 日志类.
 * @author wangpk
 * @date 2015-10-12 下午3:17:36 
 *
 */
public class LogBean {
	//测试用户id
	private String id;
	//测试用户名
	private String name;
	//测试用户密码
	private String password;
	//测试用 请求日期
	private String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
