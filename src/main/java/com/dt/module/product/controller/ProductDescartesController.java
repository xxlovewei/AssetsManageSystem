package com.dt.module.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.ResData;

/**
 * 循环和递归两种方式实现未知维度集合的笛卡尔积
 */
@Controller
@RequestMapping("/api")
public class ProductDescartesController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(ProductDescartesController.class);

	/**
	 * 递归实现dimValue中的笛卡尔积，结果放在result中
	 * @param dimValue 原始数据
	 * @param result 结果数据
	 * @param layer dimValue的层数
	 * @param curList 每次笛卡尔积的结果
	 */
	private static void recursive(List<List<String>> dimValue, List<List<String>> result, int layer,
			List<String> curList) {
		if (layer < dimValue.size() - 1) {
			if (dimValue.get(layer).size() == 0) {
				recursive(dimValue, result, layer + 1, curList);
			} else {
				for (int i = 0; i < dimValue.get(layer).size(); i++) {
					List<String> list = new ArrayList<String>(curList);
					list.add(dimValue.get(layer).get(i));
					recursive(dimValue, result, layer + 1, list);
				}
			}
		} else if (layer == dimValue.size() - 1) {
			if (dimValue.get(layer).size() == 0) {
				result.add(curList);
			} else {
				for (int i = 0; i < dimValue.get(layer).size(); i++) {
					List<String> list = new ArrayList<String>(curList);
					list.add(dimValue.get(layer).get(i));
					result.add(list);
				}
			}
		}
	}
	/**
	 * 循环实现dimValue中的笛卡尔积，结果放在result中
	 * @param dimValue 原始数据
	 * @param result 结果数据
	 */
	private static void circulate(List<List<String>> dimValue, List<List<String>> result) {
		int total = 1;
		for (List<String> list : dimValue) {
			total *= list.size();
		}
		String[] myResult = new String[total];
		int itemLoopNum = 1;
		int loopPerItem = 1;
		int now = 1;
		for (List<String> list : dimValue) {
			now *= list.size();
			int index = 0;
			int currentSize = list.size();
			itemLoopNum = total / now;
			loopPerItem = total / (itemLoopNum * currentSize);
			int myIndex = 0;
			for (String string : list) {
				_log.info(string);
				for (int i = 0; i < loopPerItem; i++) {
					if (myIndex == list.size()) {
						myIndex = 0;
					}
					for (int j = 0; j < itemLoopNum; j++) {
						myResult[index] = (myResult[index] == null ? "" : myResult[index] + ",") + list.get(myIndex);
						index++;
					}
					myIndex++;
				}
			}
		}
		List<String> stringResult = Arrays.asList(myResult);
		for (String string : stringResult) {
			String[] stringArray = string.split(",");
			result.add(Arrays.asList(stringArray));
		}
	}
	/**
	 * 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		List<String> list2 = new ArrayList<String>();
		list2.add("a");
		list2.add("b");
		List<String> list3 = new ArrayList<String>();
		list3.add("3");
		list3.add("4");
		list3.add("5");
		List<String> list4 = new ArrayList<String>();
		list4.add("c");
		list4.add("d");
		list4.add("e");
		List<List<String>> dimValue = new ArrayList<List<String>>();
		dimValue.add(list1);
		dimValue.add(list2);
		dimValue.add(list3);
		dimValue.add(list4);
		List<List<String>> recursiveResult = new ArrayList<List<String>>();
		// 递归实现笛卡尔积
		recursive(dimValue, recursiveResult, 0, new ArrayList<String>());
		_log.info("递归实现笛卡尔乘积: 共 " + recursiveResult.size() + " 个结果");
		for (List<String> list : recursiveResult) {
			for (String string : list) {
				_log.info(string + " ");
			}
		}
		List<List<String>> circulateResult = new ArrayList<List<String>>();
		circulate(dimValue, circulateResult);
		_log.info("循环实现笛卡尔乘积: 共 " + circulateResult.size() + " 个结果");
		for (List<String> list : circulateResult) {
			for (String string : list) {
				_log.info(string + " ");
			}
		}
	}
	@Res
	@Acl
	@RequestMapping("/product/prodDescartes.do")
	// 获取产品属性
	public ResData prodDescartes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String data = request.getParameter("data");
		String spu = request.getParameter("spu");
		// 格式[[1,2,3],[1,3,4]]
		JSONArray data_arr = JSONArray.parseArray(data);
		if (data_arr.size() == 0) {
			return ResData.FAILURE("数据不符合要求");
		}
		if (data_arr.size() >= 5) {
			return ResData.FAILURE("数据组合数过多,请减少组合数");
		}
		List<List<String>> dimValue = new ArrayList<List<String>>();
		for (int i = 0; i < data_arr.size(); i++) {
			List<String> list = new ArrayList<String>();
			JSONArray value = data_arr.getJSONArray(i);
			for (int j = 0; j < value.size(); j++) {
				list.add(value.getString(j));
			}
			dimValue.add(list);
		}
		// 求结果
		List<List<String>> circulateResult = new ArrayList<List<String>>();
		circulate(dimValue, circulateResult);
		// 输出
		_log.info("循环实现笛卡尔乘积: 共 " + circulateResult.size() + " 个结果");
		JSONArray res = new JSONArray();
		for (List<String> list : circulateResult) {
			JSONObject item = new JSONObject();
			JSONArray e = new JSONArray();
			for (String string : list) {
				_log.info(string + " ");
				JSONObject v = new JSONObject();
				v.put("attr_set_id", string);
				e.add(v);
			}
			item.put("attr_set_ids", e);
			item.put("spu", spu);
			res.add(item);
		}
		return ResData.SUCCESS_OPER(res);
	}
}
