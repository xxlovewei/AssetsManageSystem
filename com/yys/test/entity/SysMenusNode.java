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
@TableName("SYS_MENUS_NODE")
public class SysMenusNode extends Model<SysMenusNode> {

    private static final long serialVersionUID = 1L;

    /**
     * 用做module_id
     */
    @TableId("NODE_ID")
    private Integer nodeId;
    @TableField("NODE_NAME")
    private String nodeName;
    @TableField("PARENT_ID")
    private Integer parentId;
    /**
     * 不在使用
     */
    @TableField("MODULE_ID")
    private String moduleId;
    /**
     * 不同功能树
     */
    @TableField("MENU_ID")
    private String menuId;
    @TableField("MARK")
    private String mark;
    @TableField("LOGO")
    private String logo;
    @TableField("MENU_LEVEL")
    private Integer menuLevel;
    @TableField("ORG_ID")
    private String orgId;
    @TableField("SORT")
    private Integer sort;
    @TableField("KEYVALUE")
    private String keyvalue;
    @TableField("ROUTE")
    private String route;
    @TableField("IS_ACTION")
    private String isAction;
    /**
     * N|Y
     */
    @TableField("DELETED")
    private String deleted;
    /**
     * N|Y
     */
    @TableField("IS_G_SHOW")
    private String isGShow;
    /**
     * menus api
     */
    @TableField("TYPE")
    private String type;
    @TableField("ROUTE_NAME")
    private String routeName;


    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getIsAction() {
        return isAction;
    }

    public void setIsAction(String isAction) {
        this.isAction = isAction;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getIsGShow() {
        return isGShow;
    }

    public void setIsGShow(String isGShow) {
        this.isGShow = isGShow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.nodeId;
    }

    @Override
    public String toString() {
        return "SysMenusNode{" +
        ", nodeId=" + nodeId +
        ", nodeName=" + nodeName +
        ", parentId=" + parentId +
        ", moduleId=" + moduleId +
        ", menuId=" + menuId +
        ", mark=" + mark +
        ", logo=" + logo +
        ", menuLevel=" + menuLevel +
        ", orgId=" + orgId +
        ", sort=" + sort +
        ", keyvalue=" + keyvalue +
        ", route=" + route +
        ", isAction=" + isAction +
        ", deleted=" + deleted +
        ", isGShow=" + isGShow +
        ", type=" + type +
        ", routeName=" + routeName +
        "}";
    }
}
