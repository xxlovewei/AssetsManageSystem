package com.dt.module.cmdb.entity;

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
 * @since 2018-12-30
 */
@TableName("RES_CLASS_ATTRS")
public class ResClassAttrs extends BaseModel<ResClassAttrs> {

    private static final long serialVersionUID = 1L;

    @TableId("ATTR_ID")
    private String attrId;
    @TableField("ATTR_NAME")
    private String attrName;
    @TableField("ATRR_TYPE")
    private String atrrType;
    @TableField("SORT")
    private String sort;
    @TableField("ATTR_CODE")
    private String attrCode;
    @TableField("CLASS_ID")
    private String classId;


    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAtrrType() {
        return atrrType;
    }

    public void setAtrrType(String atrrType) {
        this.atrrType = atrrType;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    protected Serializable pkVal() {
        return this.attrId;
    }

    @Override
    public String toString() {
        return "ResClassAttrs{" +
        ", attrId=" + attrId +
        ", attrName=" + attrName +
        ", atrrType=" + atrrType +
        ", sort=" + sort +
        ", attrCode=" + attrCode +
        ", classId=" + classId +
        "}";
    }
}
