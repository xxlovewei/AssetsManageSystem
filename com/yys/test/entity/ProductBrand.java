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
@TableName("PRODUCT_BRAND")
public class ProductBrand extends Model<ProductBrand> {

    private static final long serialVersionUID = 1L;

    @TableId("BRAND_ID")
    private String brandId;
    @TableField("BRAND_CODE")
    private String brandCode;
    @TableField("NAME")
    private String name;
    @TableField("IS_DELETED")
    private String isDeleted;
    @TableField("MARK")
    private String mark;
    @TableField("OD")
    private Double od;


    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Double getOd() {
        return od;
    }

    public void setOd(Double od) {
        this.od = od;
    }

    @Override
    protected Serializable pkVal() {
        return this.brandId;
    }

    @Override
    public String toString() {
        return "ProductBrand{" +
        ", brandId=" + brandId +
        ", brandCode=" + brandCode +
        ", name=" + name +
        ", isDeleted=" + isDeleted +
        ", mark=" + mark +
        ", od=" + od +
        "}";
    }
}
