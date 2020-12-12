package com.dt.module.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bstek.uflo.process.assign.Assignee;
import com.dt.core.common.base.BaseService;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.module.base.entity.SysUserInfo;
import com.dt.module.base.service.ISysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BaseFlowService extends BaseService {

    @Autowired
    ISysUserInfoService SysUserInfoServiceImpl;

    public static String DEPARTMENT_APPROVAL_ROLE="departmentApprovalRoleAssigneeProvider";
    public static String LOCALDEPARTMENT_APPROVAL_ROLE="localDepartmentApprovalRoleAssigneeProvider";
    public static String APPOINTDEPARTMENT_APPROVAL_ROLE="appointDepartmentApprovalRoleAssigneeProvider";


    public Assignee queryAssignee(String busid,Assignee roleassignee,String promoter,String nodeid){
        System.out.println("Start to queryAssigneeByRole");
        System.out.println("assignee_id:"+roleassignee.getId());
        System.out.println("assignee_name:"+roleassignee.getName());
        System.out.println("assignee_providerId:"+roleassignee.getProviderId());
        System.out.println("promoter:"+promoter);
        System.out.println("nodeid:"+nodeid);
        Assignee assignee=new Assignee();
        assignee.setProviderId("ufloUserInfoAssigneeProvider");

        SysUserInfo assigneeUser;
        QueryWrapper<SysUserInfo> q=new QueryWrapper<SysUserInfo>();

        if(DEPARTMENT_APPROVAL_ROLE.equals(roleassignee.getProviderId())){
            //指定-部门角色
            System.out.println("固定部门角色");
            String id=roleassignee.getId();
            String nodeid2=id.split("-")[0];
            String approvalid2=id.split("-")[1];
            q.inSql("user_id",  "select userid from sys_user_approval where approvalid='"+approvalid2+"' and dr='0' and nodeid='"+nodeid2+"' ");
            assigneeUser=SysUserInfoServiceImpl.getOne(q);
            if(assigneeUser!=null){
                System.out.println("未代理");
                assignee.setId(assigneeUser.getUserId());
                assignee.setName(assigneeUser.getName());
            }else{
                System.out.println("代理");
                assignee.setId("1310090019822751746");
                assignee.setName("默认");
            }
        }else if(LOCALDEPARTMENT_APPROVAL_ROLE.equals(roleassignee.getProviderId())){
            System.out.println("本部门角色");
            //指定-本部门角色
            String sql="select * from sys_user_info where dr='0' and user_id in ( select userid from sys_user_approval where approvalid='"+roleassignee.getId()+"'\n" +
                    "  and dr='0' and nodeid in (select node_id from hrm_org_employee where empl_id in\n" +
                    "               (select empl_id from sys_user_info where user_id='"+promoter+"')))";
            System.out.println("SQL:"+sql);
            RcdSet user=db.query(sql);
            if(user.size()>0){
                System.out.println("未代理");
                if(user.size()>1){
                    System.out.println("存在多个,指定第一个");
                }
                assignee.setId(user.getRcd(0).getString("user_id"));
                assignee.setName(user.getRcd(0).getString("name"));
            }else{
                System.out.println("代理");
                assignee.setId("1310090019822751746");
                assignee.setName("默认");
            }
        }else if(APPOINTDEPARTMENT_APPROVAL_ROLE.equals(roleassignee.getProviderId())){
            //指定-指定角色
            System.out.println("指定部门角色");
            String sql="select * from sys_user_info where dr='0' and user_id in (select userid from sys_user_approval where approvalid='"+roleassignee.getId()+"' and dr='0' and nodeid='"+nodeid+"') ";
            System.out.println("SQL:"+sql);
            RcdSet user=db.query(sql);
            if(user.size()>0){
                System.out.println("未代理");
                if(user.size()>1){
                    System.out.println("存在多个,指定第一个");
                }
                assignee.setId(user.getRcd(0).getString("user_id"));
                assignee.setName(user.getRcd(0).getString("name"));
            }else{
                System.out.println("代理");
                assignee.setId("1310090019822751746");
                assignee.setName("默认");
            }
        }else{
            assignee=roleassignee;
        }
        return assignee;
    }
}
