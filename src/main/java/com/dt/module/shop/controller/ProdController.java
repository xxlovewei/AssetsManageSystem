package com.dt.module.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
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
public class ProdController extends BaseShopController {

	@RequestMapping("/mshop/demo.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER)
	public R demo() {
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/mshop/queryProdByXl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProdByXl(String xl, String status) {

		String sql = "select * from bus_prod where dr=0 and xl=?";
		// status暂时不用
		if (ToolUtil.isNotEmpty(status)) {
			sql = sql + " and status='" + status + "'";
		}
		RcdSet rs = db.query(sql, xl);
		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	/* 供前端买 */
	@RequestMapping("/mshop/queryProdByDlForSale.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProdByXlForSale(String xl) {

		return null;
		// return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	@RequestMapping("/mshop/queryProdById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProdById(String id) {
		Rcd rs = db.uniqueRecord("select * from bus_prod where id=?", id);
		return R.SUCCESS_OPER(rs.toJsonObject());
	}

	@RequestMapping("/mshop/queryProdByIdForSale.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProdByIdForSale(String id) {
		Rcd rs = db.uniqueRecord("select * from bus_prod where id=?", id);
		return R.SUCCESS_OPER(rs.toJsonObject());
	}

	@RequestMapping("/mshop/delProd.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY)
	public R delProd(String id) {
		Update me = new Update("bus_prod");
		me.set("dr", 1);
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/*
	 * uploadpic 1:需要上传二维码,0:不需要
	 */
	@RequestMapping("/mshop/saveProd.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY)
	public R saveProd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			Insert me = new Insert("bus_prod");
			me.set("id", db.getUUID());
			me.setIf("name", ps.getString("name"));
			me.setIf("user_id", ps.getString("user_id"));
			me.setIf("pic_id", ps.getString("pic_id"));
			me.setIf("title", ps.getString("title"));
			me.setIf("xl", ps.getString("xl"));
			me.setIf("dl", ps.getString("dl"));
			me.setIf("price", ps.getString("price"));
			me.setIf("sprice", ps.getString("sprice"));
			me.setIf("mark", ps.getString("mark"));
			me.setIf("status", ps.getString("status"));
			me.setIf("uploadpic", ps.getString("uploadpic", "0"));
			me.setIf("top", ps.getString("top"));
			me.set("dr", "0");
			db.execute(me);
		} else {
			Update me = new Update("bus_prod");
			me.setIf("name", ps.getString("name"));
			me.setIf("pic_id", ps.getString("pic_id"));
			me.setIf("uploadpic", ps.getString("uploadpic"));
			me.setIf("title", ps.getString("title"));
			me.setIf("xl", ps.getString("xl"));
			me.setIf("dl", ps.getString("dl"));
			me.setIf("price", ps.getString("price"));
			me.setIf("sprice", ps.getString("sprice"));
			me.setIf("mark", ps.getString("mark"));
			me.setIf("status", ps.getString("status"));
			me.setIf("top", ps.getString("top"));
			me.where().and("id=?", id);
			db.execute(me);
		}
		return R.SUCCESS_OPER();
	}

}
