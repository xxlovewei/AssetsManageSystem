package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杨永生
 * @since 2018-07-22
 */
@TableName("SYS_ROLE_MODULE_BAK")
public class SysRoleModuleBak extends Model<SysRoleModuleBak> {

    private static final long serialVersionUID = 1L;

    @TableField("ROLE_ID")
    private String roleId;
    @TableField("MODULE_ID")
    private Integer moduleId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysRoleModuleBak{" +
        ", roleId=" + roleId +
        ", moduleId=" + moduleId +
        "}";
    }
}
