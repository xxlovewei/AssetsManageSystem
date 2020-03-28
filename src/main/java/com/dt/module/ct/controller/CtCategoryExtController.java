package com.dt.module.ct.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.ct.entity.CtCategory;
import com.dt.module.ct.service.ICtCategoryService;
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
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-30
 */
@Controller
@RequestMapping("/api/ctCategory/Ext")
public class CtCategoryExtController extends BaseController {


	@Autowired
	ICtCategoryService CtCategoryServiceImpl;

 
	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectListByRoot.do")
	public R selectList(String root) {
		QueryWrapper<CtCategory> ew = new QueryWrapper<CtCategory>();
		ew.and(i -> i.eq("root", root)).orderByAsc("route_name");
		return R.SUCCESS_OPER(CtCategoryServiceImpl.list(ew));
	}

	@ResponseBody
	@Acl(info = "查询所有,有分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectPage.do")
	public R selectPage(String start, String length, @RequestParam(value = "pageSize", required = true, defaultValue = "10")  String pageSize,@RequestParam(value = "pageIndex", required = true, defaultValue = "1")  String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		QueryWrapper<CtCategory> ew = new QueryWrapper<CtCategory>();
		//ew.and(i -> i.eq("user_id", getUserId()).apply(pagesize>10, "rtime>sysdate-1","23"));
		 
		IPage<CtCategory> pdata = CtCategoryServiceImpl.page(new Page<CtCategory>(pageindex, pagesize), ew);
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", pdata.getTotal());
		retrunObject.put("iTotalDisplayRecords", pdata.getTotal());
		retrunObject.put("data", JSONArray.parseArray(JSON.toJSONString(pdata.getRecords(),SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect)));
		return R.clearAttachDirect(retrunObject);
	}


}

