package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.db.DB;

@Controller
@RequestMapping("/api")
public class ProductMaintainController extends BaseController{
	@Autowired
	private DB db = null;

	// 获得产品主要内容
	private JSONObject methodQueryProduct(String spu) {
		JSONObject r = new JSONObject();
		Rcd rs = db.uniqueRecord("select * from DT_PRODUCT where IS_DELETED='N' and  spu=?", spu);
		if (ToolUtil.isNotEmpty(r)) {
			r = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
		}
		return r;
	}

	// 获得产品主要内容
	// type:main,noraml,detail(手机端用),
	private JSONObject methodQueryProductImage(String spu) {
		JSONObject r = new JSONObject();
		String sql = "select * from  DT_PRODUCT_PIC where spu=? order by type,od";
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
		r.put("MAIN", main);
		r.put("NORMAL", normal);
		r.put("DETAIL", detail);
		return r;
	}

	private JSONArray methodQueryProductSpec(String spu) {
		
		JSONArray res=new JSONArray();
		String sepcsql="select a.*,decode(a.STATUS,'enable','启用','disabled','停用','未知') status_name FROM  DT_PRODUCT_SPECGROUP a ,DT_PRODUCT b where a.SPU=b.SPU and a.spu=?  and a.is_deleted='N' order by od" ;
		
		RcdSet rs=db.query(sepcsql,spu);
		for(int i=0;i<rs.size();i++){
			JSONObject e=new JSONObject();
			e=ConvertUtil.OtherJSONObjectToFastJSONObject(rs.getRcd(i).toJsonObject());
			
			String listsql="select * from DT_PRODUCT_SPECGROUP_ITEM where group_id=? and is_deleted='N' order by od";
			e.put("SPECS", db.query(listsql,rs.getRcd(i).getString("group_id")).toJsonArrayWithJsonObject() );
			res.add(e);
		}
		return res;
		
	}
	
	private JSONArray methodQueryProductSku(String spu) {
	
		String skusql="with prod_sepc as "+
		"(select "+
		"LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 num, "+
		"decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , "+
		"1,sku_uuid,substr(sku_uuid,1,instr(sku_uuid,',') -1)  ) level1, "+
		"decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , "+
		"1,'-1', "+
		"2 , substr(sku_uuid,instr(sku_uuid,',',1,1)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,1)  )  , "+
		"substr(sku_uuid,instr(sku_uuid,',',1,1)+1 , instr(sku_uuid,',',1,2)-instr(sku_uuid,',',1,1)-1  ) "+
		") level2, "+
		"decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , "+
		"1,'-1', "+
		"2,'-1', "+
		"3 , substr(sku_uuid,instr(sku_uuid,',',1,2)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,2)  )  , "+
		"substr(sku_uuid,instr(sku_uuid,',',1,2)+1 , instr(sku_uuid,',',1,3)-instr(sku_uuid,',',1,2)-1  ) "+
		") level3, "+
		"decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , "+
		"1,'-1', "+
		"2,'-1', "+
		"3,'-1', "+
		"4 , substr(sku_uuid,instr(sku_uuid,',',1,3)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,3)  )  , "+
		"substr(sku_uuid,instr(sku_uuid,',',1,3)+1 , instr(sku_uuid,',',1,4)-instr(sku_uuid,',',1,3)-1  ) "+
		") level4, "+
		"decode( LENGTH(sku_uuid) - LENGTH(REPLACE(sku_uuid,',',''))+1 , "+
		"1,'-1', "+
		"2,'-1', "+
		"3,'-1', "+
		"4,'-1', "+
		"5 , substr(sku_uuid,instr(sku_uuid,',',1,4)+1  ,  LENGTH(sku_uuid)-instr(sku_uuid,',',1,4)  )  , "+
		"substr(sku_uuid,instr(sku_uuid,',',1,4)+1 , instr(sku_uuid,',',1,5)-instr(sku_uuid,',',1,4)-1  ) "+
		") level5, "+
		"sku.* "+
		"from DT_PRODUCT_SKU sku where IS_DELETED='N' and spu=? "+
		") "+
		"select "+
		"(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level1) level1_name, "+
		"(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level2) level2_name, "+
		"(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level3) level3_name, "+
		"(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level4) level4_name, "+
		"(select spec_name from DT_PRODUCT_SPECGROUP_ITEM i where i.SPEC_ID=a.level5) level5_name, "+
		"a.* "+
		"from prod_sepc a";
		RcdSet rs=db.query(skusql,spu);
 
		return ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());
		
	}
	
	
	@RequestMapping("/prod/queryBySpu.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryBySpu(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject res = new JSONObject();

		String spu = request.getParameter("spu");

		if (spu == null) {
			return ResData.FAILURE("参数错误");
		}

		//获取产品主要内容
		res = methodQueryProduct(spu);
		if (!res.containsKey("SPU")) {
			return ResData.FAILURE("不存在此产品");
		}
		
		//获取产品主要图片
		res.put("IMAGES", methodQueryProductImage(spu));
		

		//获取产品规格
		res.put("SPECS", methodQueryProductSpec(spu));
		
		//获取所有产品规格组合,SKU组数据
		res.put("SKUS", methodQueryProductSku(spu));
		 
		return ResData.SUCCESS("成功获取", res);
	}

	@RequestMapping("/prod/queryBySku.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryBySku(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return ResData.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return ResData.SUCCESS_OPER();

	}

	@RequestMapping("/prod/prodAdd.do")
	@Res
	@Acl
	public ResData prodAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return ResData.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return ResData.SUCCESS_OPER();

	}

	@RequestMapping("/prod/prodDelete.do")
	@Res
	@Acl
	public ResData prodDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return ResData.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return ResData.SUCCESS_OPER();

	}

	@RequestMapping("/prod/produUpdate.do")
	@Res
	@Acl
	public ResData produUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (id == null) {
			return ResData.FAILURE("未绑定ID");
		}
		db.execute("delete from sys_dict_item where dict_item_id=?", id);
		return ResData.SUCCESS_OPER();

	}

}
