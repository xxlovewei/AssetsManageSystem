package com.dt.module.flow.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.dt.module.base.entity.SysUserInfo;
import com.dt.module.base.service.ISysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: algernonking
 * @date: Nov 30, 2019 9:34:11 AM
 * @Description: TODO
 */

//指定流程参与人
@Component
public class UfloUserPositionAssigneeProvider implements AssigneeProvider {

    @Autowired
    private ISysUserInfoService SysUserInfoServiceImpl;

    /*
     * (non Javadoc)
     *
     * @Title: isTree
     *
     * @Description: TODO
     *
     * @return
     *
     * @see com.bstek.uflo.process.assign.AssigneeProvider#isTree()
     */
    @Override
    public boolean isTree() {
        return false;
    }

    @Override
    public String getName() {
        return "岗位列表";
    }

    /*
     * (non Javadoc)
     *
     * @Title: queryEntities
     *
     * @Description: TODO
     *
     * @param pageQuery
     *
     * @param parentId
     *
     * @see
     * com.bstek.uflo.process.assign.AssigneeProvider#queryEntities(com.bstek.uflo.
     * process.assign.PageQuery, java.lang.String)
     */
    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {

//        pageQuery.setPageIndex(1);
        pageQuery.setPageSize(50);
        int index = pageQuery.getPageIndex();
        int size = pageQuery.getPageSize();
        int pagesize = size;
        int pageindex = index;
        List<Entity> entitys = new ArrayList<Entity>();
        entitys.add(new Entity("CreditLeader", "信审组长"));
        entitys.add(new Entity("QADirector", "质检主管"));
        entitys.add(new Entity("CreditDirector", "信审主管"));
        entitys.add(new Entity("QAManager", "质检经理"));
        entitys.add(new Entity("CreditManager", "信审经理"));
        pageQuery.setResult(entitys);
        pageQuery.setRecordCount(entitys.size());

    }


    @Override
    public Collection<String> getUsers(String entityId, Context context, ProcessInstance processInstance) {
        List<String> list = new ArrayList<String>();
        list.add(entityId);
        return list;
    }

    @Override
    public boolean disable() {
        return true;
    }

}
