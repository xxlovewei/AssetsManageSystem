package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.db.DB;

@Controller
@RequestMapping("/api")
public class ProductMaintainControllerdel extends BaseController {
	@Autowired
	private DB db = null;

	// 获得产品主要内容
	private JSONObject methodQueryProduct(String spu) {
		JSONObject r = new JSONObject();
		Rcd rs = db.uniqueRecord("select * from dt_product where is_deleted='N' and  spu=?", spu);
		if (ToolUtil.isNotEmpty(r)) {
			r = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
		}
		return r;
	}

	// 获得产品主要内容
	// type:main,noraml,detail(手机端用),
	private JSONObject methodQueryProductImage(String spu) {
		JSONObject r = new JSONObject();
		String sql = "select * from dt_product_pic where spu=? order by type,od";
		RcdSet rs = db.query(sql, spu);
		JSONArray main = new JSONArray();
		JSONArray normal = new JSONArray();
		JSONArray detail = new JSONArray();
		for (int i = 0; i < rs.size(); i++) {
			if (rs.getRcd(i).getString("type").equals("main")) {
				main.add(rs.getRcd(i).toJsonObject());
			} else if (rs.getRcd(i).getString("type").equals("normal")) {
				normal.add(rs.getRcd(i).toJsonObject());
			} else if (rs.getRcd(i).getString("type").equals("detail")) {
				detail.add(rs.getRcd(i).toJsonObject());
			}
		}
		r.put("main", main);
		r.put("normal", normal);
		r.put("detail", detail);
		return r;
	}

	private JSONArray methodQueryProductSpec(String spu) {

		JSONArray res = new JSONArray();
		String sepcsql = "select a.*,decode(a.status,'enable','启用','disabled','停用','未知') status_name from  dt_product_specgroup a ,dt_product b where a.spu=b.spu and a.spu=?  and a.is_deleted='N' order by od";

		RcdSet rs = db.query(sepcsql, spu);
		for (int i = 0; i < rs.size(); i++) {
			JSONObject e = new JSONObject();
			e = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.getRcd(i).toJsonObject());

			String listsql = "select * from dt_product_specgroup_item where group_id=? and is_deleted='N' order by od";
			e.put("SPECS", db.query(listsql, rs.getRcd(i).getString("group_id")).toJsonArrayWithJsonObject());
			res.add(e);
		}
		return res;

	}

	private JSONArray methodQueryProductSku(String spu) {

		String skusql = "with prod_sepc as " + "(select "
				+ "LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 num, "
				+ "decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , "
				+ "1,sku_uuid,substr(sku_uuid,1,instr(sku_uuid,',') -1)  ) level1, "
				+ "decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , " + "1,'-1', "
				+ "2 , substr(sku_uuid,instr(sku_uuid,',',1,1)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,1)  )  , "
				+ "substr(sku_uuid,instr(sku_uuid,',',1,1)+1 , instr(sku_uuid,',',1,2)-instr(sku_uuid,',',1,1)-1  ) "
				+ ") level2, " + "decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , " + "1,'-1', "
				+ "2,'-1', "
				+ "3 , substr(sku_uuid,instr(sku_uuid,',',1,2)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,2)  )  , "
				+ "substr(sku_uuid,instr(sku_uuid,',',1,2)+1 , instr(sku_uuid,',',1,3)-instr(sku_uuid,',',1,2)-1  ) "
				+ ") level3, " + "decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , " + "1,'-1', "
				+ "2,'-1', " + "3,'-1', "
				+ "4 , substr(sku_uuid,instr(sku_uuid,',',1,3)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,3)  )  , "
				+ "substr(sku_uuid,instr(sku_uuid,',',1,3)+1 , instr(sku_uuid,',',1,4)-instr(sku_uuid,',',1,3)-1  ) "
				+ ") level4, " + "decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , " + "1,'-1', "
				+ "2,'-1', " + "3,'-1', " + "4,'-1', "
				+ "5 , substr(sku_uuid,instr(sku_uuid,',',1,4)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,4)  )  , "
				+ "substr(sku_uuid,instr(sku_uuid,',',1,4)+1 , instr(sku_uuid,',',1,5)-instr(sku_uuid,',',1,4)-1  ) "
				+ ") level5, " + "sku.* " + "from DT_PRODUCT_SKU sku where IS_DELETED='N' and spu=? " + ") " + "select "
				+ "(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level1) level1_name, "
				+ "(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level2) level2_name, "
				+ "(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level3) level3_name, "
				+ "(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level4) level4_name, "
				+ "(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level5) level5_name, " + "a.* "
				+ "from prod_sepc a";
		RcdSet rs = db.query(skusql, spu);

		return ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());

	}

	@RequestMapping("/prod/queryBySpu.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "根据spu获取产品")
	public R queryBySpu(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject res = new JSONObject();

		String spu = request.getParameter("spu");

		if (spu == null) {
			return R.FAILURE("参数错误");
		}

		// 获取产品主要内容
		res = methodQueryProduct(spu);
		if (!res.containsKey("SPU")) {
			return R.FAILURE("不存在此产品");
		}

		// 获取产品主要图片
		res.put("IMAGES", methodQueryProductImage(spu));

		// 获取产品规格
		res.put("SPECS", methodQueryProductSpec(spu));

		// 获取所有产品规格组合,SKU组数据
		res.put("SKUS", methodQueryProductSku(spu));

		return R.SUCCESS("成功获取", res);
	}

	@RequestMapping("/prod/queryBySku.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "根据Sku获取产品")
	public R queryBySku(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return R.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return R.SUCCESS_OPER();

	}

	@RequestMapping("/prod/prodAdd.do")
	@ResponseBody
	@Acl(info = "添加产品")
	public R prodAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return R.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return R.SUCCESS_OPER();

	}

	@RequestMapping("/prod/prodDelete.do")
	@ResponseBody
	@Acl(info = "删除产品")
	public R prodDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return R.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return R.SUCCESS_OPER();

	}

	@RequestMapping("/prod/produUpdate.do")
	@ResponseBody
	@Acl(info = "更新产品")
	public R produUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return R.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return R.SUCCESS_OPER();

	}

}
