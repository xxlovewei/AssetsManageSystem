package com.dt.core.common.base;

import com.dt.core.shiro.ShiroKit;
import com.dt.core.shiro.ShiroUser;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.db.DB;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: algernonking
 * @date: 2017年8月26日 上午8:08:57
 * @Description: TODO
 */
public class BaseSC {
    @Autowired
    public DB db = null;

    public String getUserId() {
        String user_id = (String) HttpKit.getRequest().getSession().getAttribute("user_id");
        if (ToolUtil.isEmpty(user_id)) {
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser != null) {
                HttpKit.getRequest().getSession().setAttribute("user_id", shiroUser.getId());
                return shiroUser.getId();
            }
        }
        return user_id;
    }

    public String getUserName() {
        String name = (String) HttpKit.getRequest().getSession().getAttribute("name");
        if (ToolUtil.isEmpty(name)) {
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser != null) {
                HttpKit.getRequest().getSession().setAttribute("user_id", shiroUser.getName());
                return shiroUser.getName();
            }
        }
        return name;
    }
}
