package com.dt.module.om.util;

/**
 * @author: algernonking
 * @date: 2017年12月25日 下午9:58:55
 * @Description: TODO
 */
public class RemoteShellResult {

	public int code;
	public StringBuffer result = new StringBuffer();

	public static RemoteShellResult setData(int code, StringBuffer result) {
		RemoteShellResult res = new RemoteShellResult();
		res.result = result;
		res.code = code;
		return res;
	}
	public void print() {
		System.out.println("code:\n"+code);
		System.out.println("result:\n"+result);
	}

}
