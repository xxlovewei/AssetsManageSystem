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
@TableName("SYS_MODULES")
public class SysModules extends Model<SysModules> {

    private static final long serialVersionUID = 1L;

    /**
     * 模块id
     */
    @TableId("MODULE_ID")
    private String moduleId;
    /**
     * 所属最终模块ID
     */
    @TableField("MODULE_ROOTID")
    private String moduleRootid;
    /**
     * key
     */
    @TableField("KEY")
    private String key;
    /**
     * 是否有效
     */
    @TableField("IS_ACTION")
    private String isAction;
    /**
     * system/user
     */
    @TableField("TYPE")
    private String type;
    /**
     * 菜单ID
     */
    @TableField("MENU_ID")
    private String menuId;
    /**
     * 组织ID
     */
    @TableField("ORG_ID")
    private String orgId;
    /**
     * 备注
     */
    @TableField("MARK")
    private String mark;
    /**
     * 名称
     */
    @TableField("MODULE_NAME")
    private String moduleName;
    /**
     * N|Y
     */
    @TableField("DELETED")
    private String deleted;


    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleRootid() {
        return moduleRootid;
    }

    public void setModuleRootid(String moduleRootid) {
        this.moduleRootid = moduleRootid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIsAction() {
        return isAction;
    }

    public void setIsAction(String isAction) {
        this.isAction = isAction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.moduleId;
    }

    @Override
    public String toString() {
        return "SysModules{" +
        ", moduleId=" + moduleId +
        ", moduleRootid=" + moduleRootid +
        ", key=" + key +
        ", isAction=" + isAction +
        ", type=" + type +
        ", menuId=" + menuId +
        ", orgId=" + orgId +
        ", mark=" + mark +
        ", moduleName=" + moduleName +
        ", deleted=" + deleted +
        "}";
    }
}
