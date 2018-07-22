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
@TableName("PRODUCT_CAT_MAP_ATTR")
public class ProductCatMapAttr extends Model<ProductCatMapAttr> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("CAT_ID")
    private Long catId;
    @TableField("USER_CAT_ID")
    private Long userCatId;
    @TableField("ATTR_ID")
    private Long attrId;
    @TableField("ATTR_SET_ID")
    private Long attrSetId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getUserCatId() {
        return userCatId;
    }

    public void setUserCatId(Long userCatId) {
        this.userCatId = userCatId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
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
        return "ProductCatMapAttr{" +
        ", id=" + id +
        ", catId=" + catId +
        ", userCatId=" + userCatId +
        ", attrId=" + attrId +
        ", attrSetId=" + attrSetId +
        "}";
    }
}
