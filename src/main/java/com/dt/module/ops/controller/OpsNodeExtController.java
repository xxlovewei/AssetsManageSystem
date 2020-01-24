package com.dt.module.ops.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.ops.entity.OpsNode;
import com.dt.module.ops.service.IOpsNodeService;
import com.dt.module.ops.service.impl.OpsNodeExtServiceImpl;

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
 * @since 2020-01-24
 */
@Controller
@RequestMapping("/api/ops/opsNode/Ext")
public class OpsNodeExtController extends BaseController {

	@Autowired
	IOpsNodeService OpsNodeServiceImpl;

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(OpsNode entity) {
		return R.SUCCESS_OPER(OpsNodeServiceImpl.saveOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList(String search) {
		String sql = OpsNodeExtServiceImpl.sql;
		if (ToolUtil.isNotEmpty(search)) {
			sql = sql + " and (name like '%" + search + "%' or ip like '%" + search + "%' or leader like '%" + search
					+ "%' or mark like '%" + search + "%')";
		}
		sql = sql + " order by name";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

}
