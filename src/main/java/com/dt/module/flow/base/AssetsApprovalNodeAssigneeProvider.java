package com.dt.module.flow.base;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.zc.entity.ResApprovalnode;
import com.dt.module.zc.service.IResApprovalnodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AssetsApprovalNodeAssigneeProvider implements AssigneeProvider {

    @Autowired
    IResApprovalnodeService ResApprovalnodeServiceImpl;


    @Override
    public boolean isTree() {
        return false;
    }

    @Override
    public String getName() {
        return "指定资产审批节点";
    }

    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String s) {
        List<Entity> list = new ArrayList<Entity>();
        pageQuery.setPageIndex(1);
        pageQuery.setPageSize(30);
        pageQuery.setResult(list);
        List<ResApprovalnode> dlist = ResApprovalnodeServiceImpl.list(null);
        for (int i = 0; i < dlist.size(); i++) {
            list.add(new Entity(dlist.get(i).getId(), dlist.get(i).getName() + "-" + dlist.get(i).getUsername()));
        }
        pageQuery.setRecordCount(list.size());
    }

    @Override
    public Collection<String> getUsers(String entityId, Context context, ProcessInstance processInstance) {
        List<String> list = new ArrayList<String>();
        ResApprovalnode node = ResApprovalnodeServiceImpl.getById(entityId);
        if (node != null) {
            list.add(node.getUserid());
        }
        return list;
    }

    @Override
    public boolean disable() {
        return false;
    }
}
