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
@TableName("SYS_BANNER_ITEM")
public class SysBannerItem extends Model<SysBannerItem> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("BANNER_ID")
    private String bannerId;
    @TableField("TYPE")
    private String type;
    @TableField("NAME")
    private String name;
    @TableField("PIC_ID")
    private String picId;
    @TableField("CT")
    private String ct;
    @TableField("RK")
    private Double rk;
    @TableField("IS_USED")
    private String isUsed;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public Double getRk() {
        return rk;
    }

    public void setRk(Double rk) {
        this.rk = rk;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysBannerItem{" +
        ", id=" + id +
        ", bannerId=" + bannerId +
        ", type=" + type +
        ", name=" + name +
        ", picId=" + picId +
        ", ct=" + ct +
        ", rk=" + rk +
        ", isUsed=" + isUsed +
        "}";
    }
}
