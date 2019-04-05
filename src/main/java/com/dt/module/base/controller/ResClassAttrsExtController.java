package com.dt.module.base.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.base.entity.ResClassAttrs;
import com.dt.module.base.service.IResClassAttrsService;
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
 * @since 2019-04-04
 */
@Controller
@RequestMapping("/api/base/resClassAttrs")
public class ResClassAttrsExtController extends BaseController {


	@Autowired
	IResClassAttrsService ResClassAttrsServiceImpl;

 

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/selectByClassId.do")
	public R selectByClassId(@RequestParam(value = "classId", required = true, defaultValue = "") String classId) {
		QueryWrapper<ResClassAttrs> ew = new QueryWrapper<ResClassAttrs>();
		ew.and(i -> i.eq("class_id", classId));
		
		return R.SUCCESS_OPER(ResClassAttrsServiceImpl.list(ew));
		
	}
	
	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/selectByClassIdWithAttrCode.do")
	public R selectByClassIdWithAttrCode(@RequestParam(value = "classId", required = true, defaultValue = "") String classId,@RequestParam(value = "attrCode", required = true, defaultValue = "") String attrCode) {
		QueryWrapper<ResClassAttrs> ew = new QueryWrapper<ResClassAttrs>();
		ew.and(i -> i.eq("class_id", classId)).and(i -> i.eq("attr_code", attrCode));
		return R.SUCCESS_OPER(ResClassAttrsServiceImpl.list(ew));
		
	}
	
	 
 


}

