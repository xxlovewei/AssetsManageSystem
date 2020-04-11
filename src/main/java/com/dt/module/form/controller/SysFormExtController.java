package com.dt.module.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.form.entity.SysForm;
import com.dt.module.form.service.ISysFormService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-03-28
 */
@Controller
@RequestMapping("/api/form/sysForm/ext")
public class SysFormExtController extends BaseController {

    @Autowired
    ISysFormService SysFormServiceImpl;

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insertOrUpdate.do")
    public R insertOrUpdate(SysForm entity) {
        System.out.print(entity.getCt());
        return R.SUCCESS_OPER(SysFormServiceImpl.saveOrUpdate(entity));
    }

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByOwner.do")
    public R selectByOwner(String owner) {
        QueryWrapper<SysForm> ew = new QueryWrapper<SysForm>();
        ew.and(i -> i.eq("owner", owner)).orderByDesc("create_time");
        return R.SUCCESS_OPER(SysFormServiceImpl.list(ew));
    }

}
