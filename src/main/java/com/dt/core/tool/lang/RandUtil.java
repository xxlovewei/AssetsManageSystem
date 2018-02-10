package com.dt.core.tool.lang;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: jinjie
 * @date: 2018年2月10日 下午1:25:53
 * @Description: TODO
 */
public class RandUtil {

	/**
	 * @Title:RandUtil
	 * @Description:TODO
	 */
	public RandUtil() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 均衡的随机从[mix,max]中获取数据
	 */
	public static int rand(int mix, int max) {
		Random rand = new Random();
		return rand.nextInt(max) + mix;
	}

	/*
	 * 从source中获取cnt个数的数据,第二次运行时source并原来的数据
	 */
	public static ArrayList<String> randFromArray(ArrayList<String> source, int cnt) {
		if (cnt >= source.size()) {
			return source;
		}
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < cnt; i++) {
			int v = rand(0, source.size() - 1);
			res.add(source.get(v));
			source.remove(v);
		}
		System.out.println(res.toString());

		return res;
	}

	public static void main(String args[]) {
		ArrayList<String> map = new ArrayList<String>();
		map.add("a");
		map.add("b");
		map.add("c");
		map.add("d");
		map.add("e");
		map.add("f");
		map.add("g");
		map.add("h");
		map.add("i");
		map.add("j");
		map.add("k");
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);
		randFromArray(map, 3);

	}
}
