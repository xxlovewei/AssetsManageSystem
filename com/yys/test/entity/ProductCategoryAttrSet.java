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
@TableName("PRODUCT_CATEGORY_ATTR_SET")
public class ProductCategoryAttrSet extends Model<ProductCategoryAttrSet> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("ATTR_SET_ID")
    private Long attrSetId;
    @TableField("ATTR_ID")
    private Long attrId;
    @TableField("CAT_ID")
    private Long catId;
    @TableField("VALUE")
    private String value;
    @TableField("OD")
    private Double od;
    @TableField("IS_DELETED")
    private String isDeleted;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAttrSetId() {
        return attrSetId;
    }

    public void setAttrSetId(Long attrSetId) {
        this.attrSetId = attrSetId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getOd() {
        return od;
    }

    public void setOd(Double od) {
        this.od = od;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductCategoryAttrSet{" +
        ", id=" + id +
        ", attrSetId=" + attrSetId +
        ", attrId=" + attrId +
        ", catId=" + catId +
        ", value=" + value +
        ", od=" + od +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
