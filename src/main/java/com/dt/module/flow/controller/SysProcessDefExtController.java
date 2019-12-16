package com.dt.module.flow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.flow.entity.SysProcessDef;
import com.dt.module.flow.service.ISysProcessDefService;
import org.springframework.beans.factory.annotation.Autowired;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.tool.util.DbUtil;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.tool.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import com.dt.core.common.base.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2019-12-16
 */
@Controller
@RequestMapping("/api/flow/sysProcessDef/Ext")
public class SysProcessDefExtController extends BaseController {

	@Autowired
	ISysProcessDefService SysProcessDefServiceImpl;

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList(String owner) {

		QueryWrapper<SysProcessDef> ew = new QueryWrapper<SysProcessDef>();
		ew.eq("owner", owner);
		return R.SUCCESS_OPER(SysProcessDefServiceImpl.list(ew));

	}

}
