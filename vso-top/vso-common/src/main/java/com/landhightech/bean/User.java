package com.landhightech.bean;

import java.util.Date;

/**
 * 
 * @ClassName: User 
 * @Description: 测试bean
 * @author wangpk
 * @date 2015-10-12 上午11:52:31 
 *
 */
public class User {
	//主键id
	private Long id;
	//用户名
	private String name;
	//密码
	private String password;
	//金额
	private Float money;
	//创建日期
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
