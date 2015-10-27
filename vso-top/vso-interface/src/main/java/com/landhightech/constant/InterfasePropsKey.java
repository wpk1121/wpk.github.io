package com.landhightech.constant;
/**
 * 
 * @ClassName: InterfasePropsKey 
 * @Description: 定义配置文件中的key
 * @author wangpk
 * @date 2015-10-14 下午12:00:43 
 *
 */
public interface InterfasePropsKey extends PropsKey{
	/**
	 * 
	 * @ClassName: MqKey 
	 * @Description:rabbitmq配置文件中的key
	 *
	 */
	interface MqKey{
		//rabbitmq服务器ip
		public String MQ_HOST = "mqHost";
		//rabbitmq用户名
		public String MQ_USER = "mqUser";
		//rabbitmq密码
		public String MQ_PASSWORD = "mqPassword";
		//rabbitmq服务器端口
		public String MQ_PORT = "mqPort";
		//注册用户queue名称
		public String QUEUE_REGISTER_USER = "queue_register_user";
		
		//注册用户queue名称
		public String QUEUE_MODIFY_USER = "queue_modify_user";
		
		public String QUEUE_NOTIFY_RECORD = "queue_notify_record";
	}
	/**
	 * 
	 * @ClassName: ThreadPoolKey 
	 * @Description: threadpool配置文件中的key
	 *
	 */
	interface ThreadPoolKey{
		//线程池核心线程数，核心线程会一直存活，即使没有任务需要处理。
		public String SENDMQ_COREPOOLSIZE = "sendMq_corePoolSize";
		//当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，直到线程数量达到maxPoolSize
		public String SENDMQ_MAXIMUMPOOLSIZE = "sendMq_maximumPoolSize";
		//当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize
		public String SENDMQ_KEEPALIVETIME = "sendMq_keepAliveTime";
		//任务队列长度
		public String SENDMQ_QUEUESIZE = "sendMq_queueSize";
	}
}
