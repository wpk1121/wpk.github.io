package com.landhightech.rabbimq;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.constant.InterfasePropsKey;
import com.landhightech.servlet.Start;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 
 * @ClassName: ChannelFactory 
 * @Description: 获取rabbitmq连接.
 * @author wangpk
 * @date 2015-10-14 上午10:06:50 
 *
 */
public class ChannelFactory implements InterfasePropsKey{
	private static final Logger logger = LoggerFactory.getLogger(ChannelFactory.class);
	private static ThreadLocal<StateChannel> threadLocal = new ThreadLocal<StateChannel>();
	private static ConnectionFactory factory;
	private static List<StateChannel> list = new LinkedList<StateChannel>();
	private static ChannelFactory channelFactory;

	private ChannelFactory() {
		super();
	}

	public static ChannelFactory getInstance() {
		if(factory == null){
			 try
			    {
				 factory = new ConnectionFactory();
				 factory.setHost(Start.props.get(MqKey.MQ_HOST)); 
			     factory.setUsername(Start.props.get(MqKey.MQ_USER));
			     factory.setPassword(Start.props.get(MqKey.MQ_PASSWORD));
			   	 factory.setPort(Integer.parseInt(Start.props.get(MqKey.MQ_PORT)));
			    } catch (Exception e) {
			    	logger.error("init mq connection fail.",e);
			    }
		}
		if (channelFactory == null) {
			channelFactory = new ChannelFactory();
		}
		return channelFactory;
	}
	/**
	 * 
	 * @Title: getChannel 
	 * @Description: 获取一个线程安全的channel.
	 * @return Channel
	 */
	public StateChannel getChannel(){
		 StateChannel stateChannel = threadLocal.get();
		 Channel channel =  null;
		 if(stateChannel != null)
			  channel =  stateChannel.getChannel();
		 if(channel == null || !channel.isOpen()){
			 Connection conn = getConnection(channel);
			 try {
				if(conn != null)
					channel = conn.createChannel();
			} catch (Exception e) {
				logger.error("create new mq channel fail.",e);
			}
			if(channel != null && channel.isOpen()){
				if(stateChannel == null)
					 stateChannel = new StateChannel();
				stateChannel.setChannel(channel);
				list.add(stateChannel);
				threadLocal.set(stateChannel);
			}
		 }
//		logger.info(Thread.currentThread().getId() +"," +channel + "---"+list.size());
		return stateChannel;
	 }
	 /**
	  * 
	  * @Title: getConnection 
	  * @Description: 获取connection
	  * @param channel
	  * @return
	  */
	 private Connection getConnection(Channel channel){
		 Connection conn = null;
		 if(channel ==null || !channel.isOpen())
			try {
				conn = factory.newConnection();
			} catch (IOException e) {
				logger.error("open new mq connection fail.",e);
			}
		 return conn;
	 }
	/**
	 * 
	 * @Title: closeChannel 
	 * @Description: 关闭连接.
	 */
	 public void closeChannel(StateChannel stateChannel){
		 Channel channel = stateChannel.getChannel();
		 if(channel != null && channel.isOpen()){
			 try {
				channel.close();
				Connection conn = channel.getConnection();
				if(conn !=null && conn.isOpen())
					conn.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		 }
	 }
	 /**
	  * 
	  * @Title: getAllChannel 
	  * @Description: 获取已保存的所有连接
	  * @return
	  */
	 public  List<StateChannel> getAllChannel(){
		 return list;
	 }
	 
}
