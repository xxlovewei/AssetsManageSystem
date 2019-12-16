package com.dt.module.ct.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.ct.entity.CtCategoryRoot;
import com.dt.module.ct.service.ICtCategoryRootService;

import java.util.ArrayList;
import java.util.Collection;

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
