package com.dt.module.zc.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.zc.entity.ResChangeItem;
import com.dt.module.zc.entity.ResInventoryUser;
import com.dt.module.zc.entity.ResRepair;
import com.dt.module.zc.service.IResInventoryUserService;
import com.dt.module.zc.service.impl.ResInventoryService;
import com.dt.module.zc.service.impl.ResInventoryUserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.zc.entity.ResInventory;
import com.dt.module.zc.service.IResInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.tool.util.DbUtil;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.tool.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import com.dt.core.common.base.BaseController;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-05-14
 */
@Controller
@RequestMapping("/api/zc/resInventory/ext")
public class ResInventoryExtController extends BaseController {


	@Autowired
	IResInventoryService ResInventoryServiceImpl;

	@Autowired
	IResInventoryUserService ResInventoryUserServiceImpl;



	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {

		ResInventory r=ResInventoryServiceImpl.getById(id);
		JSONObject res = JSONObject.parseObject(JSON.toJSONString(r, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect));

		//
		return R.SUCCESS_OPER(res);
	}


	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		QueryWrapper<ResInventory> ew = new QueryWrapper<ResInventory>();
		ew.orderByDesc("create_time");
		return R.SUCCESS_OPER(ResInventoryServiceImpl.list(ew));

	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(ResInventory entity) {

		ResInventoryServiceImpl.saveOrUpdate(entity);
		QueryWrapper<ResInventory> ew = new QueryWrapper<ResInventory>();
		ew.and(i -> i.eq("batchid", entity.getBatchid()));
		ResInventory obj=ResInventoryServiceImpl.getOne(ew);


		//处理用户
		QueryWrapper<ResInventoryUser>  userqw = new QueryWrapper<ResInventoryUser>();
		userqw.and(i -> i.eq("pdid",obj.getId()));
		ResInventoryUserServiceImpl.remove(userqw);
		String pduserstr=entity.getPduserdata();
		ArrayList<ResInventoryUser> items=new ArrayList<ResInventoryUser>();
		if(ToolUtil.isNotEmpty(pduserstr)){
			JSONArray pduserarr=JSONArray.parseArray(pduserstr);
			for(int i=0;i<pduserarr.size();i++){
				ResInventoryUser e=new ResInventoryUser();
				e.setUserid(pduserarr.getJSONObject(i).getString("user_id"));
				e.setUsername(pduserarr.getJSONObject(i).getString("name"));
				e.setPdid(obj.getId());
				items.add(e);
			}
			ResInventoryUserServiceImpl.saveBatch(items);
		}

		//处理盘点单明细

		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/cancel.do")
	public R cancel(String id) {
		ResInventory item=ResInventoryServiceImpl.getById(id);
		if(item==null){
			return R.FAILURE_NO_DATA();
		}
		if(ResInventoryService.INVENTORY_STATAUS_START.equals(item.getStatus()) ||
		ResInventoryService.INVENTORY_STATAUS_WAIT.equals(item.getStatus())){
		UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
		uw.set("status", ResInventoryService.INVENTORY_STATAUS_CANCEL);
		uw.eq("id", id);
		ResInventoryServiceImpl.update(uw);
		}else{
			return R.FAILURE("当前状态不允许取消盘点单");
		}
		return R.SUCCESS_OPER();
	}


	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(String id) {
		ResInventory item=ResInventoryServiceImpl.getById(id);
		if(item==null){
			return R.FAILURE_NO_DATA();
		}
		if(ResInventoryService.INVENTORY_STATAUS_START.equals(item.getStatus()) ||
				ResInventoryService.INVENTORY_STATAUS_WAIT.equals(item.getStatus())){
			ResInventoryServiceImpl.removeById(id);
		}else{
			return R.FAILURE("当前状态不允许删除盘点单");
		}
		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/assignusers.do")
	public R assignusers(String id,String pduserdata,String pduserlist) {

		ResInventory obj=ResInventoryServiceImpl.getById(id);
		if(obj==null){
			return R.FAILURE_NO_DATA();
		}
		UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
		uw.set("pduserlist",pduserlist);
		uw.set("pduserdata",pduserdata);
		uw.eq("id", id);
		ResInventoryServiceImpl.update(uw);


		QueryWrapper<ResInventoryUser>  userqw = new QueryWrapper<ResInventoryUser>();
		userqw.and(i -> i.eq("pdid",id));
		ResInventoryUserServiceImpl.remove(userqw);
		String pduserstr=pduserdata;
		ArrayList<ResInventoryUser> items=new ArrayList<ResInventoryUser>();
		if(ToolUtil.isNotEmpty(pduserstr)){
			JSONArray pduserarr=JSONArray.parseArray(pduserstr);
			for(int i=0;i<pduserarr.size();i++){
				ResInventoryUser e=new ResInventoryUser();
				e.setUserid(pduserarr.getJSONObject(i).getString("user_id"));
				e.setUsername(pduserarr.getJSONObject(i).getString("name"));
				e.setPdid(obj.getId());
				items.add(e);
			}
			ResInventoryUserServiceImpl.saveBatch(items);
		}
		return R.SUCCESS_OPER( );
	}


}

