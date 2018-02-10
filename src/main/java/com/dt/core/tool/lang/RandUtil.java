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
	 * 从source中获取cnt个数,keep是否保存source数据不变
	 */
	public static ArrayList<String> randDataFromList(ArrayList<String> source, int cnt, boolean keep) {
		ArrayList<String> s2 = new ArrayList<String>();
		if (keep) {
			s2.addAll(source);
		} else {
			s2 = source;
		}
		if (source == null || cnt >= source.size() || source.size() == 0) {
			return source;
		}
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < cnt; i++) {
			int v = rand(0, s2.size() - 1);
			res.add(s2.get(v));
			s2.remove(v);
		}
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
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);
		randDataFromList(map, 3, false);

	}
}
