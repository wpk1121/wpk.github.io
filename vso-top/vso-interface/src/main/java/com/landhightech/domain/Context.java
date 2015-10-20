package com.landhightech.domain;

import javax.servlet.http.HttpServletRequest;

import com.landhightech.util.RequestUtil;
/**
 * 
 * @ClassName: Context 
 * @Description: 封装http请求参数类
 * @author wangpk
 * @date 2015-10-16 上午10:27:42 
 *
 */
public class Context {
	//客户端请求json参数
	private String jsonRequest;
	//客户端请求ip
	private String remoteAddress;
	//客户端请求方法
	private String method;	
	//客户端请求网络类型
	private String netType;

	public String getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(String jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}
	/**
	 * 
	 * @Title: setRequest 
	 * @Description: 设置ip等参数.
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		this.netType = RequestUtil.getNetType(request);
		this.jsonRequest = request.getParameter("jsonRequest");
		Object obj= request.getHeader("X-Real-IP");
		if(obj!=null){
			this.remoteAddress=obj.toString();
		}else{
			this.remoteAddress = request.getRemoteAddr();
		}
		
	}
	
}
