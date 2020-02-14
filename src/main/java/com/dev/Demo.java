package com.dev;

import com.alibaba.fastjson.JSONArray;

import cn.hutool.core.util.IdUtil;

/** 
 * @author: algernonking
 * @date: Feb 11, 2020 7:55:08 AM 
 * @Description: TODO 
 */
public class Demo {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String simpleUUID = IdUtil.simpleUUID();
		String btns = "[{\"id\":\"search\",\"name\":\"搜索\"},{\"id\":\"select\",\"name\":\"查询\"},{\"id\":\"insert\",\"name\":\"新增\"},{\"id\":\"update\",\"name\":\"更新\"},{\"id\":\"remove\",\"name\":\"删除\"},{\"id\":\"item_select\",\"name\":\"项目查询\"},{\"id\":\"item_insert\",\"name\":\"项目新增\"},{\"id\":\"item_update\",\"name\":\"项目更新\"},{\"id\":\"item_remove\",\"name\":\"项目删除\"},{\"id\":\"cancel\",\"name\":\"取消\"},{\"id\":\"save\",\"name\":\"保存\"},{\"id\":\"submit\",\"name\":\"提交\"},{\"id\":\"exportfile\",\"name\":\"导出\"},{\"id\":\"importfile\",\"name\":\"导入\"},{\"id\":\"uploadfile\",\"name\":\"上传\"},{\"id\":\"downfile\",\"name\":\"下载\"},{\"id\":\"act1\",\"name\":\"动作1\"},{\"id\":\"act2\",\"name\":\"动作2\"},{\"id\":\"act3\",\"name\":\"动作3\"},{\"id\":\"act4\",\"name\":\"动作4\"},{\"id\":\"act5\",\"name\":\"动作5\"},{\"id\":\"act6\",\"name\":\"动作6\"},{\"id\":\"act7\",\"name\":\"动作7\"},{\"id\":\"act8\",\"name\":\"动作8\"}]"
			;System.out.println(btns);
		JSONArray btns_arr = JSONArray.parseArray(btns);
		System.out.println(btns_arr.size());
	}

}

