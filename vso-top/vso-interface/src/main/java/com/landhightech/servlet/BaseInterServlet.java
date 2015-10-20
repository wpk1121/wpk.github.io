package com.landhightech.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.landhightech.constant.VsoConstant;
import com.landhightech.domain.Context;
import com.landhightech.execute.BaseInteExecute;
import com.landhightech.util.ResponseSender;
/**
 * 
 * @ClassName: BaseInterServlet 
 * @Description: 基础servlet.
 * @author wangpk
 * @date 2015-10-12 下午2:40:31 
 *
 */
@WebServlet(urlPatterns = "/vso", asyncSupported = false, loadOnStartup = 1)
public class BaseInterServlet extends HttpServlet implements VsoConstant{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String resultJson = null;
		String method = req.getParameter("method");
		BaseInteExecute base = null;
		//若请求方法错误返回错误信息.
		resultJson = BaseInteExecute.getErrorResult(0,MessageConstant.METHOD_ERROR_MESSAGE);
			
		if(method != null && !"".equals(method.trim())){
			String med = Start.executes.get(method);
			if (med != null && !"".equalsIgnoreCase(med)) {
				try {
					base = (BaseInteExecute) Class.forName(med).newInstance();
				}
				catch (Exception e) {
					
				}
			}
			if (base != null) {
				Context context = new Context();
				context.setRequest(req);
				context.setMethod(method);
				resultJson = base.doProcess(context);
			} 
		}
		ResponseSender.sendGzipResponse(response, resultJson.getBytes("utf-8"), null, null);
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0, arg1);
	}

}
