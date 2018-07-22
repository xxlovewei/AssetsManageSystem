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
@TableName("MALL_LOGISTICS")
public class MallLogistics extends Model<MallLogistics> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("SHOP_ID")
    private String shopId;
    @TableField("NAME")
    private String name;
    @TableField("YUNFEI")
    private Integer yunfei;
    @TableField("ADDYUNFEI")
    private Integer addyunfei;
    @TableField("IS_DELETE")
    private String isDelete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYunfei() {
        return yunfei;
    }

    public void setYunfei(Integer yunfei) {
        this.yunfei = yunfei;
    }

    public Integer getAddyunfei() {
        return addyunfei;
    }

    public void setAddyunfei(Integer addyunfei) {
        this.addyunfei = addyunfei;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallLogistics{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", name=" + name +
        ", yunfei=" + yunfei +
        ", addyunfei=" + addyunfei +
        ", isDelete=" + isDelete +
        "}";
    }
}
