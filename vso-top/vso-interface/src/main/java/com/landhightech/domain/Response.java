package com.landhightech.domain;

import com.landhightech.constant.VsoConstant;


/**
 * 
 * @ClassName: Response 
 * @Description: 封装接口响应内容
 * @author wangpk
 * @date 2015-10-12 下午2:09:04 
 *
 */
public class Response implements VsoConstant{
	//响应码
	private int ret;
	//响应业务码
	private int code;
	//响应信息
	private String msg;
	
	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Response(){
		ret = CodeConstant.DEFAULT_SUCCESS_CODE;
		code = CodeConstant.DEFAULT_SUCCESS_CODE;
		msg = MessageConstant.DEFAULT_SUCCESS_MESSAGE;
	}
}
