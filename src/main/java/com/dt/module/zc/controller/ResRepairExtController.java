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
import com.dt.module.zc.entity.ResRepair;
import com.dt.module.zc.entity.ResRepairFile;
import com.dt.module.zc.entity.ResRepairItem;
import com.dt.module.zc.service.IResRepairFileService;
import com.dt.module.zc.service.IResRepairItemService;
import com.dt.module.zc.service.IResRepairService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-04-19
 */
@Controller
@RequestMapping("/api/zc/resRepair/ext")
public class ResRepairExtController extends BaseController {

	@Autowired
	IResRepairFileService ResRepairFileServiceImpl;


	@Autowired
	IResService ResServiceImpl;

	@Autowired
	IResRepairItemService ResRepairItemServiceImpl;


	@Autowired
	IResRepairService ResRepairServiceImpl;

	@Autowired
	ZcService zcService;

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(ResRepair entity,String items,String files) {
		System.out.println(entity.toString());
		String status=entity.getFstatus();
		String id=entity.getId();

		JSONArray arr=JSONArray.parseArray(items);
		if(ToolUtil.isNotEmpty(id)){
			//维护单已完成，不允许修改
			ResRepair dbobj=ResRepairServiceImpl.getById(entity.getId());
			if(ZcCommonService.BX_STATUS_FINSH.equals(dbobj.getFstatus())){
				return R.FAILURE("当前状态不允许更新");
			}
			if(ZcCommonService.BX_STATUS_FINSH.equals(status)){
				//更新资产状态，完成单据
				UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
				ups.inSql("id","select resid from res_repair_item where dr='0' and repairid='"+id+"'");
				ups.setSql("recycle=prerecycle");
				ResServiceImpl.update(ups);
			}
			ResRepairServiceImpl.saveOrUpdate(entity);
		}else{
			//新增单据
			ArrayList<ResRepairItem> cols=new ArrayList<ResRepairItem>();
			String uuid=zcService.createUuid(ZcCommonService.UUID_BX);
			entity.setFuuid(uuid);
			ResRepairServiceImpl.saveOrUpdate(entity);
			QueryWrapper<ResRepair> ew = new QueryWrapper<ResRepair>();
			ew.and(i -> i.eq("fuuid",uuid));
			ResRepair dbobj=ResRepairServiceImpl.getOne(ew);
			id=dbobj.getId();
			for(int i=0;i<arr.size();i++){
				ResRepairItem e=new ResRepairItem();
				e.setRepairid(id);
				e.setResid(arr.getJSONObject(i).getString("id"));
				cols.add(e);
			}
			ResRepairItemServiceImpl.saveOrUpdateBatch(cols);
			UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
			ups.inSql("id","select resid from res_repair_item where dr='0' and repairid='"+id+"'");
			ups.setSql("prerecycle=recycle");
			ups.set("recycle",ZcCommonService.RECYCLE_REPAIR);
			ResServiceImpl.update(ups);

		}

		//删除图片
		QueryWrapper<ResRepairFile> fq = new QueryWrapper<ResRepairFile>();
		String finalId = id;
		fq.and(i -> i.eq("repiarid", finalId));
		ResRepairFileServiceImpl.remove(fq);
		//处理图片
		if(ToolUtil.isNotEmpty(files)){
			ArrayList<ResRepairFile> flist=new ArrayList<ResRepairFile>();
			String[] arrfiles=files.split("#");
			for(int i=0;i<arrfiles.length;i++){
				ResRepairFile e=new ResRepairFile();
				e.setRepiarid(id);
				e.setFileid(arrfiles[i]);
				if(arrfiles[i].length()>6){
					flist.add(e);
				}
			}
			if(flist.size()>0){
				ResRepairFileServiceImpl.saveBatch(flist);
			}
		}

		return R.SUCCESS_OPER();

	}


	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {

		JSONObject res;
		ResRepair bill=ResRepairServiceImpl.getById(id);
		res=JSONObject.parseObject(JSON.toJSONString(bill, SerializerFeature.WriteDateUseDateFormat));
		QueryWrapper<ResRepairFile> ew = new QueryWrapper<ResRepairFile>();
		ew.and(i -> i.eq("repiarid", id));
		List<ResRepairFile> list=ResRepairFileServiceImpl.list(ew);
		res.put("files",JSONArray.parseArray(JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect)));
		String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t,res_repair_item b where  t.id=b.resid and b.dr='0' and b.repairid='"+id+"'";
		res.put("items",db.query(sql).toJsonArrayWithJsonObject());
		return R.SUCCESS_OPER(res);
	}


	@ResponseBody
	@Acl(info = "根据Id删除", value = Acl.ACL_USER)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		ResRepair obj=ResRepairServiceImpl.getById(id);
		if(ZcCommonService.BX_STATUS_WAIT.equals(obj.getFstatus())){
			return R.FAILURE("当前状态不允许删除");
		}else{
			return R.SUCCESS_OPER(ResRepairServiceImpl.removeById(id));
		}

	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		QueryWrapper<ResRepair> ew = new QueryWrapper<ResRepair>();
		ew.orderByDesc("create_time");
		return R.SUCCESS_OPER(ResRepairServiceImpl.list(ew));

	}


	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/finish.do")
	public R finish(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		ResRepair obj=ResRepairServiceImpl.getById(id);
		if(ZcCommonService.BX_STATUS_FINSH.equals(obj.getFstatus())){
			return R.FAILURE("当前状态不允许变更");
		}else{
			//修改单据
			ResRepair e=new ResRepair();
			e.setId(id);
			e.setFstatus(ZcCommonService.BX_STATUS_FINSH);
			ResRepairServiceImpl.saveOrUpdate(e);

			//更新资产状态，完成单据
			UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
			ups.inSql("id","select resid from res_repair_item where dr='0' and repairid='"+id+"'");
			ups.setSql("recycle=prerecycle");
			ResServiceImpl.update(ups);

			//修改资产
			return R.SUCCESS_OPER();

		}

	}






}

