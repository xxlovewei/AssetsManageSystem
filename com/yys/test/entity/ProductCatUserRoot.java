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
@TableName("PRODUCT_CAT_USER_ROOT")
public class ProductCatUserRoot extends Model<ProductCatUserRoot> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;
    @TableField("TEXT")
    private String text;
    @TableField("IS_DELETED")
    private String isDeleted;
    @TableField("IS_USED")
    private String isUsed;
    @TableField("MARK")
    private String mark;
    @TableField("CODE")
    private String code;
    @TableField("OD")
    private Double od;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getOd() {
        return od;
    }

    public void setOd(Double od) {
        this.od = od;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductCatUserRoot{" +
        ", id=" + id +
        ", text=" + text +
        ", isDeleted=" + isDeleted +
        ", isUsed=" + isUsed +
        ", mark=" + mark +
        ", code=" + code +
        ", od=" + od +
        "}";
    }
}
