package com.dev;

import java.util.UUID;

import com.dt.core.dao.Rcd;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.util.ToolUtil;

/** 
 * @author: algernonking
 * @date: Oct 10, 2019 1:37:53 PM 
 * @Description: TODO 
 */
public class abc {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		
		System.out.println(uuid.toString().substring(9, 23));
	}
	 


}

