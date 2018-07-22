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
@TableName("SYS_MENUS")
public class SysMenus extends Model<SysMenus> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单
     */
    @TableId("MENU_ID")
    private String menuId;
    /**
     * 菜单名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 备注
     */
    @TableField("MARK")
    private String mark;
    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;
    /**
     * 多模式
     */
    @TableField("ORG_ID")
    private String orgId;
    /**
     * PC |
     */
    @TableField("TYPE")
    private String type;
    @TableField("USED")
    private Double used;
    @TableField("DR")
    private Double dr;


    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
    }

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public String toString() {
        return "SysMenus{" +
        ", menuId=" + menuId +
        ", name=" + name +
        ", mark=" + mark +
        ", sort=" + sort +
        ", orgId=" + orgId +
        ", type=" + type +
        ", used=" + used +
        ", dr=" + dr +
        "}";
    }
}
