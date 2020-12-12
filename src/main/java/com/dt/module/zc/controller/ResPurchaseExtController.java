package com.dt.module.zc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.zc.entity.ResPurchase;
import com.dt.module.zc.service.IResPurchaseService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc/resPurchase/ext")
public class ResPurchaseExtController  extends BaseController {


    @Autowired
    IResPurchaseService ResPurchaseServiceImpl;

    @Autowired
    ZcService zcService;


    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        QueryWrapper<ResPurchase> q=new QueryWrapper<>();
        q.orderByDesc("create_time");
        return R.SUCCESS_OPER(ResPurchaseServiceImpl.list(q));
    }

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insertOrUpdate.do")
    public R insertOrUpdate(ResPurchase entity) {

        if(ToolUtil.isEmpty(entity.getId())){
            entity.setBusid(zcService.createUuid(ZcCommonService.UUID_PURCHASE));
            entity.setStatus("apply");
        }
        return R.SUCCESS_OPER(ResPurchaseServiceImpl.saveOrUpdate(entity));
    }

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/approval.do")
    public R approval(String busid) {

       return R.SUCCESS_OPER();
    }



}
