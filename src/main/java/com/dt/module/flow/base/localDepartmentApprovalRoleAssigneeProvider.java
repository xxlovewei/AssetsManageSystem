package com.dt.module.flow.base;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.dt.core.dao.RcdSet;
import com.dt.module.base.entity.SysUserApproval;
import com.dt.module.base.service.ISysUserApprovalService;
import com.dt.module.db.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class localDepartmentApprovalRoleAssigneeProvider implements AssigneeProvider {
    @Autowired
    ISysUserApprovalService SysUserApprovalServiceImpl;

    @Override
    public boolean isTree() {
        return false;
    }

    @Override
    public String getName() {
        return "指定-本部门角色";
    }



    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String s) {
        List<Entity> list = new ArrayList<Entity>();
        String sql="select id,node from sys_approval_node where dr='0'";
        RcdSet rs= DB.instance().query(sql);
        pageQuery.setPageSize(50);
        for (int i = 0; i < rs.size(); i++) {
            list.add(new Entity(rs.getRcd(i).getString("id"), rs.getRcd(i).getString("node")));
        }
        pageQuery.setResult(list);
        pageQuery.setRecordCount(rs.size());
    }

    @Override
    public Collection<String> getUsers(String entityId, Context context, ProcessInstance processInstance) {
        List<String> list = new ArrayList<String>();
        String suser=processInstance.getPromoter();
        System.out.println("promoter:"+suser);
        System.out.println("entityId:"+entityId);
        System.out.println("className:"+this.getName());
        System.out.println("processInstance:"+processInstance.getBusinessId());
      //SysUserApproval node=SysUserApprovalServiceImpl.getById(entityId);
        list.add("1310090019822751746");
        return list;
    }

    @Override
    public boolean disable() {
        return false;
    }
}
