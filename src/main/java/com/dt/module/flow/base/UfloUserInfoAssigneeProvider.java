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
public class UfloUserInfoAssigneeProvider implements AssigneeProvider {

    @Autowired
    private ISysUserInfoService SysUserInfoServiceImpl;

    @Override
    public boolean isTree() {
        return false;
    }

    @Override
    public String getName() {
        return "所有用户列表";
    }


    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {

        pageQuery.setPageSize(50);
        int index = pageQuery.getPageIndex();
        int size = pageQuery.getPageSize();
        int pagesize = size;
        int pageindex = index;
        IPage<SysUserInfo> pdata = SysUserInfoServiceImpl.page(new Page<SysUserInfo>(pageindex, pagesize));
        List<Entity> entitys = new ArrayList<Entity>();
        for (SysUserInfo userinfo : pdata.getRecords()) {
            entitys.add(new Entity(userinfo.getUserId(), userinfo.getName()));
        }
        System.out.println("pageindex" + pageindex + ",pagesize" + pagesize);

        pageQuery.setResult(entitys);
        pageQuery.setRecordCount(SysUserInfoServiceImpl.count());

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
