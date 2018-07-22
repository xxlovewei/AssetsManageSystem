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
@TableName("PRODUCT_CATEGORY")
public class ProductCategory extends Model<ProductCategory> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;
    @TableField("PARENT_ID")
    private Long parentId;
    @TableField("TEXT")
    private String text;
    @TableField("IS_DELETED")
    private String isDeleted;
    @TableField("IS_CAT")
    private String isCat;
    @TableField("ROUTE")
    private String route;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getIsCat() {
        return isCat;
    }

    public void setIsCat(String isCat) {
        this.isCat = isCat;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", text=" + text +
        ", isDeleted=" + isDeleted +
        ", isCat=" + isCat +
        ", route=" + route +
        "}";
    }
}
