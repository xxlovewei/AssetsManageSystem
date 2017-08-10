package com.dt.core.common.util;

import java.util.UUID;

/** 
* @author 作者 Lank 
* @version 创建时间：2017年8月1日 上午11:23:29 
* 类说明 
*/
public class UuidUtil {

	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		str = str.replaceAll("-", "");
		return str;
	}
}


