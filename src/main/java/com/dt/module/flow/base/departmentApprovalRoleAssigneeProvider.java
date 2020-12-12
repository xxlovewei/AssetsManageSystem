package com.dt.module.flow.base;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.dt.core.dao.RcdSet;
import com.dt.module.db.DB;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class departmentApprovalRoleAssigneeProvider implements AssigneeProvider {



    @Override
    public boolean isTree() {
        return false;
    }

    @Override
    public String getName() {
        return "指定-部门角色";
    }

    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String s) {
        List<Entity> list = new ArrayList<Entity>();
        String sql="select c.route_name,b.node,a.id,a.nodeid,a.approvalid from sys_user_approval a,sys_approval_node b,hrm_org_part c where a.dr='0'" +
                "and a.approvalid=b.id and c.node_id=a.nodeid group by c.route_name,b.node,a.id order by c.route_name";
        RcdSet rs=DB.instance().query(sql);
        pageQuery.setPageSize(50);
        for (int i = 0; i < rs.size(); i++) {
            list.add(new Entity(rs.getRcd(i).getString("nodeid")+"-"+rs.getRcd(i).getString("approvalid"), rs.getRcd(i).getString("route_name") + "-" + rs.getRcd(i).getString("node")));
        }
        pageQuery.setResult(list);
        pageQuery.setRecordCount(rs.size());
    }

    @Override
    public Collection<String> getUsers(String entityId, Context context, ProcessInstance processInstance) {
        List<String> list = new ArrayList<String>();
        list.add(entityId);
        return list;
    }

    @Override
    public boolean disable() {
        return false;
    }
}
