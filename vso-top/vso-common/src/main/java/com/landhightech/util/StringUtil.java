package com.landhightech.util;
/**
 * 
 * @ClassName: CommonUtil 
 * @Description:字符串工具类
 * @author wangpk
 * @date 2015-10-12 下午3:08:22 
 *
 */
public class StringUtil {
	/**
	 * 
	 * @Title: isStrNvl 
	 * @Description: 判断字符串是否空或者空串.
	 * @param str 
	 * @return boolean
	 */
	public static boolean isStrNvl(String str){
		boolean boo = false;
		if(null == str || "".equals(str.trim()))
			boo = true;
		return boo;
	}

	/**
	 * 
	 * @Title: isJsonNvl 
	 * @Description: 判断字符串是否空或者空串或"null".
	 * @param str
	 * @return boolean
	 */
	public static boolean isJsonNvl(String str){
		boolean boo = false;
		if(isStrNvl(str) || "NULL".equalsIgnoreCase(str))
			boo = true;
		return boo;
	}
}
