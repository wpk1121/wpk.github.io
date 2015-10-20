package com.landhightech.service.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.landhightech.bean.User;
import com.landhightech.service.IUserService;
import com.landhightech.service.impl.UserServiceImpl;
import com.landhightech.util.MyBatisUtil;
import com.mysql.jdbc.Connection;

public class UserServiceTest {
	/**
	 * 
	 * @Title: testSelectUser 
	 * @Description: 测试查询用户
	 */
	@Test
	public void testSelectUser(){
		IUserService userService = UserServiceImpl.getInstance();
		List<User> list = userService.getUsersFromDB();
		try {
			if(list != null){
				for(User user:list){
					String userJson = JSON.toJSONString(user);
					System.out.println(userJson);
					User u = (User)(JSON.parseObject(userJson, User.class));
					System.out.println(u.getId() +","+u.getName()+","+u.getCreateTime());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: testInsertUser 
	 * @Description: 测试添加用户
	 */
	@Test
	public void testInsertUser(){
		Long id = 0l;
		IUserService userService = UserServiceImpl.getInstance();
		User user = new User();
		user.setName("a1");
		user.setPassword("123456");
		user.setCreateTime(new Date());
		user.setMoney(100.0f);
		id = userService.addUser(user);
		System.out.println(id);
	}
}
