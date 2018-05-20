package com.dt.module.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2018年5月20日 上午7:57:51
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ProdItemController extends BaseShopController {
	public static String ITME_TYPE_OFFLINE = "offine";// 下架
	public static String ITME_TYPE_ONLINE = "online";// 出售中
	public static String ITME_TYPE_FINISH = "finish";// 已出售

	//
	// 图片提早已经上传
	// {
	// prod_id:1212,
	// data:[{code:1,pic_id:"adfa",sdate:"2012-01-01"}]
	// }
	// 添加产品数据无权限,只能添加到自己到用户下
	@RequestMapping("/mshop/AddProdItem.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R addProdItems() {

		String user_id = getShopUserId();
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE("用户识别失败.");
		}
		// 判断用户是否有手机号,没有则需要补充

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String prod_id = ps.getString("prod_id");
		String data = ps.getString("data");
		JSONArray dataarr = JSONArray.parseArray(data);

		if (ToolUtil.isOneEmpty(prod_id, data)) {
			return R.FAILURE_NO_DATA();
		}
		if (dataarr.size() == 0) {
			return R.FAILURE_NO_DATA();
		}
		List<SQL> sqls = new ArrayList<SQL>();
		for (int i = 0; i < dataarr.size(); i++) {
			JSONObject e = dataarr.getJSONObject(i);
			Insert me = new Insert("bus_prod_item");
			me.setIf("dr", 0);
			me.setIf("user_id", user_id);
			me.setIf("name", e.getString("name"));
			me.setIf("code", e.getString("code"));
			me.setIf("title", e.getString("title"));
			me.setIf("pic_id", e.getString("pic_id"));
			me.setIf("status", ITME_TYPE_ONLINE);
			me.setIf("prod_id", e.getString("name"));
			me.setIf("mark", e.getString("mark"));
			me.setIf("pwd", e.getString("pwd"));
			sqls.add(me);
		}
		db.executeSQLList(sqls);
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/mshop/queryProdItemsByProdId.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProdItem(String id) {
		Update me = new Update("bus_prod");
		me.set("dr", 1);
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

}
