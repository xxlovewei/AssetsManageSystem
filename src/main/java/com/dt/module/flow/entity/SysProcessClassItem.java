package com.dt.module.flow.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2019-11-30
 */
 
@TableName("sys_process_class_item")
 
public class SysProcessClassItem extends BaseModel<SysProcessClassItem> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("mark")
    private String mark;
    @TableField("tpl")
    private String tpl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysProcessClassItem{" +
        "id=" + id +
        ", name=" + name +
        ", mark=" + mark +
        ", tpl=" + tpl +
        "}";
    }
}