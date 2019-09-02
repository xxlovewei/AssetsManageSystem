package com.dt.module.flow.base;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ProcessEventHandler;

/**
 * @author: algernonking
 * @date: Sep 2, 2019 11:10:47 AM
 * @Description: TODO
 */
public class FlowProcessEventHandler implements ProcessEventHandler {

	/*
	 * (non Javadoc)
	 * 
	 * @Title: end
	 * 
	 * @Description: TODO
	 * 
	 * @param arg0
	 * 
	 * @param arg1
	 * 
	 * @see
	 * com.bstek.uflo.process.handler.ProcessEventHandler#end(com.bstek.uflo.model.
	 * ProcessInstance, com.bstek.uflo.env.Context)
	 */
	@Override
	public void end(ProcessInstance arg0, Context arg1) {
		// TODO Auto-generated method stub

		System.out.println("end " + arg0.getCurrentNode() + ",'" + arg0.getCurrentTask());

	}

	@Override
	public void start(ProcessInstance arg0, Context arg1) {
		System.out.println("start " + arg0.getCurrentNode() + ",'" + arg0.getCurrentTask());
	}

}
