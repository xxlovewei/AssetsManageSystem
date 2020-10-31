package com.dt.module.zc.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.controller.FileUpDownController;
import com.dt.module.base.entity.SysFiles;
import com.dt.module.base.service.ISysFilesService;
import com.dt.module.zc.entity.*;
import com.dt.module.zc.service.IResInventoryItemSService;
import com.dt.module.zc.service.IResInventoryItemService;
import com.dt.module.zc.service.IResInventoryService;
import com.dt.module.zc.service.IResInventoryUserService;
import com.dt.module.zc.service.impl.ResInventoryImportService;
import com.dt.module.zc.service.impl.ResInventoryService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

        ResInventory r = ResInventoryServiceImpl.getById(id);
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(r, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect));

        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res_inventory_item t where dr=0 and pdid=?";
        res.put("items", db.query(sql, id).toJsonArrayWithJsonObject());
        return R.SUCCESS_OPER(res);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/syncdata.do")
    public R syncdata(String id) {

        ResInventory ri = ResInventoryServiceImpl.getById(id);
        if (ri == null) {
            return R.FAILURE_NO_DATA();
        }
        if (!ResInventoryService.INVENTORY_STATAUS_FINISH.equals(ri.getStatus())) {
            return R.FAILURE("当前单据单状态错误,不能进行同步数据操作!");
        }
        if ("1".equals(ri.getSyncstatus())) {
            return R.FAILURE("数据已同步,不需要重复操作.");
        }

        UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
        uw.set("syncstatus", "1");
        uw.eq("id", id);
        ResInventoryServiceImpl.update(uw);
        String sql1 = "update res a,res_inventory_item b   " +
                "set a.class_id=b.class_id,   " +
                "a.type=b.type,   " +
                "a.uuid=b.uuid,   " +
                "a.name=b.name,   " +
                "a.zcsource=b.zcsource,   " +
                "a.model=b.model,   " +
                "a.sn=b.sn,   " +
                "a.res_desc=b.res_desc,   " +
                "a.brand=b.brand,   " +
                "a.supplier=b.supplier,   " +
                "a.recycle=b.recycle,   " +
                "a.prerecycle=b.prerecycle,   " +
                "a.buy_time=b.buy_time,   " +
                "a.confdesc=b.confdesc,   " +
                "a.loc=b.loc,   " +
                "a.locdtl=b.locdtl,   " +
                "a.belong_company_id=b.belong_company_id,   " +
                "a.belong_part_id=b.belong_part_id,   " +
                "a.used_company_id=b.used_company_id,   " +
                "a.part_id=b.part_id,   " +
                "a.used_userid=b.used_userid,   " +
                "a.mgr_part_id=b.mgr_part_id,   " +
                "a.buy_price=b.buy_price,   " +
                "a.net_worth=b.net_worth,   " +
                "a.zc_cnt=b.zc_cnt,   " +
                "a.actionstatus=b.actionstatus,   " +
                "a.wb=b.wb,   " +
                "a.wb_auto=b.wb_auto,   " +
                "a.wbsupplier=b.wbsupplier,   " +
                "a.wbct=b.wbct,   " +
                "a.status=b.status,   " +
                "a.mark=b.mark,   " +
                "a.lastinventorytime=now()   " +
                "where a.id=b.resid   ";
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
    public R insertOrUpdate(ResInventory entity, String category) {

        ResInventoryServiceImpl.saveOrUpdate(entity);
        QueryWrapper<ResInventory> ew = new QueryWrapper<ResInventory>();
        ew.and(i -> i.eq("batchid", entity.getBatchid()));
        ResInventory obj = ResInventoryServiceImpl.getOne(ew);


        //处理用户
        QueryWrapper<ResInventoryUser> userqw = new QueryWrapper<ResInventoryUser>();
        userqw.and(i -> i.eq("pdid", obj.getId()));
        ResInventoryUserServiceImpl.remove(userqw);
        String pduserstr = entity.getPduserdata();
        ArrayList<ResInventoryUser> items = new ArrayList<ResInventoryUser>();
        if (ToolUtil.isNotEmpty(pduserstr)) {
            JSONArray pduserarr = JSONArray.parseArray(pduserstr);
            for (int i = 0; i < pduserarr.size(); i++) {
                ResInventoryUser e = new ResInventoryUser();
                e.setUserid(pduserarr.getJSONObject(i).getString("user_id"));
                e.setUsername(pduserarr.getJSONObject(i).getString("name"));
                e.setPdid(obj.getId());
                items.add(e);
            }
            ResInventoryUserServiceImpl.saveBatch(items);
        }


        //处理盘点单明细
        R itemsR = resInventoryService.inventoryRange(entity, category);
        JSONArray arr = itemsR.queryDataToJSONArray();
        ArrayList<ResInventoryItem> itemlist = new ArrayList<ResInventoryItem>();
        ArrayList<ResInventoryItemS> itemslist = new ArrayList<ResInventoryItemS>();
        if (arr.size() > 0) {
            for (int i = 0; i < arr.size(); i++) {
                ResInventoryItem e1 = new ResInventoryItem();
                e1.setPdbatchid(obj.getBatchid());
                e1.setPdid(obj.getId());
                e1.setResid(arr.getJSONObject(i).getString("id"));
                e1.setPdstatus(ResInventoryService.INVENTORY_ITEM_STATAUS_WAIT);
                e1.setPdsyncneed(ResInventoryService.INVENTORY_ITEM_ACTION_NOSYNC);

                ResInventoryItemS e2 = new ResInventoryItemS();
                e2.setPdbatchid(obj.getBatchid());
                e2.setPdid(obj.getId());
                e2.setResid(arr.getJSONObject(i).getString("id"));
                itemlist.add(e1);
                itemslist.add(e2);
            }

            obj.setCnt(new BigDecimal(itemlist.size()));
            ResInventoryServiceImpl.saveOrUpdate(obj);
            //批量更新主要数据
            ResInventoryItemServiceImpl.saveBatch(itemlist);
            ResInventoryItemSServiceImpl.saveBatch(itemslist);
            //批量更新内容
            String sql1 = "update res_inventory_item a,res b   " +
                    "set a.category=b.category,   " +
                    "a.class_id=b.class_id,   " +
                    "a.type=b.type,   " +
                    "a.gj_dl=b.gj_dl,   " +
                    "a.gj_xl=b.gj_xl,   " +
                    "a.uuid=b.uuid,   " +
                    "a.name=b.name,   " +
                    "a.zcsource=b.zcsource,   " +
                    "a.model=b.model,   " +
                    "a.sn=b.sn,   " +
                    "a.version=b.version,   " +
                    "a.res_desc=b.res_desc,   " +
                    "a.brand=b.brand,   " +
                    "a.supplier=b.supplier,   " +
                    "a.recycle=b.recycle,   " +
                    "a.prerecycle=b.prerecycle,   " +
                    "a.env=b.env,   " +
                    "a.risk=b.risk,   " +
                    "a.buy_time=b.buy_time,   " +
                    "a.offline_time=b.offline_time,   " +
                    "a.online_time=b.online_time,   " +
                    "a.ip=b.ip,   " +
                    "a.rwm=b.rwm,   " +
                    "a.confdesc=b.confdesc,   " +
                    "a.loc=b.loc,   " +
                    "a.locshow=b.locshow,   " +
                    "a.locdtl=b.locdtl,   " +
                    "a.rack=b.rack,   " +
                    "a.frame=b.frame,   " +
                    "a.belong_company_id=b.belong_company_id,   " +
                    "a.belong_part_id=b.belong_part_id,   " +
                    "a.used_company_id=b.used_company_id,   " +
                    "a.part_id=b.part_id,   " +
                    "a.used_userid=b.used_userid,   " +
                    "a.mgr_part_id=b.mgr_part_id,   " +
                    "a.maintain_userid=b.maintain_userid,   " +
                    "a.headuserid=b.headuserid,   " +
                    "a.buy_price=b.buy_price,   " +
                    "a.net_worth=b.net_worth,   " +
                    "a.zc_cnt=b.zc_cnt,   " +
                    "a.actionstatus=b.actionstatus,   " +
                    "a.wb=b.wb,   " +
                    "a.wb_auto=b.wb_auto,   " +
                    "a.wbout_date=b.wbout_date,   " +
                    "a.wbsupplier=b.wbsupplier,   " +
                    "a.wbct=b.wbct,   " +
                    "a.status=b.status,   " +
                    "a.changestatus=b.changestatus,   " +
                    "a.importlabel=b.importlabel,   " +
                    "a.img=b.img,   " +
                    "a.attach=b.attach,   " +
                    "a.mark=b.mark,   " +
                    "a.changestate=b.changestate,   " +
                    "a.review_userid=b.review_userid,   " +
                    "a.review_date=b.review_date,   " +
                    "a.fs1=b.fs1,   " +
                    "a.fs2=b.fs2,   " +
                    "a.fs3=b.fs3,   " +
                    "a.fs4=b.fs4,   " +
                    "a.fs5=b.fs5,   " +
                    "a.fs6=b.fs6,   " +
                    "a.fs7=b.fs7,   " +
                    "a.fs8=b.fs8,   " +
                    "a.fs9=b.fs9,   " +
                    "a.fs10=b.fs10,   " +
                    "a.fs11=b.fs11,   " +
                    "a.fs12=b.fs12,   " +
                    "a.fs13=b.fs13,   " +
                    "a.fs14=b.fs14,   " +
                    "a.fs15=b.fs15,   " +
                    "a.fs16=b.fs16,   " +
                    "a.fs17=b.fs17,   " +
                    "a.fs18=b.fs18,   " +
                    "a.fs19=b.fs19,   " +
                    "a.fs20=b.fs20,   " +
                    "a.fi1=b.fi1,   " +
                    "a.fi2=b.fi2,   " +
                    "a.fi3=b.fi3,   " +
                    "a.fi4=b.fi4,   " +
                    "a.fi5=b.fi5,   " +
                    "a.fi6=b.fi6,   " +
                    "a.fi7=b.fi7,   " +
                    "a.fi8=b.fi8,   " +
                    "a.fi9=b.fi9,   " +
                    "a.fi10=b.fi10,   " +
                    "a.fi11=b.fi11,   " +
                    "a.fi12=b.fi12,   " +
                    "a.fi13=b.fi13,   " +
                    "a.fi14=b.fi14,   " +
                    "a.fi15=b.fi15,   " +
                    "a.fi16=b.fi16,   " +
                    "a.fi17=b.fi17,   " +
                    "a.fi18=b.fi18,   " +
                    "a.fi19=b.fi19,   " +
                    "a.fi20=b.fi20,   " +
                    "a.fd1=b.fd1,   " +
                    "a.fd2=b.fd2,   " +
                    "a.fd3=b.fd3   " +
                    "where a.resid=b.id   ";

            String sql2 = "update res_inventory_item_s a,res b   " +
                    "set a.category=b.category,   " +
                    "a.class_id=b.class_id,   " +
                    "a.type=b.type,   " +
                    "a.gj_dl=b.gj_dl,   " +
                    "a.gj_xl=b.gj_xl,   " +
                    "a.uuid=b.uuid,   " +
                    "a.name=b.name,   " +
                    "a.zcsource=b.zcsource,   " +
                    "a.model=b.model,   " +
                    "a.sn=b.sn,   " +
                    "a.version=b.version,   " +
                    "a.res_desc=b.res_desc,   " +
                    "a.brand=b.brand,   " +
                    "a.supplier=b.supplier,   " +
                    "a.recycle=b.recycle,   " +
                    "a.prerecycle=b.prerecycle,   " +
                    "a.env=b.env,   " +
                    "a.risk=b.risk,   " +
                    "a.buy_time=b.buy_time,   " +
                    "a.offline_time=b.offline_time,   " +
                    "a.online_time=b.online_time,   " +
                    "a.ip=b.ip,   " +
                    "a.rwm=b.rwm,   " +
                    "a.confdesc=b.confdesc,   " +
                    "a.loc=b.loc,   " +
                    "a.locshow=b.locshow,   " +
                    "a.locdtl=b.locdtl,   " +
                    "a.rack=b.rack,   " +
                    "a.frame=b.frame,   " +
                    "a.belong_company_id=b.belong_company_id,   " +
                    "a.belong_part_id=b.belong_part_id,   " +
                    "a.used_company_id=b.used_company_id,   " +
                    "a.part_id=b.part_id,   " +
                    "a.used_userid=b.used_userid,   " +
                    "a.mgr_part_id=b.mgr_part_id,   " +
                    "a.maintain_userid=b.maintain_userid,   " +
                    "a.headuserid=b.headuserid,   " +
                    "a.buy_price=b.buy_price,   " +
                    "a.net_worth=b.net_worth,   " +
                    "a.zc_cnt=b.zc_cnt,   " +
                    "a.actionstatus=b.actionstatus,   " +
                    "a.wb=b.wb,   " +
                    "a.wb_auto=b.wb_auto,   " +
                    "a.wbout_date=b.wbout_date,   " +
                    "a.wbsupplier=b.wbsupplier,   " +
                    "a.wbct=b.wbct,   " +
                    "a.status=b.status,   " +
                    "a.changestatus=b.changestatus,   " +
                    "a.importlabel=b.importlabel,   " +
                    "a.img=b.img,   " +
                    "a.attach=b.attach,   " +
                    "a.mark=b.mark,   " +
                    "a.changestate=b.changestate,   " +
                    "a.review_userid=b.review_userid,   " +
                    "a.review_date=b.review_date,   " +
                    "a.fs1=b.fs1,   " +
                    "a.fs2=b.fs2,   " +
                    "a.fs3=b.fs3,   " +
                    "a.fs4=b.fs4,   " +
                    "a.fs5=b.fs5,   " +
                    "a.fs6=b.fs6,   " +
                    "a.fs7=b.fs7,   " +
                    "a.fs8=b.fs8,   " +
                    "a.fs9=b.fs9,   " +
                    "a.fs10=b.fs10,   " +
                    "a.fs11=b.fs11,   " +
                    "a.fs12=b.fs12,   " +
                    "a.fs13=b.fs13,   " +
                    "a.fs14=b.fs14,   " +
                    "a.fs15=b.fs15,   " +
                    "a.fs16=b.fs16,   " +
                    "a.fs17=b.fs17,   " +
                    "a.fs18=b.fs18,   " +
                    "a.fs19=b.fs19,   " +
                    "a.fs20=b.fs20,   " +
                    "a.fi1=b.fi1,   " +
                    "a.fi2=b.fi2,   " +
                    "a.fi3=b.fi3,   " +
                    "a.fi4=b.fi4,   " +
                    "a.fi5=b.fi5,   " +
                    "a.fi6=b.fi6,   " +
                    "a.fi7=b.fi7,   " +
                    "a.fi8=b.fi8,   " +
                    "a.fi9=b.fi9,   " +
                    "a.fi10=b.fi10,   " +
                    "a.fi11=b.fi11,   " +
                    "a.fi12=b.fi12,   " +
                    "a.fi13=b.fi13,   " +
                    "a.fi14=b.fi14,   " +
                    "a.fi15=b.fi15,   " +
                    "a.fi16=b.fi16,   " +
                    "a.fi17=b.fi17,   " +
                    "a.fi18=b.fi18,   " +
                    "a.fi19=b.fi19,   " +
                    "a.fi20=b.fi20,   " +
                    "a.fd1=b.fd1,   " +
                    "a.fd2=b.fd2,   " +
                    "a.fd3=b.fd3   " +
                    "where a.resid=b.id";
            db.executes(sql1, sql2);
        }
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/cancel.do")
    public R cancel(String id) {
        ResInventory item = ResInventoryServiceImpl.getById(id);
        if (item == null) {
            return R.FAILURE_NO_DATA();
        }
        if (ResInventoryService.INVENTORY_STATAUS_START.equals(item.getStatus()) ||
                ResInventoryService.INVENTORY_STATAUS_WAIT.equals(item.getStatus())) {
            UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
            uw.set("status", ResInventoryService.INVENTORY_STATAUS_CANCEL);
            uw.eq("id", id);
            ResInventoryServiceImpl.update(uw);
        } else {
            return R.FAILURE("当前状态不允许取消盘点单");
        }
        return R.SUCCESS_OPER();
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/deleteById.do")
    public R deleteById(String id) {
        ResInventory item = ResInventoryServiceImpl.getById(id);
        if (item == null) {
            return R.FAILURE_NO_DATA();
        }
        if (ResInventoryService.INVENTORY_STATAUS_CANCEL.equals(item.getStatus()) || ResInventoryService.INVENTORY_STATAUS_START.equals(item.getStatus()) ||
                ResInventoryService.INVENTORY_STATAUS_WAIT.equals(item.getStatus())) {
            ResInventoryServiceImpl.removeById(id);
        } else {
            return R.FAILURE("当前状态不允许删除盘点单");
        }
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/assignusers.do")
    public R assignusers(String id, String pduserdata, String pduserlist) {

        ResInventory obj = ResInventoryServiceImpl.getById(id);
        if (obj == null) {
            return R.FAILURE_NO_DATA();
        }
        UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
        uw.set("pduserlist", pduserlist);
        uw.set("pduserdata", pduserdata);
        uw.eq("id", id);
        ResInventoryServiceImpl.update(uw);


        QueryWrapper<ResInventoryUser> userqw = new QueryWrapper<ResInventoryUser>();
        userqw.and(i -> i.eq("pdid", id));
        ResInventoryUserServiceImpl.remove(userqw);
        String pduserstr = pduserdata;
        ArrayList<ResInventoryUser> items = new ArrayList<ResInventoryUser>();
        if (ToolUtil.isNotEmpty(pduserstr)) {
            JSONArray pduserarr = JSONArray.parseArray(pduserstr);
            for (int i = 0; i < pduserarr.size(); i++) {
                ResInventoryUser e = new ResInventoryUser();
                e.setUserid(pduserarr.getJSONObject(i).getString("user_id"));
                e.setUsername(pduserarr.getJSONObject(i).getString("name"));
                e.setPdid(obj.getId());
                items.add(e);
            }
            ResInventoryUserServiceImpl.saveBatch(items);
        }
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryInventoryRes.do")
    public R queryInventoryRes(ResInventory entity, String category) {
        return resInventoryService.inventoryRange(entity, category);
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/manualInventoryRes.do")
    public R manualInventoryRes(String fileid, String id, HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        SysFiles fileobj = SysFilesServiceImpl.getById(fileid);
        String fileurl = fileobj.getPath();
        String filePath = FileUpDownController.getWebRootDir() + ".." + File.separatorChar + fileurl;

        ResInventory ri = ResInventoryServiceImpl.getById(id);
        if (ri == null) {
            return R.FAILURE_NO_DATA();
        }
        if (!ResInventoryService.INVENTORY_STATAUS_START.equals(ri.getStatus())) {
            return R.FAILURE("当前单据单状态错误,不能进行盘点操作!");
        }

        R r = R.SUCCESS_OPER();
        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            params.setTitleRows(0);
            params.setStartSheetIndex(0);
            List<ResInventoryEntity> result = ExcelImportUtil.importExcel(new File(filePath), ResInventoryEntity.class, params);
            if (result.size() > 0) {
                if (!result.get(0).getPdbatchid().equals(ri.getBatchid())) {
                    return R.FAILURE("导入的盘点单据错误!");
                }
            }
            r = resInventoryImportService.executeEntitysImport(result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAILURE("导入数据异常");
        }
        //处理状态
        if ("0".equals(db.uniqueRecord("select count(1) cnt from res_inventory_item where dr='0' and pdstatus<>'" + ResInventoryService.INVENTORY_STATAUS_FINISH + "' and pdid=?", id).getString("cnt"))) {
            //更新状态
            Date date = new Date(); // 获取一个Date对象
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
            String nowtime = simpleDateFormat.format(date);
            UpdateWrapper<ResInventory> uw = new UpdateWrapper<ResInventory>();
            uw.set("status", ResInventoryService.INVENTORY_STATAUS_FINISH);
            uw.set("finishtime", nowtime);
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

        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String sql = "select " + ZcCommonService.resSqlbody;
        sql = sql + " case when t.pdstatus = '" + ResInventoryService.INVENTORY_ITEM_STATAUS_WAIT + "' then '待盘点' when t.pdstatus = '" + ResInventoryService.INVENTORY_STATAUS_FINISH + "' then '已盘点'  else '未知' end pdstatusstr ,";
        sql = sql + " case when t.pdsyncneed = '" + ResInventoryService.INVENTORY_ITEM_ACTION_SYNC + "' then '更新' when t.pdsyncneed = '" + ResInventoryService.INVENTORY_ITEM_ACTION_NOSYNC + "' then '不更新'  else '未知' end pdsyncneedstr ,";
        sql = sql + " t.* from res_inventory_item t where dr='0' and pdid=? order by t.pdstatus desc";
        JSONArray data = ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, ps.getString("id")).toJsonArrayWithJsonObject());
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

