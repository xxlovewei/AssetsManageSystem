package com.dt.module.zc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.zc.entity.ResChangeItem;
import com.dt.module.zc.entity.ResInout;
import com.dt.module.zc.service.IResInoutService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-05-25
 */
@Controller
@RequestMapping("/api/zc/resInout/ext")
public class ResInoutExtController extends BaseController {


	@Autowired
	IResInoutService ResInoutServiceImpl;



	@Autowired
	IResService ResServiceImpl;

	@Autowired
	ZcService zcService;


	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insert.do")
	public R insert(ResInout entity,String items) throws ParseException {

		String uuid=zcService.createUuid(entity.getAction());
		entity.setUuid(uuid);
		entity.setOperuserid(this.getUserId());
		entity.setOperusername(this.getUserName());
		System.out.println(entity);

		JSONArray items_arr=JSONArray.parseArray(items);
		ArrayList<Res> cols=new ArrayList<Res>();
		for(int i=0;i<items_arr.size();i++){
			Res e=new Res();
			e.setUuid(uuid);
			e.setBatchno(items_arr.getJSONObject(i).getString("batchno"));
			e.setZcCnt(new BigDecimal(items_arr.getJSONObject(i).getString("zc_cnt")));
			e.setBuyPrice(new BigDecimal(items_arr.getJSONObject(i).getString("buy_price")));
			e.setLoc(items_arr.getJSONObject(i).getString("loc"));
			e.setWarehouse(items_arr.getJSONObject(i).getString("warehouse"));
			e.setSupplier(items_arr.getJSONObject(i).getString("supplier"));
			e.setClassId(items_arr.getJSONObject(i).getString("class_id"));
			e.setZcCategory(items_arr.getJSONObject(i).getString("zc_category"));
			e.setUsedCompanyId(items_arr.getJSONObject(i).getString("used_company_id"));
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(items_arr.getJSONObject(i).getString("buy_timestr"));
			e.setBuyTime(date);
			cols.add(e);
		}
		ResInoutServiceImpl.saveOrUpdate(entity);
		ResServiceImpl.saveOrUpdateBatch(cols);
		return R.SUCCESS_OPER();
	}



	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList(String type) {
		QueryWrapper<ResInout> qw = new QueryWrapper<ResInout>();
		qw.and(i -> i.eq("type", type));
		qw.orderByDesc("create_time");
		return R.SUCCESS_OPER(ResInoutServiceImpl.list(qw));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(String id) {

		return R.SUCCESS_OPER();
	}


}

