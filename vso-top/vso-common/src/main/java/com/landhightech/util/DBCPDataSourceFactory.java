package com.landhightech.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName: DBCPDataSourceFactory 
 * @Description: DBCP连接池
 * @author wangpk
 * @date 2015-10-12 下午3:51:21 
 *
 */
public class DBCPDataSourceFactory extends UnpooledDataSourceFactory {
	public static final Logger logger = LoggerFactory.getLogger(DBCPDataSourceFactory.class);
	private static InputStream inStream;
	private static Properties pro;
	private static DataSource datasource;

	static {
		//读取数据库配置
		try {
			inStream = DBCPDataSourceFactory.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties");
			pro = new Properties();
			pro.load(inStream);
			datasource = BasicDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			logger.error(e.getMessage());
//			throw new RuntimeException("初始化错误！");
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	public DBCPDataSourceFactory() {
		this.dataSource = getDataSource();
	}
	
	public DataSource getDataSource() {
		return datasource;
	}
	/**
	 * 获取数据库连接
	 * @Title: getConnection 
	 * @Description: TODO
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("得到数据库连接失败！");
		}
	}

}
