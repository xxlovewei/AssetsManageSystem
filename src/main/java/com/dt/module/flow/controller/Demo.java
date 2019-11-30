package com.dt.module.flow.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskService;
import com.bstek.uflo.utils.EnvironmentUtils;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;

/** 
 * @author: algernonking
 * @date: Nov 30, 2019 8:24:46 AM 
 * @Description: TODO 
 */

@Controller
@RequestMapping("/api")
public class Demo  extends BaseController{

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	
	private ProcessService processService;
	private TaskService taskService;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ProcessService
//		StartProcessInfo startProcessInfo = new StartProcessInfo(EnvironmentUtils.getEnvironment().getLoginUser());
//		startProcessInfo.setBusinessId("12");
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("approveUser","superman");
			
	}
	@RequestMapping("/flow/demo.do")
	@ResponseBody
	@Acl(info = "添加人员")
	public R employeeAdd() {
		 
//		long processId=701;
//		StartProcessInfo startProcessInfo = new StartProcessInfo(EnvironmentUtils.getEnvironment().getLoginUser());
//		startProcessInfo.setCompleteStartTask(true);
//		String variables=req.getParameter("variables");
//		Map<String,Object> variableMaps=buildVariables(variables);
//		if(variableMaps!=null){
//			startProcessInfo.setVariables(variableMaps);
//		}
//		processService.startProcessById(processId, startProcessInfo);
//		processService.s
//		
		
		return R.SUCCESS_OPER();
	}

}

