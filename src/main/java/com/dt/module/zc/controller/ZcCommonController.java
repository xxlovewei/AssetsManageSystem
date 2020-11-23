package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.ct.entity.CtCategoryRoot;
import com.dt.module.ct.service.ICtCategoryRootService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc")
public class ZcCommonController extends BaseController {

    @Autowired
    ZcService zcService;

    @Autowired
    ICtCategoryRootService CtCategoryRootServiceImpl;


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryDictFast.do")
    @Transactional
    public R queryDictFast(String uid, String zchccat, String comppart, String comp, String belongcomp, String dicts, String parts, String partusers, String classid, String classroot, String zccatused) {
        return zcService.queryDictFast(uid, zchccat, comppart, comp, belongcomp, dicts, parts, partusers, classid, classroot, zccatused);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/modifyZcColCtlShow.do")
    public R modifyZcColCtlShow(String id, String json) {
        return zcService.modifyZcColCtlShow(id, json);

    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryZcColCtlById.do")
    public R queryZcColCtlById(String id) {
        return zcService.queryZcColCtlById(id);
    }

    @ResponseBody
    @Acl(info = "获取后台类目", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectZcCats.do")
    public R selectZcCats() {
        QueryWrapper<CtCategoryRoot> ew = new QueryWrapper<CtCategoryRoot>();
        ew.in("id", '8', '3', '7');
        return R.SUCCESS_OPER(CtCategoryRootServiceImpl.list(ew));
    }


    @ResponseBody
    @Acl(info = "zc", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryzclabelcols.do")
    public R queryzclabelcols() {

        JSONArray res = new JSONArray();
        JSONObject e1 = new JSONObject();
        e1.put("name", "资产名称");
        res.add(e1);

        JSONObject e2 = new JSONObject();
        e2.put("model", "资产型号");
        res.add(e2);

        JSONObject e3 = new JSONObject();
        e3.put("buy_time", "采购日期");
        res.add(e3);

        JSONObject e4 = new JSONObject();
        e4.put("part_id", "使用部门");
        res.add(e4);

        JSONObject e5 = new JSONObject();
        e5.put("loc", "存放区域");
        res.add(e5);
        return R.SUCCESS_OPER(res);

    }


}
