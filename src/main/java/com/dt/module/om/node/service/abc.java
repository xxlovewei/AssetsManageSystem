package com.dt.module.om.node.service;

import com.dt.core.tool.lang.ArrayUtil;

/** 
 * @author: algernonking
 * @date: 2018年1月31日 下午2:57:45 
 * @Description: TODO 
 */
public class abc {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void tt( Class<?> clazz) {
		
		ArrayUtil.toList(clazz.getEnumConstants());
		 	 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tt(NodeEntity.class);;
		 
	}

}

