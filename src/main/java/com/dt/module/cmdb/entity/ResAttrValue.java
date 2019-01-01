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
@TableName("RES_ATTR_VALUE")
public class ResAttrValue extends BaseModel<ResAttrValue> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("RES_ID")
    private String resId;
    @TableField("ATTR_ID")
    private String attrId;
    @TableField("ATTR_VALUE")
    private String attrValue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResAttrValue{" +
        "id=" + id +
        ", resId=" + resId +
        ", attrId=" + attrId +
        ", attrValue=" + attrValue +
        "}";
    }
}
