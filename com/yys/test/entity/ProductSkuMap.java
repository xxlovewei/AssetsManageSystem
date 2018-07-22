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
@TableName("PRODUCT_SKU_MAP")
public class ProductSkuMap extends Model<ProductSkuMap> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("SPU")
    private String spu;
    @TableField("SKU")
    private String sku;
    @TableField("ATTR_SET_ID")
    private Long attrSetId;


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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getAttrSetId() {
        return attrSetId;
    }

    public void setAttrSetId(Long attrSetId) {
        this.attrSetId = attrSetId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductSkuMap{" +
        ", id=" + id +
        ", spu=" + spu +
        ", sku=" + sku +
        ", attrSetId=" + attrSetId +
        "}";
    }
}
