package com.landhightech.quartz.mq;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.domain.StateChannel;
import com.landhightech.mq.ChannelFactory;
/**
 * 
 * @ClassName: CheckChannelCallable 
 * @Description: 定时检查连接 关闭不活动的链接
 * @author wangpk
 * @date 2015-10-14 下午3:55:24 
 *
 */
public class CheckChannelJob{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @Title: work 
	 * @Description: 检查连接 关闭不活动的链接
	 */
	public void work() {
     	try {
     		List<StateChannel>  list = ChannelFactory.getInstance().getAllChannel();
     		int size = 0;
			if(list!=null)
				size = list.size();
     		logger.info("job start;list size:{}",size);
     		if(list != null && list.size()>0){
     			Iterator<StateChannel> it =list.iterator();  
					while(it.hasNext()) {
						StateChannel sc = it.next();
						//如果定时器检查连接不在使用关闭连接.
						if(sc != null && !sc.isBusy()){
							ChannelFactory.getInstance().closeChannel(sc);
							sc.setChannel(null);
							it.remove();
						}
					}
				logger.info("job end;list size:{}",list.size());	
     		}
     	} catch (Exception e) {
     		logger.error(e.getMessage());
     	}
     }
}
