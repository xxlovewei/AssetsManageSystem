package com.dt.module.form.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.form.entity.SysForm;
import com.dt.module.form.service.ISysFormService;
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
 * @since 2020-03-28
 */
@Controller
@RequestMapping("/api/form/sysForm/Ext")
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

	 


}

