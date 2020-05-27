package com.dt.module.zc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import com.dt.module.zc.entity.ResInoutItem;
import com.dt.module.zc.service.IResInoutItemService;
import com.dt.module.zc.service.IResInoutService;
import com.dt.module.zc.service.impl.ResInoutExtService;
import com.dt.module.zc.service.impl.ZcCommonService;
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
	ResInoutExtService resInoutExtService;

	@Autowired
	IResService ResServiceImpl;

	@Autowired
	ZcService zcService;

	@Autowired
	IResInoutItemService ResInoutItemServiceImpl;

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insert.do")
	public R insert(ResInout entity,String busitimestr,String items) throws ParseException {
		ArrayList<Res> cols=new ArrayList<Res>();
		ArrayList<ResInoutItem> cols2=new ArrayList<ResInoutItem>();
		String uuid=zcService.createUuid(entity.getAction());
		entity.setUuid(uuid);
		entity.setStatus("none");
		if(ToolUtil.isEmpty(entity.getOperuserid())){
			entity.setOperuserid(this.getUserId());
		}
		if(ToolUtil.isEmpty(entity.getOperusername())){
			entity.setOperusername(this.getUserName());
		}
		JSONArray items_arr=JSONArray.parseArray(items);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(busitimestr);
		entity.setBusidate(date);
		if(ZcCommonService.UUID_HCRK.equals(entity.getAction())){

			for(int i=0;i<items_arr.size();i++){
				Res e=new Res();
				e.setUuid(uuid);
				e.setBatchno(items_arr.getJSONObject(i).getString("batchno"));
				e.setCrkstatus("none");
				e.setRecycle(ZcCommonService.RECYCLE_IDLE);
				e.setZcCnt(new BigDecimal(items_arr.getJSONObject(i).getString("zc_cnt")));
				e.setBuyPrice(new BigDecimal(items_arr.getJSONObject(i).getString("buy_price")));
				e.setLoc(items_arr.getJSONObject(i).getString("loc"));
				e.setWarehouse(items_arr.getJSONObject(i).getString("warehouse"));
				e.setSupplier(items_arr.getJSONObject(i).getString("supplier"));
				e.setClassId(items_arr.getJSONObject(i).getString("class_id"));
				e.setZcCategory(items_arr.getJSONObject(i).getString("zc_category"));
				e.setBelongCompanyId(items_arr.getJSONObject(i).getString("belong_company_id"));
				cols.add(e);

				ResInoutItem e2=new ResInoutItem();
				e2.setUuid(uuid);
				e2.setBatchno(items_arr.getJSONObject(i).getString("batchno"));
				e2.setCrkstatus("none");
				e2.setZcCnt(new BigDecimal(items_arr.getJSONObject(i).getString("zc_cnt")));
				e2.setBuyPrice(new BigDecimal(items_arr.getJSONObject(i).getString("buy_price")));
				e2.setLoc(items_arr.getJSONObject(i).getString("loc"));
				e2.setSupplier(items_arr.getJSONObject(i).getString("supplier"));
				e2.setWarehouse(items_arr.getJSONObject(i).getString("warehouse"));
				e2.setClassId(items_arr.getJSONObject(i).getString("class_id"));
				e2.setZcCategory(items_arr.getJSONObject(i).getString("zc_category"));
				e2.setBelongCompanyId(items_arr.getJSONObject(i).getString("belong_company_id"));
				e2.setBuyTime(date);
				cols2.add(e2);
			}


		}else if (ZcCommonService.UUID_HCCK.equals(entity.getAction())){
			for(int i=0;i<items_arr.size();i++) {
				UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
				ups.setSql("zc_cnt=zc_cnt-"+items_arr.getJSONObject(i).getString("zc_cnt"));
				ups.eq("id", items_arr.getJSONObject(i).getString("id"));
				ResServiceImpl.update(ups);

				ResInoutItem e2 = new ResInoutItem();
				e2.setUuid(uuid);
			 	e2.setResid(items_arr.getJSONObject(i).getString("id"));
				e2.setBatchno(items_arr.getJSONObject(i).getString("batchno"));
				e2.setCrkstatus("none");
				e2.setZcCnt(new BigDecimal(items_arr.getJSONObject(i).getString("zc_cnt")));
				e2.setBuyPrice(new BigDecimal(items_arr.getJSONObject(i).getString("buy_price")));
				e2.setLoc(items_arr.getJSONObject(i).getString("loc"));
				e2.setSupplier(items_arr.getJSONObject(i).getString("supplier"));
				e2.setWarehouse(items_arr.getJSONObject(i).getString("warehouse"));
				e2.setClassId(items_arr.getJSONObject(i).getString("class_id"));
				e2.setZcCategory(items_arr.getJSONObject(i).getString("zc_category"));
				e2.setBelongCompanyId(items_arr.getJSONObject(i).getString("belong_company_id"));
				e2.setBuyTime(date);
				cols2.add(e2);
			}

		}

		entity.setCnt(new BigDecimal(items_arr.size()));
		ResInoutServiceImpl.saveOrUpdate(entity);
		if(cols.size()>0){
			ResServiceImpl.saveOrUpdateBatch(cols);
		}
		if(cols2.size()>0){
			ResInoutItemServiceImpl.saveOrUpdateBatch(cols2);
		}

		return R.SUCCESS_OPER();
	}


	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectHcTj.do")
	public R selectHcTj(String loc) {
		return resInoutExtService.selectHcTj(loc);
	}


	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList(String type,String action) {
		if(ZcCommonService.UUID_HCCK.equals(action)){
			return resInoutExtService.selectHcCk();
		}else if (ZcCommonService.UUID_HCRK.equals(action)){
			QueryWrapper<ResInout> qw = new QueryWrapper<ResInout>();
			qw.and(i -> i.eq("type", type));
			qw.and(i -> i.eq("action", action));
			qw.orderByDesc("create_time");
			return R.SUCCESS_OPER(ResInoutServiceImpl.list(qw));
		}

		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(String id) {
		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectSafetyStore.do")
	public R selectSafetyStore() {
		return resInoutExtService.selectSafetyStore();
	}




	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectHcInDataById.do")
	public R selectHcInDataById(String id) {
		return resInoutExtService.selectHcInDataById(id);
	}


	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectHcOutDataById.do")
	public R selectHcOutDataById(String id) {
		return resInoutExtService.selectHcOutDataById(id);
	}


}

