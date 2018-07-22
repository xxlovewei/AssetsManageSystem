package com.yys.test.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.yys.test.entity.Dtuser;
import com.yys.test.service.IDtuserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-22
 */
@Controller
@RequestMapping("/dtuser")
public class Dt3userController extends BaseController{

	

	@Autowired
	IDtuserService dtuserService;
	
	
	

	@ResponseBody
	@Acl(info = "网页授权跳转", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/selectbyid.do")
	public R test(String id) {
 
		return R.SUCCESS_OPER(dtuserService.selectById(id));
	}
	
	@ResponseBody
	@Acl(info = "网页授权跳转", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/select.do")
	public R select(String id) {
		
	 
		return R.SUCCESS_OPER(dtuserService.selectList(null)  );
	}
	
	@ResponseBody
	@Acl(info = "网页授权跳转", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/query.do")
	public String query(Dtuser user) {
		 
		dtuserService.insert(user);
		//System.out.println(dtuserService.selectById(id).toString());
		return "";
	}
	
	
	
}

