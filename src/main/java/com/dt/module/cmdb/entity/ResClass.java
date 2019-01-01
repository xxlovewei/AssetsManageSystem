package com.dt.module.cmdb.entity;

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
 * @since 2018-12-30
 */
@TableName("RES_CLASS")
public class ResClass extends BaseModel<ResClass> {

    private static final long serialVersionUID = 1L;

    @TableId("CLASS_ID")
    private String classId;
    @TableField("CLASS_CODE")
    private String classCode;
    @TableField("PID")
    private String pid;
    @TableField("NAME")
    private String name;
    @TableField("TYPE")
    private String type;
    @TableField("IMG")
    private String img;
    @TableField("STATUS")
    private String status;
    @TableField("SORT")
    private String sort;
    @TableField("SUBTYPE")
    private String subtype;


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    @Override
    protected Serializable pkVal() {
        return this.classId;
    }

    @Override
    public String toString() {
        return "ResClass{" +
        "classId=" + classId +
        ", classCode=" + classCode +
        ", pid=" + pid +
        ", name=" + name +
        ", type=" + type +
        ", img=" + img +
        ", status=" + status +
        ", sort=" + sort +
        ", subtype=" + subtype +
        "}";
    }
}
