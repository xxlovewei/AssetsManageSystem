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
 * @since 2020-04-04
 */

@TableName("sys_process_def")

public class SysProcessDef extends BaseModel<SysProcessDef> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("mark")
    private String mark;
    /**
     * stop,normal
     */
    @TableField("status")
    private String status;
    @TableField("ptplid")
    private String ptplid;
    @TableField("form")
    private String form;
    @TableField("ptplname")
    private String ptplname;
    @TableField("owner")
    private String owner;
    @TableField("ptplkey")
    private String ptplkey;
    @TableField("type")
    private String type;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPtplid() {
        return ptplid;
    }

    public void setPtplid(String ptplid) {
        this.ptplid = ptplid;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getPtplname() {
        return ptplname;
    }

    public void setPtplname(String ptplname) {
        this.ptplname = ptplname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPtplkey() {
        return ptplkey;
    }

    public void setPtplkey(String ptplkey) {
        this.ptplkey = ptplkey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysProcessDef{" +
                "id=" + id +
                ", name=" + name +
                ", mark=" + mark +
                ", status=" + status +
                ", ptplid=" + ptplid +
                ", form=" + form +
                ", ptplname=" + ptplname +
                ", owner=" + owner +
                ", ptplkey=" + ptplkey +
                ", type=" + type +
                "}";
    }
}
