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
@TableName("PRODUCT_SKU")
public class ProductSku extends Model<ProductSku> {

    private static final long serialVersionUID = 1L;

    @TableId("SKU")
    private String sku;
    /**
     * sku 很少变动
     */
    @TableField("SPU")
    private String spu;
    @TableField("SKU_UUID")
    private String skuUuid;
    @TableField("CODE")
    private String code;
    @TableField("PRICE")
    private Long price;
    @TableField("STOCK")
    private Long stock;
    @TableField("IS_OFF")
    private String isOff;


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getSkuUuid() {
        return skuUuid;
    }

    public void setSkuUuid(String skuUuid) {
        this.skuUuid = skuUuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getIsOff() {
        return isOff;
    }

    public void setIsOff(String isOff) {
        this.isOff = isOff;
    }

    @Override
    protected Serializable pkVal() {
        return this.sku;
    }

    @Override
    public String toString() {
        return "ProductSku{" +
        ", sku=" + sku +
        ", spu=" + spu +
        ", skuUuid=" + skuUuid +
        ", code=" + code +
        ", price=" + price +
        ", stock=" + stock +
        ", isOff=" + isOff +
        "}";
    }
}
