package com.dt.module.ct.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.ct.entity.CtCategoryRoot;
import com.dt.module.ct.service.ICtCategoryRootService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-30
 */
@Controller
@RequestMapping("/api/ctCategoryRoot/Ext")
public class CtCategoryRootExtController extends BaseController {

    @Autowired
    ICtCategoryRootService CtCategoryRootServiceImpl;

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList(String ids) {
        QueryWrapper<CtCategoryRoot> ew = null;
        Collection<String> cols = new ArrayList<String>();
        if (ToolUtil.isEmpty(ids)) {
        } else {
            JSONArray ids_arr = JSONArray.parseArray(ids);
            if (ids_arr != null && ids_arr.size() > 0) {
                for (int i = 0; i < ids_arr.size(); i++) {
                    cols.add(ids_arr.getString(i));
                }
                ew = new QueryWrapper<CtCategoryRoot>();
                ew.in("id", cols);
            }
        }
        return R.SUCCESS_OPER(CtCategoryRootServiceImpl.list(ew));
    }

}
