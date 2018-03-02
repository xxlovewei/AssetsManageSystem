package com.dt.core.common.base;
 

/**
 * @author: jinjie
 * @date: 2018年3月2日 上午8:26:30
 * @Description: TODO
 */
public class TT {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		R r = new R();
		r.setData(1.1);
		System.out.println(r.asJsonStr());
		System.out.println(r.toString());
		System.out.println(r.getDataToString());
		System.out.println(R.SUCCESS("成功", 12345).toString());
	}

}
