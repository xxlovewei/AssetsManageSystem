package com.dt.module.demo.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.module.db.DB;
import com.dt.module.demo.service.DemoService;

/** 
* @author 作者 Lank 
* @version 创建时间：2017年8月2日 上午6:48:36 
* 类说明 
*/
@Controller
@RequestMapping(value = "/api")
public class DemoController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DB db = null;
	@Autowired
	DemoService demoService;
	@Acl(value = Acl.TYPE_ALLOW)
	@Res
	@RequestMapping("/demo/test.do")
	public ResData orgDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// resource type url
		;
		demoService.query();
		System.out.println(HttpKit.getIpAddr(request));
		System.out.println(HttpKit.removeParam(request, "a"));
		System.out.println(HttpKit.getRequestParameters().toString());
		System.out.println("test");
		_log.debug("hello debug");
	 
		_log.info("df", db.query("select 1 from dual"));
		_log.warn("this is warn");
		return ResData.SUCCESS();
	}
	
	@Acl(value = Acl.TYPE_ALLOW)
	@Res
	@RequestMapping(value="/demo/tes2t.do")
	public ResData abc(String id,String value){
		System.out.println("id"+id);
		System.out.println("value"+value);
		if (ToolUtil.isEmpty(id)){
			
			db.execute("select 1 from adf");
			//throw new BizException(BizExceptionEnum.REQUEST_NULL);
		}
		return ResData.SUCCESS("as");
	    
	    
	    
	 
	}
	

}

