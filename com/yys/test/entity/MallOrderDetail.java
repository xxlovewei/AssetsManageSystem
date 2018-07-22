package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("MALL_ORDER_DETAIL")
public class MallOrderDetail extends Model<MallOrderDetail> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("ORDER_ID")
    private String orderId;
    @TableField("SPU")
    private String spu;
    @TableField("BUY_NUMBER")
    private Long buyNumber;
    @TableField("PIC_ID")
    private String picId;
    @TableField("PROD_NAME")
    private String prodName;
    @TableField("PRICE")
    private String price;
    @TableField("SKU")
    private String sku;
    @TableField("PROPERTYCHILDIDS")
    private String propertychildids;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("LABEL")
    private String label;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("REPUTATION")
    private Long reputation;
    @TableField("CT_EVALUATE")
    private String ctEvaluate;
    @TableField("SHOP_ID")
    private String shopId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public Long getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Long buyNumber) {
        this.buyNumber = buyNumber;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPropertychildids() {
        return propertychildids;
    }

    public void setPropertychildids(String propertychildids) {
        this.propertychildids = propertychildids;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }

    public String getCtEvaluate() {
        return ctEvaluate;
    }

    public void setCtEvaluate(String ctEvaluate) {
        this.ctEvaluate = ctEvaluate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallOrderDetail{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", spu=" + spu +
        ", buyNumber=" + buyNumber +
        ", picId=" + picId +
        ", prodName=" + prodName +
        ", price=" + price +
        ", sku=" + sku +
        ", propertychildids=" + propertychildids +
        ", isDelete=" + isDelete +
        ", label=" + label +
        ", cdate=" + cdate +
        ", reputation=" + reputation +
        ", ctEvaluate=" + ctEvaluate +
        ", shopId=" + shopId +
        "}";
    }
}
