package com.dt.module.flow.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.NodeEventHandler;
import com.bstek.uflo.process.node.Node;
import com.dt.module.cmdb.entity.ResAction;
import com.dt.module.cmdb.service.IResActionService;
import com.dt.module.cmdb.service.impl.ResActionService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;

/**
 * @author: algernonking
 * @date: Nov 30, 2019 9:04:15 AM
 * @Description: TODO
 */

//流程节点处理
@Component
public class UfloNodeEndEventHandler implements NodeEventHandler {

	@Autowired
	ISysProcessDataService SysProcessDataServiceImpl;

	@Autowired
	IResActionService ResActionServiceImpl;

	@Override
	public void enter(Node node, ProcessInstance processInstance, Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave(Node node, ProcessInstance processInstance, Context context) {
		// TODO Auto-generated method stub

		String busid = processInstance.getBusinessId();

		if (busid != null && !busid.equals("")) {
			//更新流程总表
			UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
			uw.eq("busid", busid);
			uw.set("pstatus", SysUfloProcessService.P_TYPE_FINISH);
			SysProcessDataServiceImpl.update(uw);

			QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
			qw.eq("busid", busid);
			SysProcessData sd = SysProcessDataServiceImpl.getOne(qw);
			String pdtype = sd.getPtype();
			// 流程类型处理
			if (pdtype != null) {
				if (pdtype.equals("LY") || pdtype.equals("JY") || pdtype.equals("ZY")) {
					UpdateWrapper<ResAction> uw2 = new UpdateWrapper<ResAction>();
					uw2.set("spstatus", ResActionService.ACT_STATUS_APPROVALSUCCESS);
					uw2.eq("tplinstid", sd.getProcessInstanceId());
					ResActionServiceImpl.update(uw2);
				}
			}
		}

	}

}
