package com.landhightech.constant;
/**
 * 
 * @ClassName: PropsKey 
 * @Description: 统一保存配置文件key
 * @author wangpk
 * @date 2015-10-16 下午4:03:42 
 *
 */
public interface PropsKey {
	/**
	 * 
	 * @ClassName: RedisKey 
	 * @Description: redis配置文件key
	 *
	 */
	public interface RedisKey{
		//redis服务器ip
		public String REDIS_IP = "redis_ip";
		//redis服务器端口
		public String REDIS_PORT = "redis_port";
		//控制一个pool可分配多少个jedis实例
		public String REDIS_MAXACTIVE = "redis_maxActive";
		//最大空闲连接数
		public String REDIS_MAXIDLE = "redis_maxIdle";
		//最小空闲连接数
		public String REDIS_MINIDLE = "redis_minIdle";
		//获取连接时的最大等待毫秒数
		public String REDIS_MAXWAIT = "redis_maxWait";
		//在获取连接的时候检查有效性
		public String REDIS_TESTONBORROW = "redis_testOnBorrow";
		//在return给pool时,是否提前进行validate操作
		public String REDIS_TESTONRETURN = "redis_testOnReturn";
		//分布式锁默认失效时间
		public String REDIS_LOCK_EXPIRE = "redis_lock_expire";
		
		//用户信息mq发送失败存储到redis的queue名称
		public String REDIS_FAIL_USER_QUEUE = "redis_userMessage_fail";
		//用户信息数据库插入失败存储到redis的queue名称
		public String REDIS_FAIL_USERADD_QUEUE = "redis_userAdd_fail";
		
	}
	
}
