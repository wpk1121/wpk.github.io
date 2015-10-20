package com.landhightech.mapper;

import java.util.List;

import com.landhightech.bean.User;
/**
 * 
 * @ClassName: UsertMapper 
 * @Description: user Dao.
 * @author wangpk
 * @date 2015-10-12 上午11:55:54 
 *
 */
public interface UserMapper {
	/**
	 * 
	 * @Title: selectUsers 
	 * @Description: 查询用户列表
	 * @return List<User>
	 */
	public List<User> selectUsers();
	/**
	 * 
	 * @Title: insertUser 
	 * @Description: 插入一条用户数据到数据库.
	 * @param user
	 * @return Long
	 */
	public Long insertUser(User user);
}
