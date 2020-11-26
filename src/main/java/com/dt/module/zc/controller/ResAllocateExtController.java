package com.dt.module.zc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.entity.ResAllocateItem;
import com.dt.module.zc.service.IResAllocateItemService;
import com.dt.module.zc.service.IResAllocateService;
import com.dt.module.zc.service.impl.ResAllocateService;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-04-25
 */
@Controller
@RequestMapping("/api/zc/resAllocate/ext")
public class ResAllocateExtController extends BaseController {

    @Autowired
    ZcChangeService zcChangeService;

    @Autowired
    ZcService zcService;

    @Autowired
    IResAllocateItemService ResAllocateItemServiceImpl;

    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    @Autowired
    ResAllocateService resAllocateService;


    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        return resAllocateService.selectList(null, null);
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/myList.do")
    public R myList(String statustype) {
        return resAllocateService.selectList(this.getUserId(), statustype);
    }

//    @ResponseBody
//    @Acl(info = "sure", value = Acl.ACL_USER)
//    @RequestMapping(value = "/sureAllocationById.do")
//    public R sureAllocationById(@RequestParam(value = "id", required = true, defaultValue = "") String id, String items) {
//        //        ResAllocate obj = ResAllocateServiceImpl.getById(id);
////        String status = obj.getStatus();
////        if ("doing".equals(status)) {
////            UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
////            ups.set("status", "finish");
////            ups.and(i -> i.eq("id", obj.getId()));
////            ResAllocateServiceImpl.update(ups);
////            return zcChangeService.zcDBSureChange(obj.getUuid());
////        } else {
////            return R.FAILURE("当前状态不允许确认");
////        }
//    }

    //status:doing,finish,cancel
//    @ResponseBody
//    @Acl(info = "cancel", value = Acl.ACL_USER)
//    @RequestMapping(value = "/cancelAllocationById.do")
//    public R cancelAllocationById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
//        ResAllocate obj = ResAllocateServiceImpl.getById(id);
//        String status = obj.getStatus();
//        if ("cancel".equals(status) || "finish".equals(status)) {
//            return R.FAILURE("当前状态不允许取消");
//        } else {
//            UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
//            ups.set("status", "cancel");
//            ups.and(i -> i.eq("id", obj.getId()));
//            ResAllocateServiceImpl.update(ups);
//            return zcChangeService.zcDBCancelChange(obj.getUuid());
//        }
//    }


    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectById.do")
    public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        ResAllocate obj = ResAllocateServiceImpl.getById(id);
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t,res_allocate_item b where t.id=b.resid and b.dr='0' and b.allocateid='" + id + "'";
        res.put("items", db.query(sql).toJsonArrayWithJsonObject());
        return R.SUCCESS_OPER(res);
    }

    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByBusid.do")
    public R selectByBusid(@RequestParam(value = "busid", required = true, defaultValue = "") String busid) {
        QueryWrapper<ResAllocate> qw = new QueryWrapper<ResAllocate>();
        qw.and(i -> i.eq("uuid", busid));
        ResAllocate obj = ResAllocateServiceImpl.getOne(qw);
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t,res_allocate_item b where t.id=b.resid and b.dr='0' and b.busuuid=?";
        res.put("items", db.query(sql, busid).toJsonArrayWithJsonObject());
        return R.SUCCESS_OPER(res);
    }

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/createDb.do")
    public R createDb(ResAllocate entity, String items) {
        return resAllocateService.createDb(entity, items);
    }


}

