package com.dt.module.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.entity.ResClassAttrs;
import com.dt.module.base.service.IResClassAttrsService;

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

