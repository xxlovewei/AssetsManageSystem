package com.dt.module.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2018-07-27
 */
@TableName("SYS_USER_GROUP")
public class SysUserGroup extends BaseModel<SysUserGroup> {

    private static final long serialVersionUID = 1L;

    @TableId("GROUP_ID")
    private String groupId;
    @TableField("NAME")
    private String name;
    @TableField("SORT")
    private String sort;
    @TableField("MARK")
    private String mark;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.groupId;
    }

    @Override
    public String toString() {
        return "SysUserGroup{" +
        ", groupId=" + groupId +
        ", name=" + name +
        ", sort=" + sort +
        ", mark=" + mark +
        "}";
    }
}
