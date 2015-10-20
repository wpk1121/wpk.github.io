package com.landhightech.execute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landhightech.domain.Context;
import com.landhightech.util.LoggerStatistics;
import com.landhightech.util.StringUtil;
/**
 * 
 * @ClassName: AbstractvalidateExecute 
 * @Description: TODO
 * @author wangpk
 * @date 2015-10-12 下午4:50:58 
 *
 */
public abstract class AbstractvalidateExecute extends BaseInteExecute {

	protected Context context;
	public void setContext(Context context) {
		this.context = context;
	}
	/**
	 * 统一打印日志等.
	 */
	public String doProcess(Context context) {
		this.context = context;
		String jsonRequest = context.getJsonRequest();
		if (StringUtil.isJsonNvl(jsonRequest)) {
			return getErrorResult(0,MessageConstant.PARAM_ERROR_MESSAGE);
		}
		JSONObject json = JSON.parseObject(jsonRequest);
		if (json == null || json.isEmpty()) {
			return getErrorResult(0,MessageConstant.PARAM_ERROR_MESSAGE);
		}
		LoggerStatistics log = new LoggerStatistics(context);
		log.loginfo();
		String result = doProcess(json);
		return result;
	}

	public abstract String doProcess(JSONObject jobject);

	

}
