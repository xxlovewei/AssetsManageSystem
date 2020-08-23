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
import com.dt.module.zc.service.impl.ResAllocateExtService;
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
    ResAllocateExtService resAllocateExtService;


    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        QueryWrapper<ResAllocate> ew = new QueryWrapper<ResAllocate>();
        ew.orderByDesc("create_time");
        return R.SUCCESS_OPER(ResAllocateServiceImpl.list(ew));
    }

    @ResponseBody
    @Acl(info = "sure", value = Acl.ACL_USER)
    @RequestMapping(value = "/sureAllocationById.do")
    public R sureAllocationById(@RequestParam(value = "id", required = true, defaultValue = "") String id, String items) {
        ResAllocate obj = ResAllocateServiceImpl.getById(id);
        String status = obj.getStatus();
        if ("doing".equals(status)) {
            UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
            ups.set("status", "finish");
            ups.and(i -> i.eq("id", obj.getId()));
            ResAllocateServiceImpl.update(ups);
            return zcChangeService.zcDBSureChange(obj.getUuid());
        } else {
            return R.FAILURE("当前状态不允许确认");
        }
    }

    //status:doing,finish,cancel
    @ResponseBody
    @Acl(info = "cancel", value = Acl.ACL_USER)
    @RequestMapping(value = "/cancelAllocationById.do")
    public R cancelAllocationById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        ResAllocate obj = ResAllocateServiceImpl.getById(id);
        String status = obj.getStatus();
        if ("cancel".equals(status) || "finish".equals(status)) {
            return R.FAILURE("当前状态不允许取消");
        } else {
            UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
            ups.set("status", "cancel");
            ups.and(i -> i.eq("id", obj.getId()));
            ResAllocateServiceImpl.update(ups);
            return zcChangeService.zcDBCancelChange(obj.getUuid());
        }
    }


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
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insertOrUpdate.do")
    public R insertOrUpdate(ResAllocate entity, String items) {
        String id = "";
        String uuid = "";
        if (ToolUtil.isNotEmpty(entity.getId())) {
            id = entity.getId();
            uuid = entity.getUuid();
        } else {
            ArrayList<ResAllocateItem> cols = new ArrayList<ResAllocateItem>();
            uuid = zcService.createUuid(ZcCommonService.UUID_DB);
            entity.setUuid(uuid);
            entity.setStatus(ResAllocateExtService.STATUS_DOING);
            ResAllocateServiceImpl.saveOrUpdate(entity);
            QueryWrapper<ResAllocate> ew = new QueryWrapper<ResAllocate>();
            String finalUuid = uuid;
            ew.and(i -> i.eq("uuid", finalUuid));
            ResAllocate dbobj = ResAllocateServiceImpl.getOne(ew);
            JSONArray arr = JSONArray.parseArray(items);
            for (int i = 0; i < arr.size(); i++) {
                ResAllocateItem e = new ResAllocateItem();
                e.setBusuuid(uuid);
                e.setResid(arr.getJSONObject(i).getString("id"));
                e.setAllocateid(dbobj.getId());

                e.setFcompid(entity.getFcompid());
                e.setFcompname(entity.getFcompname());
                e.setBusdate(entity.getBusdate());

//                e.setFloc(entity.getFloc());
//                e.setFlocname(entity.getFlocname());
//                e.setFlocdtl(entity.getFlocdtl());

                e.setTousedcompid(entity.getTousedcompid());
                e.setTousedcompname(entity.getTousedcompname());
                e.setToloc(entity.getToloc());
                e.setTolocname(entity.getTolocname());
                e.setTolocdtl(entity.getTolocdtl());

                cols.add(e);
            }
            ResAllocateItemServiceImpl.saveBatch(cols);
            zcChangeService.zcDBChange(uuid);
        }
        return R.SUCCESS_OPER();
    }


}

