package com.dt.module.base.controller;

import com.dt.module.base.entity.SysDictItem;
import com.dt.module.base.service.ISysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.entity.SysDict;
import com.dt.module.base.service.ISysDictService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/api/sysDict/ext")
public class SysDictExtController extends BaseController {

    @Autowired
    ISysDictService SysDictServiceImpl;

    @Autowired
    ISysDictItemService SysDictItemServiceImpl;

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_DENY)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        QueryWrapper<SysDict> ew = new QueryWrapper<SysDict>();
        ew.orderByDesc("name");
        return R.SUCCESS_OPER(SysDictServiceImpl.list(ew));
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectItemListByDictId.do")
    public R selectListByDictId(String dict_id) {
        QueryWrapper<SysDictItem> ew = new QueryWrapper<SysDictItem>();
        ew.and(i -> i.eq("dict_id", dict_id));
        ew.orderByDesc("sort");
        return R.SUCCESS_OPER(SysDictItemServiceImpl.list(ew));
    }


}
