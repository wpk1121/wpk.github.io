package com.landhightech.service;

import java.util.List;

import com.landhightech.bean.User;
/**
 * 
 * @ClassName: IUserService 
 * @Description: 处理用户信息接口
 * @author wangpk
 * @date 2015-10-19 上午10:28:26 
 *
 */
public interface IUserService {
	/**
	 * 
	 * @Title: getUsersFromDB 
	 * @Description: 获取用户列表.
	 * @return List<User>
	 */
	public List<User> getUsersFromDB();
	/**
	 * 
	 * @Title: addUser 
	 * @Description: 注册用户.
	 * @param user
	 * @return Long
	 */
	public Long addUser(User user);
}
