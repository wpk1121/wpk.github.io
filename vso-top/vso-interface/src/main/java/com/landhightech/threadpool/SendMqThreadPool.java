package com.landhightech.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.landhightech.constant.InterfasePropsKey;
import com.landhightech.servlet.Start;

/**
 * 
 * @ClassName: SendMqThreadPool 
 * @Description: 线程池
 * @author wangpk
 * @date 2015-10-13 上午10:47:21 
 *
 */
public class SendMqThreadPool implements InterfasePropsKey{

	@SuppressWarnings("rawtypes")
	private static LinkedBlockingQueue linkedBlockingQueue;
	private static ThreadPoolExecutor threadPool;
	/**
	 * 
	 * @Title: init 
	 * @Description: 初始化线程池.
	 */
	@SuppressWarnings("unchecked")
	public static void init() throws Exception{
		int corePoolSize = Integer.parseInt(Start.props.get(ThreadPoolKey.SENDMQ_COREPOOLSIZE));
		int maximumPoolSize = Integer.parseInt(Start.props.get(ThreadPoolKey.SENDMQ_MAXIMUMPOOLSIZE));
		int keepAliveTime = Integer.parseInt(Start.props.get(ThreadPoolKey.SENDMQ_KEEPALIVETIME));
		int queueSize = Integer.parseInt(Start.props.get(ThreadPoolKey.SENDMQ_QUEUESIZE));
		if(queueSize > 0)
			linkedBlockingQueue = new LinkedBlockingQueue<Runnable>(queueSize);
		else 
			linkedBlockingQueue = new LinkedBlockingQueue<Runnable>();
		threadPool = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize, keepAliveTime,
				TimeUnit.SECONDS, linkedBlockingQueue,
				new ThreadPoolExecutor.CallerRunsPolicy()); //自动重试
	}
	/**
	 * 
	 * @Title: execute 
	 * @Description: 线城池执行方法
	 * @param r
	 */
	public static void execute(Runnable r){
		threadPool.execute(r);
	}
	/**
	 * 
	 * @Title: getThreadSize 
	 * @Description: 获取线城池线程数量
	 * @return
	 */
	public static int getThreadSize() {
		return threadPool.getPoolSize();
	}
	/**
	 * 
	 * @Title: shutdown 
	 * @Description: 关闭线程池
	 */
	public static void shutdown() {
		threadPool.shutdown();
	}
	/**
	 * 
	 * @Title: getQueueSize 
	 * @Description: 获取队列长度
	 * @return
	 */
	public static int getQueueSize(){
		return linkedBlockingQueue.size();
	}

}
