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
@TableName("SYS_USER_GROUP")
public class SysUserGroup extends Model<SysUserGroup> {

    private static final long serialVersionUID = 1L;

    @TableId("GROUP_ID")
    private String groupId;
    @TableField("NAME")
    private String name;
    @TableField("SORT")
    private Integer sort;
    @TableField("MARK")
    private String mark;
    @TableField("DELETED")
    private String deleted;


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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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
        ", deleted=" + deleted +
        "}";
    }
}
