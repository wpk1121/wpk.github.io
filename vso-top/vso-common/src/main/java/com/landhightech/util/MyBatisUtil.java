package com.landhightech.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName: MyBatisUtil 
 * @Description: 获取mybatis数据库连接.
 * @author wangpk
 * @date 2015-10-12 下午3:51:38 
 *
 */
public class MyBatisUtil {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisUtil.class);

	public static SqlSessionFactory sqlMapper = null;
	private static Reader reader = null;
	static {
		try {
			//读取mybatis配置.
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
//			throw new RuntimeException("初始化错误！");
		}finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}
	/**
	 * 
	 * @Title: getSqlSession 
	 * @Description: 获取Session.
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = sqlMapper.openSession();
//		session.getConfiguration().setDefaultStatementTimeout(5);
		return session;
	}
}
