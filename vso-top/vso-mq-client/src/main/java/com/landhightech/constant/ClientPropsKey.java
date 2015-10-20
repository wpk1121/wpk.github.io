package com.landhightech.constant;

public interface ClientPropsKey {
	
	interface ThreadPoolKey{
		//线程池核心线程数，核心线程会一直存活，即使没有任务需要处理。
		public String COMMON_COREPOOLSIZE = "common_corePoolSize";
		//当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，直到线程数量达到maxPoolSize
		public String COMMON_MAXIMUMPOOLSIZE = "common_maximumPoolSize";
		//当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize
		public String COMMON_KEEPALIVETIME = "common_keepAliveTime";
		//任务队列长度
		public String COMMON_QUEUESIZE = "common_queueSize";
	}
}
