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
@TableName("CT_CATEGORY_ROOT")
public class CtCategoryRoot extends Model<CtCategoryRoot> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Double id;
    @TableField("NAME")
    private String name;
    @TableField("TYPE")
    private String type;
    @TableField("MARK")
    private String mark;
    @TableField("OD")
    private Integer od;
    @TableField("DELETED")
    private String deleted;


    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CtCategoryRoot{" +
        ", id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", mark=" + mark +
        ", od=" + od +
        ", deleted=" + deleted +
        "}";
    }
}
