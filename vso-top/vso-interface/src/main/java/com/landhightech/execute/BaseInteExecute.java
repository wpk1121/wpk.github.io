package com.landhightech.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.landhightech.constant.VsoConstant;
import com.landhightech.domain.Context;
import com.landhightech.domain.Response;
import com.landhightech.util.StringUtil;
/**
 * 
 * @ClassName: BaseInteExecute 
 * @Description: 处理客户端请求抽象类.
 * @author wangpk
 * @date 2015-10-12 下午2:41:36 
 *
 */
public abstract class BaseInteExecute implements VsoConstant{

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract String doProcess(Context context);

	public static String getErrorResult(int ret,String str) {
		if (ret == 0) {
			ret =  CodeConstant.DEFAULT_ERROR_CODE;
		}
		if (StringUtil.isStrNvl(str)) {
			str =  MessageConstant.DEFAULT_ERROR_MESSAGE;
		}
		Response response = new Response();
		response.setRet(ret);
		response.setMsg(str);
		return JSON.toJSONString(response);
	}
}
