package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.cmdb.service.impl.ResExtService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.entity.ResActionItem;
import java.util.ArrayList;
import java.util.List;
import com.dt.module.base.service.impl.SysUserInfoServiceImpl;
import com.dt.module.base.service.ISysUserInfoService;

/**
 * @author: algernonking
 * @date: Dec 2, 2019 2:31:20 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/zc")
public class ZcController extends BaseController {

    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Autowired
    IResActionItemService ResActionItemServiceImpl;

    @Autowired
    ResExtService resExtService;

    @Autowired
    ISysUserInfoService SysUserInfoServiceImpl;

    @ResponseBody
    @Acl(info = "查询单据", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectListBills.do")
    public R selectListBills(String bustype) {
        QueryWrapper<SysProcessData> ew = new QueryWrapper<SysProcessData>();
        ew.and(i -> i.eq("bustype",bustype));
        return R.SUCCESS_OPER(  SysProcessDataServiceImpl.list(ew));
    }

    @ResponseBody
    @Acl(info = "创建单据", value = Acl.ACL_USER)
    @RequestMapping(value = "/insertBill.do")
    public R insertBill(SysProcessData entity, String items) {
        String uuid = resExtService.createUuid(entity.getBustype());
        entity.setBusid(uuid);
        entity.setPstatus(SysUfloProcessService.P_STATUS_SFA);
        entity.setPstartuserid(this.getUserId());
        entity.setPstartusername(SysUserInfoServiceImpl.getById(this.getUserId()).getName());
        entity.setPtype(SysUfloProcessService.P_TYPE_FLOW);
        JSONArray items_arr = JSONArray.parseArray(items);
        List<ResActionItem> entityList = new ArrayList<ResActionItem>();
        for (int i = 0; i < items_arr.size(); i++) {
            ResActionItem e = new ResActionItem();
            e.setBusuuid(uuid);
            e.setResid(items_arr.getJSONObject(i).getString("id"));
            e.setStatus("out");
            entityList.add(e);
        }
        //entity.setDtotal(ConvertUtil.toBigDecimal(entityList.size()));
       System.out.println(entity);
        ResActionItemServiceImpl.saveBatch(entityList);
        SysProcessDataServiceImpl.save(entity);
        return R.SUCCESS_OPER();
    }




}
