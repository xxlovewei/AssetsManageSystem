package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.module.zc.entity.ResCollectionreturn;
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
        String sql = "select   " +
                "(select name from sys_user_info where user_id=b.create_by) createusername,   " +
                "(select route_name from hrm_org_part where node_id=b.tusedcompanyid) tcompfullname,   " +
                "(select node_name from hrm_org_part where node_id=b.tusedcompanyid) tcompname,   " +
                "(select route_name from hrm_org_part where node_id=b.tpartid) tpartfullame,   " +
                "(select node_name from hrm_org_part where node_id=b.tpartid) tpartname,   " +
                "(select name from sys_user_info where user_id=b.tuseduserid) usedusername,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tloc) tlocstr,   " +
                "date_format(busdate,'%Y-%m-%d') busdatestr,   " +
                "date_format(rreturndate,'%Y-%m-%d') rreturndatestr,   " +
                "date_format(returndate,'%Y-%m-%d') returndatestr,   " +
                "b.*" +
                "from res_collectionreturn b where dr='0' order by create_time desc";
        RcdSet rs = db.query(sql);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {
       return resCollectionreturnService.selectByUuid(uuid);
    }


}
