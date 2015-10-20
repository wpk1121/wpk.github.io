package com.landhightech.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName: ResponseSender 
 * @Description: 响应客户端内容处理类(gzip压缩).
 * @author wangpk
 * @date 2015-10-12 下午2:39:35 
 *
 */
public class ResponseSender {

	private static final Logger logger = LoggerFactory.getLogger(ResponseSender.class);
	//默认响应编码
	private static final String default_charset = "utf-8";
	//默认响应contentType
	private static final String default_ContentType = "text/plain;charset=";
	
	/**
	 * 
	 * @Title: sendGzipResponse 
	 * @Description: 使用gzip压缩响应.
	 * @param response
	 * @param res
	 * @param charset
	 * @param contentType
	 */
	public static void sendGzipResponse(HttpServletResponse response, byte[] res, String charset, String contentType) {
		ServletOutputStream o = null;
		GZIPOutputStream out = null;
		if(charset == null || "".equalsIgnoreCase(charset.trim()))
			charset = default_charset;
		if(contentType == null || "".equalsIgnoreCase(contentType.trim()))
			contentType = default_ContentType;
		response.setCharacterEncoding(charset);
		response.setContentType(contentType + charset);
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Connection", "keep-alive");
		try {
			o = response.getOutputStream();
			out = new GZIPOutputStream(o);
			out.write(res);
		}
		catch (UnsupportedEncodingException e) {
			logger.error("response gzip err.", e);
		}
		catch (IOException e) {
			logger.error("response gzip err.", e);
		}
		finally {
			try {
				if (out != null) {
					out.flush();
					out.finish();
					out.close();
				}
				if (o != null) {
					o.close();
				}
			}
			catch (IOException e) {
				logger.error("response gzip err...response:[" + res + "]", e);
			}
		}
	}

	/**
	 * 
	 * @Title: sendResponse 
	 * @Description: 普通响应
	 * @param response
	 * @param res
	 * @param charset
	 * @param contentType
	 */
	public static void sendResponse(HttpServletResponse response, byte[] res, String charset, String contentType) {
		ServletOutputStream o = null;
		if(charset == null || "".equalsIgnoreCase(charset.trim()))
			charset = default_charset;
		if(contentType == null || "".equalsIgnoreCase(contentType.trim()))
			contentType = default_ContentType;
		response.setCharacterEncoding(charset);
		response.setContentType(contentType + charset);
		response.setHeader("Connection", "keep-alive");
		try {
			o = response.getOutputStream();
			o.write(res);
		}
		catch (UnsupportedEncodingException e) {
			logger.error("response err.", e);
		}
		catch (IOException e) {
			logger.error("response err.", e);
		}
		finally {
			try {
				if (o != null) {
					o.flush();
					o.close();
				}
			}
			catch (IOException e) {
				logger.error("response err...response:[" + res + "]", e);
			}
		}
	}
}
