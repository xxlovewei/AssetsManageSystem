package com.dt.module.flow.base;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.NodeEventHandler;
import com.bstek.uflo.process.node.Node;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: algernonking
 * @date: Nov 30, 2019 9:04:15 AM
 * @Description: TODO
 */


@Component
public class BaseNodeEventHandler implements NodeEventHandler {


    @Override
    public void enter(Node node, ProcessInstance processInstance, Context context) {
        System.out.println("###BaseNodeEventHandler Enter Node###");
        System.out.println("processInstance.getId()" + processInstance.getId());
        System.out.println("processInstance.getProcessId()" + processInstance.getProcessId());
        System.out.println("processInstance.getBusinessId()" + processInstance.getBusinessId());
        System.out.println("processInstance.getSubject()" + processInstance.getSubject());
        System.out.println("processInstance.getCurrentNode()" + processInstance.getCurrentNode());
        System.out.println("processInstance.getCurrentTask()" + processInstance.getCurrentTask());
        System.out.println("processInstance.getTag()" + processInstance.getTag());
        System.out.println("processInstance.getState()" + processInstance.getState());


        System.out.println("node.getProcessId()" + node.getProcessId());
        System.out.println("node.getName()" + node.getName());
        System.out.println("node.getType()" + node.getType());
        System.out.println("node.getLabel()" + node.getLabel());
        System.out.println("node.getDescription()" + node.getDescription());
        System.out.println("---------------------------------\n\n");
    }


    @Override
    public void leave(Node node, ProcessInstance processInstance, Context context) {
        System.out.println("###BaseNodeEventHandler Leave Node###");
        System.out.println("processInstance.getId()" + processInstance.getId());
        System.out.println("processInstance.getProcessId()" + processInstance.getProcessId());
        System.out.println("processInstance.getBusinessId()" + processInstance.getBusinessId());
        System.out.println("processInstance.getSubject()" + processInstance.getSubject());
        System.out.println("processInstance.getCurrentNode()" + processInstance.getCurrentNode());
        System.out.println("processInstance.getCurrentTask()" + processInstance.getCurrentTask());
        System.out.println("processInstance.getTag()" + processInstance.getTag());
        System.out.println("processInstance.getState()" + processInstance.getState());

        System.out.println("node.getProcessId()" + node.getProcessId());
        System.out.println("node.getName()" + node.getName());
        System.out.println("node.getType()" + node.getType());
        System.out.println("node.getLabel()" + node.getLabel());
        System.out.println("node.getDescription()" + node.getDescription());
        System.out.println("---------------------------------\n\n");
    }

}
