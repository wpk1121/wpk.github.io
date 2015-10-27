package com.landhightech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.domain.Context;
import com.landhightech.domain.LogBean;

public class LoggerStatistics {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	//分隔符
	private static final char separat = '\001';
	//分隔符
	private static final char separat_ = ';';
	//字段为空时打印null
	private static final String defaultString = "null";
	//打印日志格式
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	//封装http请求信息对象
	private Context context;
	

	public LoggerStatistics(Context context) {
		this.context = context;
	}
	public void loginfo(){
		logger.info(new StringBuffer(context.getMethod())
		.append(separat_)
		.append(context.getJsonRequest())
		.toString());
	}
	/**
	 * 
	 * @Title: loginfo 
	 * @Description: 打印日志
	 */
	/*public void loginfo() {
		if (context == null || StringUtil.isJsonNvl(context.getJsonRequest())) {
			return;
		}
		String json = context.getJsonRequest();
		if (context.getMethod() == null) {
			return;
		}
		
		LogBean bean = JSON.parseObject(json,LogBean.class);
		if (bean == null) {
			return;
		}
		try {
			loginfo(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 
	 * @Title: loginfo 
	 * @Description: 打印日志
	 * @param bean
	 * @throws Exception
	 */
	public void loginfo(LogBean bean) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(context.getMethod());
		buf.append(separat);
		String time = sdf.format(new Date());
		buf.append(time);
		buf.append(separat);
		buf.append(context.getRemoteAddress());
		buf.append(separat);
		handleLog(buf, bean.getId(),bean.getName(),bean.getPassword(),bean.getDate());

		logger.info(buf.toString());
	}

	/**
	 * 
	 * @Title: handleLog 
	 * @Description: 日志字段统一处理
	 * @param buf
	 * @param strs
	 */
	private void handleLog(StringBuffer buf, String... strs) {
		if (buf != null && strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				if (!StringUtil.isStrNvl(strs[i])) {
					buf.append(strs[i]);
				} else {
					buf.append(defaultString);
				}
				// 最后面不加分隔符
				if (i < strs.length - 1) {
					buf.append(separat);
				}
			}
		}
	}

}
