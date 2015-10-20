package com.landhightech.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	/**
	 * 
	 * @Title: getNetType 
	 * @Description: 获取客户端请求id地址
	 * @param request
	 * @return String
	 */
	public static String getNetType(HttpServletRequest request) {
		 return request.getHeader("x-up-bear-type");
	}

}
