package com.yys.test.entity;

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
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId("SPU")
    private String spu;
    @TableField("PROD_NAME")
    private String prodName;
    @TableField("CAT_ID")
    private Long catId;
    @TableField("LIST_PRICE")
    private Long listPrice;
    @TableField("LIST_ORI_PRICE")
    private Long listOriPrice;
    @TableField("STOCK")
    private Long stock;
    @TableField("CODE")
    private String code;
    @TableField("SALES")
    private Long sales;
    @TableField("IS_OFF")
    private String isOff;
    @TableField("IS_DELETED")
    private String isDeleted;
    @TableField("PROD_DESC")
    private String prodDesc;
    @TableField("BRAND_ID")
    private String brandId;
    @TableField("UNIT")
    private String unit;
    @TableField("TITLE")
    private String title;
    @TableField("PLACE")
    private String place;
    @TableField("PIC_ID")
    private String picId;
    @TableField("SHOP_ID")
    private String shopId;
    @TableField("GOODREPUTATION")
    private Long goodreputation;
    @TableField("BONUSPOINTS")
    private Long bonuspoints;
    @TableField("MOBILE_PROFILE_HTML")
    private String mobileProfileHtml;
    /**
     * 是否需要物流信息0不需要 1需要
     */
    @TableField("ISNEEDLOGISTICS")
    private Double isneedlogistics;
    @TableField("WEIGHT")
    private Integer weight;
    @TableField("CDATE")
    private LocalDateTime cdate;


    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getListPrice() {
        return listPrice;
    }

    public void setListPrice(Long listPrice) {
        this.listPrice = listPrice;
    }

    public Long getListOriPrice() {
        return listOriPrice;
    }

    public void setListOriPrice(Long listOriPrice) {
        this.listOriPrice = listOriPrice;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public String getIsOff() {
        return isOff;
    }

    public void setIsOff(String isOff) {
        this.isOff = isOff;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getGoodreputation() {
        return goodreputation;
    }

    public void setGoodreputation(Long goodreputation) {
        this.goodreputation = goodreputation;
    }

    public Long getBonuspoints() {
        return bonuspoints;
    }

    public void setBonuspoints(Long bonuspoints) {
        this.bonuspoints = bonuspoints;
    }

    public String getMobileProfileHtml() {
        return mobileProfileHtml;
    }

    public void setMobileProfileHtml(String mobileProfileHtml) {
        this.mobileProfileHtml = mobileProfileHtml;
    }

    public Double getIsneedlogistics() {
        return isneedlogistics;
    }

    public void setIsneedlogistics(Double isneedlogistics) {
        this.isneedlogistics = isneedlogistics;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.spu;
    }

    @Override
    public String toString() {
        return "Product{" +
        ", spu=" + spu +
        ", prodName=" + prodName +
        ", catId=" + catId +
        ", listPrice=" + listPrice +
        ", listOriPrice=" + listOriPrice +
        ", stock=" + stock +
        ", code=" + code +
        ", sales=" + sales +
        ", isOff=" + isOff +
        ", isDeleted=" + isDeleted +
        ", prodDesc=" + prodDesc +
        ", brandId=" + brandId +
        ", unit=" + unit +
        ", title=" + title +
        ", place=" + place +
        ", picId=" + picId +
        ", shopId=" + shopId +
        ", goodreputation=" + goodreputation +
        ", bonuspoints=" + bonuspoints +
        ", mobileProfileHtml=" + mobileProfileHtml +
        ", isneedlogistics=" + isneedlogistics +
        ", weight=" + weight +
        ", cdate=" + cdate +
        "}";
    }
}
