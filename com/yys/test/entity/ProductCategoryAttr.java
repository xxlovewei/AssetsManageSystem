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
@TableName("PRODUCT_CATEGORY_ATTR")
public class ProductCategoryAttr extends Model<ProductCategoryAttr> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("ATTR_ID")
    private Long attrId;
    @TableField("CAT_ID")
    private Long catId;
    @TableField("NAME")
    private String name;
    /**
     * input ,single-select, muliti-select
     */
    @TableField("IS_INPUT")
    private String isInput;
    /**
     * string,number,date
     */
    @TableField("IS_KEY")
    private String isKey;
    @TableField("IS_DELETED")
    private String isDeleted;
    @TableField("IS_SEARCH")
    private String isSearch;
    @TableField("CAN_ALIAS")
    private String canAlias;
    @TableField("IS_NEED")
    private String isNeed;
    @TableField("OD")
    private Double od;
    @TableField("IS_ENUM")
    private String isEnum;
    @TableField("IS_USED")
    private String isUsed;
    @TableField("ATTR_TYPE")
    private String attrType;
    @TableField("INPUT_TYPE")
    private String inputType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsInput() {
        return isInput;
    }

    public void setIsInput(String isInput) {
        this.isInput = isInput;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }

    public String getCanAlias() {
        return canAlias;
    }

    public void setCanAlias(String canAlias) {
        this.canAlias = canAlias;
    }

    public String getIsNeed() {
        return isNeed;
    }

    public void setIsNeed(String isNeed) {
        this.isNeed = isNeed;
    }

    public Double getOd() {
        return od;
    }

    public void setOd(Double od) {
        this.od = od;
    }

    public String getIsEnum() {
        return isEnum;
    }

    public void setIsEnum(String isEnum) {
        this.isEnum = isEnum;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductCategoryAttr{" +
        ", id=" + id +
        ", attrId=" + attrId +
        ", catId=" + catId +
        ", name=" + name +
        ", isInput=" + isInput +
        ", isKey=" + isKey +
        ", isDeleted=" + isDeleted +
        ", isSearch=" + isSearch +
        ", canAlias=" + canAlias +
        ", isNeed=" + isNeed +
        ", od=" + od +
        ", isEnum=" + isEnum +
        ", isUsed=" + isUsed +
        ", attrType=" + attrType +
        ", inputType=" + inputType +
        "}";
    }
}
