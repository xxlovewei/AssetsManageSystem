package com.dt.module.cmdb.controller;


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
import com.dt.module.cmdb.entity.ResAttrs;
import com.dt.module.cmdb.service.IResAttrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-06-17
 */
@Controller
@RequestMapping("/api/cmdb/resAttrs/ext")
public class ResAttrsExtController extends BaseController {


	@Autowired
	IResAttrsService ResAttrsServiceImpl;


	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectByCatId.do")
	public R selectById(@RequestParam(value = "catid", required = true, defaultValue = "") String catid) {
		QueryWrapper<ResAttrs> ew = new QueryWrapper<ResAttrs>();
		ew.and(i -> i.eq("catid",catid));
		ew.orderByAsc("sort");
		return R.SUCCESS_OPER(ResAttrsServiceImpl.list(ew));
	}



}

