package com.landhightech.execute.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landhightech.bean.NotifyReturnData;
import com.landhightech.constant.PropsKey;
import com.landhightech.domain.Response;
import com.landhightech.execute.AbstractvalidateExecute;
import com.landhightech.thread.mq.SendMqCallable;
import com.landhightech.threadpool.SendMqThreadPool;
import com.landhightech.util.InterfacePropUti;
import com.landhightech.util.PropertiesUtil;

public class VsoNotifyRecord extends AbstractvalidateExecute {

	/**
	 * attention：Override！ also see：@see
	 * com.landhightech.execute.AbstractvalidateExecute
	 * #doProcess(com.alibaba.fastjson.JSONObject)
	 * 
	 * @author leobert.lan
	 * @time: 2015年10月23日 下午3:29:26
	 * @desc: the thread body to process user charge/return-log jobs
	 */
	@Override
	public String doProcess(JSONObject jobject) {
		String resultJson = null;
		// logger.info("datadata:"+jobject.toString());

		try {
			// 创建实体
			NotifyReturnData data = JSON.parseObject(jobject.toString(),
					NotifyReturnData.class);
			// TODO 检校，如果不需要检校则不需要转化实体

			// logger.info(JSON.toJSONString(data));
			SendMqThreadPool
					.execute(new SendMqCallable(
							JSON.toJSONString(data),
							InterfacePropUti
									.getValue(MqKey.QUEUE_NOTIFY_RECORD),
							PropertiesUtil
									.getValue(PropsKey.RedisKey.REDIS_FAIL_NOTIFYRECORD_QUEUE)));
			Response response = new Response();
			resultJson = JSON.toJSONString(response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// TODO 约定错误码
			resultJson = getErrorResult(0, null);
		}
		return resultJson;
	}
}
