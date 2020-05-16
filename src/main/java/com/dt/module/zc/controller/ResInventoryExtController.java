package com.dt.module.zc.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.controller.FileUpDownController;
import com.dt.module.base.entity.SysFiles;
import com.dt.module.base.service.ISysFilesService;
import com.dt.module.cmdb.entity.ResEntity;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.zc.entity.*;
import com.dt.module.zc.service.IResInventoryItemSService;
import com.dt.module.zc.service.IResInventoryItemService;
import com.dt.module.zc.service.IResInventoryUserService;
import com.dt.module.zc.service.impl.ResInventoryImportService;
import com.dt.module.zc.service.impl.ResInventoryService;
import com.dt.module.zc.service.impl.ResInventoryUserServiceImpl;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	ResInventoryImportService resInventoryImportService;


	@Autowired
	ISysFilesService SysFilesServiceImpl;

	@Autowired
	IResInventoryService ResInventoryServiceImpl;

	@Autowired
	IResInventoryUserService ResInventoryUserServiceImpl;

	@Autowired
	IResInventoryItemService ResInventoryItemServiceImpl;

	@Autowired
	IResInventoryItemSService ResInventoryItemSServiceImpl;

	@Autowired
	ResInventoryService resInventoryService;


	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {

		ResInventory r=ResInventoryServiceImpl.getById(id);
		JSONObject res = JSONObject.parseObject(JSON.toJSONString(r, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect));

		String sql="select "+ZcCommonService.resSqlbody + " t.* from res_inventory_item t where dr=0 and pdid=?";
		res.put("items",db.query(sql,id).toJsonArrayWithJsonObject());
		return R.SUCCESS_OPER(res);
	}


	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/syncdata.do")
	public R syncdata(String id) {


		ResInventory ri=ResInventoryServiceImpl.getById(id);
		if(ri==null){
			return R.FAILURE_NO_DATA();
		}
		if(!ResInventoryService.INVENTORY_STATAUS_FINISH.equals(ri.getStatus())){
			return R.FAILURE("当前单据单状态错误,不能进行同步数据操作!");
		}
		if("1".equals(ri.getSyncstatus())){
			return R.FAILURE("数据已同步,不需要重复操作.");
		}

		UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
		uw.set("syncstatus","1");
		uw.eq("id", id);
		ResInventoryServiceImpl.update(uw);
		String sql1="update res a,res_inventory_item b\n" +
				"set a.class_id=b.class_id,\n" +
				"a.type=b.type,\n" +
				"a.uuid=b.uuid,\n" +
				"a.name=b.name,\n" +
				"a.zcsource=b.zcsource,\n" +
				"a.model=b.model,\n" +
				"a.sn=b.sn,\n" +
				"a.res_desc=b.res_desc,\n" +
				"a.brand=b.brand,\n" +
				"a.supplier=b.supplier,\n" +
				"a.recycle=b.recycle,\n" +
				"a.prerecycle=b.prerecycle,\n" +
				"a.buy_time=b.buy_time,\n" +
				"a.confdesc=b.confdesc,\n" +
				"a.loc=b.loc,\n" +
				"a.locdtl=b.locdtl,\n" +
				"a.belong_company_id=b.belong_company_id,\n" +
				"a.belong_part_id=b.belong_part_id,\n" +
				"a.used_company_id=b.used_company_id,\n" +
				"a.part_id=b.part_id,\n" +
				"a.used_userid=b.used_userid,\n" +
				"a.mgr_part_id=b.mgr_part_id,\n" +
				"a.buy_price=b.buy_price,\n" +
				"a.net_worth=b.net_worth,\n" +
			//	"a.zc_cnt=b.zc_cnt,\n" +
				"a.actionstatus=b.actionstatus,\n" +
				"a.wb=b.wb,\n" +
				"a.wb_auto=b.wb_auto,\n" +
				"a.wbsupplier=b.wbsupplier,\n" +
				"a.wbct=b.wbct,\n" +
				"a.status=b.status,\n" +
				"a.mark=b.mark\n" +
				"where a.id=b.resid\n";
		db.execute(sql1);
		return R.SUCCESS_OPER();

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
		R itemsR=resInventoryService.inventoryRange(entity);
		JSONArray arr=itemsR.queryDataToJSONArray();
		ArrayList<ResInventoryItem> itemlist=new ArrayList<ResInventoryItem>();
		ArrayList<ResInventoryItemS> itemslist=new ArrayList<ResInventoryItemS>();
		if(arr.size()>0){
			for(int i=0;i<arr.size();i++){
				ResInventoryItem e1=new ResInventoryItem();
				e1.setPdbatchid(obj.getBatchid());
				e1.setPdid(obj.getId());
				e1.setResid(arr.getJSONObject(i).getString("id"));
				e1.setPdstatus(ResInventoryService.INVENTORY_ITEM_STATAUS_WAIT);
				e1.setPdsyncneed(ResInventoryService.INVENTORY_ITEM_ACTION_NOSYNC);

				ResInventoryItemS e2=new ResInventoryItemS();
				e2.setPdbatchid(obj.getBatchid());
				e2.setPdid(obj.getId());
				e2.setResid(arr.getJSONObject(i).getString("id"));
				itemlist.add(e1);
				itemslist.add(e2);
			}
			//批量更新主要数据
			ResInventoryItemServiceImpl.saveBatch(itemlist);
			ResInventoryItemSServiceImpl.saveBatch(itemslist);
			//批量更新内容
			String sql1="update res_inventory_item a,res b\n" +
					"set a.zc_category=b.zc_category,\n" +
					"a.class_id=b.class_id,\n" +
					"a.type=b.type,\n" +
					"a.gj_dl=b.gj_dl,\n" +
					"a.gj_xl=b.gj_xl,\n" +
					"a.uuid=b.uuid,\n" +
					"a.name=b.name,\n" +
					"a.zcsource=b.zcsource,\n" +
					"a.model=b.model,\n" +
					"a.sn=b.sn,\n" +
					"a.version=b.version,\n" +
					"a.res_desc=b.res_desc,\n" +
					"a.brand=b.brand,\n" +
					"a.supplier=b.supplier,\n" +
					"a.recycle=b.recycle,\n" +
					"a.prerecycle=b.prerecycle,\n" +
					"a.env=b.env,\n" +
					"a.risk=b.risk,\n" +
					"a.buy_time=b.buy_time,\n" +
					"a.offline_time=b.offline_time,\n" +
					"a.online_time=b.online_time,\n" +
					"a.ip=b.ip,\n" +
					"a.rwm=b.rwm,\n" +
					"a.confdesc=b.confdesc,\n" +
					"a.loc=b.loc,\n" +
					"a.locshow=b.locshow,\n" +
					"a.locdtl=b.locdtl,\n" +
					"a.rack=b.rack,\n" +
					"a.frame=b.frame,\n" +
					"a.belong_company_id=b.belong_company_id,\n" +
					"a.belong_part_id=b.belong_part_id,\n" +
					"a.used_company_id=b.used_company_id,\n" +
					"a.part_id=b.part_id,\n" +
					"a.used_userid=b.used_userid,\n" +
					"a.mgr_part_id=b.mgr_part_id,\n" +
					"a.maintain_userid=b.maintain_userid,\n" +
					"a.headuserid=b.headuserid,\n" +
					"a.buy_price=b.buy_price,\n" +
					"a.net_worth=b.net_worth,\n" +
					"a.zc_cnt=b.zc_cnt,\n" +
					"a.actionstatus=b.actionstatus,\n" +
					"a.wb=b.wb,\n" +
					"a.wb_auto=b.wb_auto,\n" +
					"a.wbout_date=b.wbout_date,\n" +
					"a.wbsupplier=b.wbsupplier,\n" +
					"a.wbct=b.wbct,\n" +
					"a.status=b.status,\n" +
					"a.changestatus=b.changestatus,\n" +
					"a.importlabel=b.importlabel,\n" +
					"a.img=b.img,\n" +
					"a.attach=b.attach,\n" +
					"a.mark=b.mark,\n" +
					"a.changestate=b.changestate,\n" +
					"a.review_userid=b.review_userid,\n" +
					"a.review_date=b.review_date,\n" +
					"a.fs1=b.fs1,\n" +
					"a.fs2=b.fs2,\n" +
					"a.fs3=b.fs3,\n" +
					"a.fs4=b.fs4,\n" +
					"a.fs5=b.fs5,\n" +
					"a.fs6=b.fs6,\n" +
					"a.fs7=b.fs7,\n" +
					"a.fs8=b.fs8,\n" +
					"a.fs9=b.fs9,\n" +
					"a.fs10=b.fs10,\n" +
					"a.fs11=b.fs11,\n" +
					"a.fs12=b.fs12,\n" +
					"a.fs13=b.fs13,\n" +
					"a.fs14=b.fs14,\n" +
					"a.fs15=b.fs15,\n" +
					"a.fs16=b.fs16,\n" +
					"a.fs17=b.fs17,\n" +
					"a.fs18=b.fs18,\n" +
					"a.fs19=b.fs19,\n" +
					"a.fs20=b.fs20,\n" +
					"a.fi1=b.fi1,\n" +
					"a.fi2=b.fi2,\n" +
					"a.fi3=b.fi3,\n" +
					"a.fi4=b.fi4,\n" +
					"a.fi5=b.fi5,\n" +
					"a.fi6=b.fi6,\n" +
					"a.fi7=b.fi7,\n" +
					"a.fi8=b.fi8,\n" +
					"a.fi9=b.fi9,\n" +
					"a.fi10=b.fi10,\n" +
					"a.fi11=b.fi11,\n" +
					"a.fi12=b.fi12,\n" +
					"a.fi13=b.fi13,\n" +
					"a.fi14=b.fi14,\n" +
					"a.fi15=b.fi15,\n" +
					"a.fi16=b.fi16,\n" +
					"a.fi17=b.fi17,\n" +
					"a.fi18=b.fi18,\n" +
					"a.fi19=b.fi19,\n" +
					"a.fi20=b.fi20,\n" +
					"a.fd1=b.fd1,\n" +
					"a.fd2=b.fd2,\n" +
					"a.fd3=b.fd3\n" +
					"where a.resid=b.id\n";

			String sql2="update res_inventory_item_s a,res b\n" +
					"set a.zc_category=b.zc_category,\n" +
					"a.class_id=b.class_id,\n" +
					"a.type=b.type,\n" +
					"a.gj_dl=b.gj_dl,\n" +
					"a.gj_xl=b.gj_xl,\n" +
					"a.uuid=b.uuid,\n" +
					"a.name=b.name,\n" +
					"a.zcsource=b.zcsource,\n" +
					"a.model=b.model,\n" +
					"a.sn=b.sn,\n" +
					"a.version=b.version,\n" +
					"a.res_desc=b.res_desc,\n" +
					"a.brand=b.brand,\n" +
					"a.supplier=b.supplier,\n" +
					"a.recycle=b.recycle,\n" +
					"a.prerecycle=b.prerecycle,\n" +
					"a.env=b.env,\n" +
					"a.risk=b.risk,\n" +
					"a.buy_time=b.buy_time,\n" +
					"a.offline_time=b.offline_time,\n" +
					"a.online_time=b.online_time,\n" +
					"a.ip=b.ip,\n" +
					"a.rwm=b.rwm,\n" +
					"a.confdesc=b.confdesc,\n" +
					"a.loc=b.loc,\n" +
					"a.locshow=b.locshow,\n" +
					"a.locdtl=b.locdtl,\n" +
					"a.rack=b.rack,\n" +
					"a.frame=b.frame,\n" +
					"a.belong_company_id=b.belong_company_id,\n" +
					"a.belong_part_id=b.belong_part_id,\n" +
					"a.used_company_id=b.used_company_id,\n" +
					"a.part_id=b.part_id,\n" +
					"a.used_userid=b.used_userid,\n" +
					"a.mgr_part_id=b.mgr_part_id,\n" +
					"a.maintain_userid=b.maintain_userid,\n" +
					"a.headuserid=b.headuserid,\n" +
					"a.buy_price=b.buy_price,\n" +
					"a.net_worth=b.net_worth,\n" +
					"a.zc_cnt=b.zc_cnt,\n" +
					"a.actionstatus=b.actionstatus,\n" +
					"a.wb=b.wb,\n" +
					"a.wb_auto=b.wb_auto,\n" +
					"a.wbout_date=b.wbout_date,\n" +
					"a.wbsupplier=b.wbsupplier,\n" +
					"a.wbct=b.wbct,\n" +
					"a.status=b.status,\n" +
					"a.changestatus=b.changestatus,\n" +
					"a.importlabel=b.importlabel,\n" +
					"a.img=b.img,\n" +
					"a.attach=b.attach,\n" +
					"a.mark=b.mark,\n" +
					"a.changestate=b.changestate,\n" +
					"a.review_userid=b.review_userid,\n" +
					"a.review_date=b.review_date,\n" +
					"a.fs1=b.fs1,\n" +
					"a.fs2=b.fs2,\n" +
					"a.fs3=b.fs3,\n" +
					"a.fs4=b.fs4,\n" +
					"a.fs5=b.fs5,\n" +
					"a.fs6=b.fs6,\n" +
					"a.fs7=b.fs7,\n" +
					"a.fs8=b.fs8,\n" +
					"a.fs9=b.fs9,\n" +
					"a.fs10=b.fs10,\n" +
					"a.fs11=b.fs11,\n" +
					"a.fs12=b.fs12,\n" +
					"a.fs13=b.fs13,\n" +
					"a.fs14=b.fs14,\n" +
					"a.fs15=b.fs15,\n" +
					"a.fs16=b.fs16,\n" +
					"a.fs17=b.fs17,\n" +
					"a.fs18=b.fs18,\n" +
					"a.fs19=b.fs19,\n" +
					"a.fs20=b.fs20,\n" +
					"a.fi1=b.fi1,\n" +
					"a.fi2=b.fi2,\n" +
					"a.fi3=b.fi3,\n" +
					"a.fi4=b.fi4,\n" +
					"a.fi5=b.fi5,\n" +
					"a.fi6=b.fi6,\n" +
					"a.fi7=b.fi7,\n" +
					"a.fi8=b.fi8,\n" +
					"a.fi9=b.fi9,\n" +
					"a.fi10=b.fi10,\n" +
					"a.fi11=b.fi11,\n" +
					"a.fi12=b.fi12,\n" +
					"a.fi13=b.fi13,\n" +
					"a.fi14=b.fi14,\n" +
					"a.fi15=b.fi15,\n" +
					"a.fi16=b.fi16,\n" +
					"a.fi17=b.fi17,\n" +
					"a.fi18=b.fi18,\n" +
					"a.fi19=b.fi19,\n" +
					"a.fi20=b.fi20,\n" +
					"a.fd1=b.fd1,\n" +
					"a.fd2=b.fd2,\n" +
					"a.fd3=b.fd3\n" +
					"where a.resid=b.id";
			db.executes(sql1,sql2);
		}
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
		if(ResInventoryService.INVENTORY_STATAUS_CANCEL.equals(item.getStatus())||ResInventoryService.INVENTORY_STATAUS_START.equals(item.getStatus()) ||
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

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/queryInventoryRes.do")
	public R queryInventoryRes(ResInventory entity) {
		return resInventoryService.inventoryRange(entity);
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/manualInventoryRes.do")
	public R manualInventoryRes( String fileid,String id, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		SysFiles fileobj = SysFilesServiceImpl.getById(fileid);
		String fileurl = fileobj.getPath();
		String filePath = FileUpDownController.getWebRootDir() + ".." + File.separatorChar + fileurl;

		ResInventory ri=ResInventoryServiceImpl.getById(id);
		if(ri==null){
			return R.FAILURE_NO_DATA();
		}
		if(!ResInventoryService.INVENTORY_STATAUS_WAIT.equals(ri.getStatus())){
			return R.FAILURE("当前单据单状态错误,不能进行盘点操作!");
		}

		R r = R.SUCCESS_OPER();
		try {
			ImportParams params = new ImportParams();
			params.setHeadRows(1);
			params.setTitleRows(0);
			params.setStartSheetIndex(0);
			List<ResInventoryEntity> result = ExcelImportUtil.importExcel(new File(filePath), ResInventoryEntity.class, params);
			if(result.size()>0)
			{
				if(!result.get(0).getPdbatchid().equals(ri.getBatchid())){
					return R.FAILURE("导入的盘点单据错误!");
				}
			}
			r = resInventoryImportService.executeEntitysImport(result);
		} catch (Exception e) {
			e.printStackTrace();
			return R.FAILURE("导入数据异常");
		}
		//处理状态
		if("0".equals(db.uniqueRecord("select count(1) cnt from res_inventory_item where dr='0' and pdstatus<>'"+ResInventoryService.INVENTORY_STATAUS_FINISH+"' and pdid=?",id).getString("cnt"))){
			//更新状态
			Date date = new Date(); // 获取一个Date对象
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
			String nowtime = simpleDateFormat.format(date);
			UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
			uw.set("status",ResInventoryService.INVENTORY_STATAUS_FINISH);
			uw.set("finishtime",nowtime);
			uw.eq("id", id);
			ResInventoryServiceImpl.update(uw);
		}
		return r;

	}



	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	@RequestMapping(value = "/downloadInventoryRes.do")
	public void downloadInventoryRes(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String sql="select "+ZcCommonService.resSqlbody ;
		sql=sql+" case when t.pdstatus = '"+ResInventoryService.INVENTORY_ITEM_STATAUS_WAIT+"' then '待盘点' when t.pdstatus = '"+ResInventoryService.INVENTORY_STATAUS_FINISH+"' then '已盘点'  else '未知' end pdstatusstr ,";
		sql=sql+" case when t.pdsyncneed = '"+ResInventoryService.INVENTORY_ITEM_ACTION_SYNC+"' then '更新' when t.pdsyncneed = '"+ResInventoryService.INVENTORY_ITEM_ACTION_NOSYNC+"' then '不更新'  else '未知' end pdsyncneedstr ,";
		sql=sql+" t.* from res_inventory_item t where dr='0' and pdid=? order by t.pdstatus desc";
		JSONArray data = ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql,ps.getString("id")).toJsonArrayWithJsonObject());
		List<ResInventoryEntity> data_excel = new ArrayList<ResInventoryEntity>();
		for (int i = 0; i < data.size(); i++) {
			ResInventoryEntity entity = new ResInventoryEntity();
			entity.fullResEntity(data.getJSONObject(i));
			data_excel.add(entity);
		}

		ExportParams parms = new ExportParams();
		parms.setSheetName("盘点明细");
		parms.setHeaderHeight(1000);

		Workbook workbook;
		workbook = ExcelExportUtil.exportExcel(parms, ResInventoryEntity.class, data_excel);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");
		String filedisplay = "inventory.xls";
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
		try {
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

