package com.dt.module.flow.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2019-12-03
 */
@Controller
@RequestMapping("/api/flow/sysProcessDataExt")
public class SysProcessDataExtController extends BaseController {

    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @ResponseBody
    @Acl(info = "根据Id删除", value = Acl.ACL_USER)
    @RequestMapping(value = "/deleteById.do")
    public R deleteById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        return R.SUCCESS_OPER(SysProcessDataServiceImpl.removeById(id));
    }



    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByBusinessId.do")
    public R selectById(@RequestParam(value = "businessid", required = true, defaultValue = "") String businessid) {
        QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
        qw.eq("busid", businessid);
        return R.SUCCESS_OPER(SysProcessDataServiceImpl.getOne(qw));
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectListByMy.do")
    public R selectListByMy(String sdate, String edate, String type) {
        QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
        qw.eq("pstartuserid", this.getUserId());
        qw.isNotNull("ptype");
        if (ToolUtil.isNotEmpty(sdate)) {
            qw.ge("create_time", sdate);
        }
        if (ToolUtil.isNotEmpty(edate)) {
            qw.le("create_time", edate);
        }
        if (ToolUtil.isNotEmpty(type)) {
            JSONArray type_arr = JSONArray.parseArray(type);
            if (type_arr.size() > 0) {
                List<String> colsv = new ArrayList<String>();
                for (int i = 0; i < type_arr.size(); i++) {
                    colsv.add(type_arr.getString(i));
                }
                qw.in("ptype", colsv);
            }
        }
        qw.orderByDesc("create_time");
        return R.SUCCESS_OPER(SysProcessDataServiceImpl.list(qw));

    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList(String sdate, String edate, String type) {
        QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
        qw.isNotNull("ptype");
        if (ToolUtil.isNotEmpty(sdate)) {
            qw.ge("create_time", sdate);
        }
        if (ToolUtil.isNotEmpty(edate)) {
            qw.le("create_time", edate);
        }
        if (ToolUtil.isNotEmpty(type)) {
            JSONArray type_arr = JSONArray.parseArray(type);
            if (type_arr.size() > 0) {
                List<String> colsv = new ArrayList<String>();
                for (int i = 0; i < type_arr.size(); i++) {
                    colsv.add(type_arr.getString(i));
                }
                qw.in("ptype", colsv);
            }
        }
        qw.orderByDesc("create_time");
        return R.SUCCESS_OPER(SysProcessDataServiceImpl.list(qw));
    }

}
