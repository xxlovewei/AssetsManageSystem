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
@TableName("PRODUCT_ATTR_SET")
public class ProductAttrSet extends Model<ProductAttrSet> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("SPU")
    private String spu;
    @TableField("ATTR_SET_ID")
    private Long attrSetId;
    @TableField("ATTR_ID")
    private Long attrId;
    @TableField("ATTR_NAME")
    private String attrName;
    @TableField("IS_SKU")
    private String isSku;
    /**
     * attr_type为input的时候需要填充数据
     */
    @TableField("VALUE")
    private String value;


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

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getIsSku() {
        return isSku;
    }

    public void setIsSku(String isSku) {
        this.isSku = isSku;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductAttrSet{" +
        ", id=" + id +
        ", spu=" + spu +
        ", attrSetId=" + attrSetId +
        ", attrId=" + attrId +
        ", attrName=" + attrName +
        ", isSku=" + isSku +
        ", value=" + value +
        "}";
    }
}
