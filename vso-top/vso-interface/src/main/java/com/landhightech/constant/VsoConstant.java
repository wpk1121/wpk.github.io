package com.landhightech.constant;
/**
 * 
 * @ClassName: VsoConstant 
 * @Description: 常量类
 * @author wangpk
 * @date 2015-10-16 上午10:26:35 
 *
 */
public interface VsoConstant extends InterfasePropsKey{
	/**
	 * 
	 * @ClassName: CodeConstant 
	 * @Description: http响应code
	 *
	 */
	interface CodeConstant{
		//默认响应成功状态码
		public int DEFAULT_ERROR_CODE = 9000;
		//默认响应失败状态码
		public int DEFAULT_SUCCESS_CODE = 0;
	}
	/**
	 * 
	 * @ClassName: MessageConstant 
	 * @Description: http响应消息
	 *
	 */
	interface MessageConstant{
		//默认响应成功信息
		public String DEFAULT_SUCCESS_MESSAGE = "success";
		//默认响应失败信息
		public String DEFAULT_ERROR_MESSAGE = "server err.";
		//方法名错误
		public String METHOD_ERROR_MESSAGE = "server method err.";
		//参数错误
		public String PARAM_ERROR_MESSAGE = "param err.";
	}
	
}
