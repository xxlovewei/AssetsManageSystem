package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.entity.ResCollectionreturnItem;
import com.dt.module.zc.service.IResCollectionreturnItemService;
import com.dt.module.zc.service.IResCollectionreturnService;
import com.dt.module.zc.service.impl.ResCollectionreturnService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc/resCollectionreturn/ext")
public class ResCollectionreturnExtController extends BaseController {


    @Autowired
    IResCollectionreturnService ResCollectionreturnServiceImpl;

    @Autowired
    IResCollectionreturnItemService ResCollectionreturnItemServiceImpl;


    @Autowired
    ResCollectionreturnService resCollectionreturnService;

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insertOrUpdate.do")
    public R insertOrUpdate(ResCollectionreturn entity, String items) {
        return resCollectionreturnService.insertOrUpdate(entity, items);
    }


    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        return resCollectionreturnService.selectList(null, null, null);
    }


    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/myList.do")
    public R myList(String statustype, String bustype) {
        return resCollectionreturnService.selectList(getUserId(), statustype, bustype);
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {
        return resCollectionreturnService.selectByUuid(uuid);
    }


}
