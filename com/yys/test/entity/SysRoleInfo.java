package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("SYS_ROLE_INFO")
public class SysRoleInfo extends Model<SysRoleInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("ROLE_ID")
    private String roleId;
    @TableField("ROLE_NAME")
    private String roleName;
    @TableField("MARK")
    private String mark;
    @TableField("ORG_ID")
    private String orgId;
    @TableField("DELETED")
    private String deleted;
    @TableField("IS_ACTION")
    private String isAction;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getIsAction() {
        return isAction;
    }

    public void setIsAction(String isAction) {
        this.isAction = isAction;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

    @Override
    public String toString() {
        return "SysRoleInfo{" +
        ", roleId=" + roleId +
        ", roleName=" + roleName +
        ", mark=" + mark +
        ", orgId=" + orgId +
        ", deleted=" + deleted +
        ", isAction=" + isAction +
        "}";
    }
}
