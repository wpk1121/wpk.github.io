package com.landhightech.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.bean.User;
import com.landhightech.mapper.UserMapper;
import com.landhightech.service.IUserService;
import com.landhightech.util.MyBatisUtil;

public class UserServiceImpl implements IUserService {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static IUserService service = new UserServiceImpl();

	private UserServiceImpl() {
		super();
	}

	public static IUserService getInstance() {
		if (service == null) {
			service = new UserServiceImpl();
		}
		return service;
	}
	
	
	public List<User> getUsersFromDB() {
		SqlSession ss = null;
		List<User> list = null;
		try {
			ss = MyBatisUtil.getSqlSession();
			UserMapper mapper = ss
					.getMapper(UserMapper.class);
			list = mapper.selectUsers();
		} catch (Exception e) {
			logger.error("select user from db error.{}",e.getMessage());
		} finally {
			ss.close();
		}
		return list;
	}

	public Long addUser(User user) {
		SqlSession ss = null;
		Long id = null;
		try {
			ss = MyBatisUtil.getSqlSession();
			UserMapper mapper = ss
					.getMapper(UserMapper.class);
			mapper.insertUser(user);
			ss.commit();
		} catch (Exception e) {
			if(ss!=null)
				try {
					ss.rollback();
				} catch (Exception e1) {
					logger.error(e.getMessage());
				}
			logger.error("insert user to db error.{}",e.getMessage());
		} finally {
			if(ss!=null)
				try {
					ss.close();
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
		}
		
		if(user != null)
			id = user.getId();
		return id;
	}

}
