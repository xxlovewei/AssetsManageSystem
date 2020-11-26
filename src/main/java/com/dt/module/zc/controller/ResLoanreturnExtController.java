package com.dt.module.zc.controller;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.entity.ResLoanreturn;
import com.dt.module.zc.service.IResLoanreturnItemService;
import com.dt.module.zc.service.IResLoanreturnService;
import com.dt.module.zc.service.impl.ResLoanreturnService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc/resLoanreturn/ext")
public class ResLoanreturnExtController extends BaseController {

    @Autowired
    IResLoanreturnService ResLoanreturnServiceImpl;

    @Autowired
    IResLoanreturnItemService ResLoanreturnItemServiceImpl;


    @Autowired
    ResLoanreturnService resLoanreturnService;

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/zcjy.do")
    public R zcjy(ResLoanreturn entity, String items) {
        return resLoanreturnService.zcJy(entity, items);
    }

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/zcgh.do")
    public R zcgh(String id, String rreturndate, String rprocessuserid, String rprocessusername) {
        return resLoanreturnService.zcGh(id, rreturndate, rprocessuserid, rprocessusername);
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        return resLoanreturnService.selectList(null, null, null);
    }


    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/myList.do")
    public R myList(String statustype, String bustype) {
        return resLoanreturnService.selectList(this.getUserId(), statustype, bustype);

    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {
        return resLoanreturnService.selectByUuid(uuid);
    }

}
