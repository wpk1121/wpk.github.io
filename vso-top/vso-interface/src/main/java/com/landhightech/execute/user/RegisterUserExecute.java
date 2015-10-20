package com.landhightech.execute.user;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landhightech.bean.User;
import com.landhightech.constant.PropsKey;
import com.landhightech.domain.Response;
import com.landhightech.execute.AbstractvalidateExecute;
import com.landhightech.servlet.Start;
import com.landhightech.thread.SendMqCallable;
import com.landhightech.threadpool.SendMqThreadPool;
import com.landhightech.util.PropertiesUtil;
/**
 * 
 * @ClassName: RegisterUserExecute 
 * @Description: 处理用户注册.
 * @author wangpk
 * @date 2015-10-12 下午3:44:57 
 *
 */
public class RegisterUserExecute extends AbstractvalidateExecute{
	
	@Override
	public String doProcess(JSONObject jobject) {
		String resultJson = null;
		try {
			User user = JSON.parseObject(jobject.toString(), User.class);
			user.setCreateTime(new Date());
			user.setMoney(0f);
//			IUserService userService = UserServiceImpl.getInstance();
//			Long uid = userService.addUser(user);
			SendMqThreadPool.execute(new SendMqCallable(JSON.toJSONString(user),
					Start.props.get(MqKey.QUEUE_REGISTER_USER),
					PropertiesUtil.getValue(PropsKey.RedisKey.REDIS_FAIL_USER_QUEUE)));
			Response response = new Response();
//			if(uid != null)
//				response.setUid(uid.toString());
			resultJson = JSON.toJSONString(response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultJson = getErrorResult(0,null);
		}
		return resultJson;
	}

}
