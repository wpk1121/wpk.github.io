package com.landhightech.execute.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landhightech.bean.User;
import com.landhightech.constant.PropsKey;
import com.landhightech.domain.Response;
import com.landhightech.execute.AbstractvalidateExecute;
import com.landhightech.thread.mq.SendMqCallable;
import com.landhightech.threadpool.SendMqThreadPool;
import com.landhightech.util.InterfacePropUti;
import com.landhightech.util.PropertiesUtil;

/**
 * @ClassName ModifyUserExecute
 * @author leobert.lan
 * @date 2015年10月20日
 * @desc : 仅为了熟悉流程，接口设计不一定合理
 */
public class ModifyUserExecute extends AbstractvalidateExecute{

	@Override
	public String doProcess(JSONObject jobject) {
		String resultJson = null;
		System.out.println("modify work");
		try {
			User user = JSON.parseObject(jobject.toString(), User.class);
			//异步处理
			SendMqThreadPool.execute(new SendMqCallable(JSON.toJSONString(user),
					InterfacePropUti.getValue(MqKey.QUEUE_MODIFY_USER),
					PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USER_QUEUE)));
			
			logger.info("queue:"+InterfacePropUti.getValue(MqKey.QUEUE_MODIFY_USER)
					+"\nfailuresave:"+PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USER_QUEUE));
			
			Response response = new Response();
			resultJson = JSON.toJSONString(response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultJson = getErrorResult(0,null);
		}
		return resultJson;
	}

}
