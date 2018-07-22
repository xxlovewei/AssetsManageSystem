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
@TableName("PRODUCT_PIC")
public class ProductPic extends Model<ProductPic> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("SPU")
    private String spu;
    @TableField("PIC_ID")
    private String picId;
    /**
     * main,normal,detail
     */
    @TableField("TYPE")
    private String type;
    @TableField("OD")
    private String od;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductPic{" +
        ", id=" + id +
        ", spu=" + spu +
        ", picId=" + picId +
        ", type=" + type +
        ", od=" + od +
        "}";
    }
}
