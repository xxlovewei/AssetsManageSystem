package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("MALL_SHOP")
public class MallShop extends Model<MallShop> {

    private static final long serialVersionUID = 1L;

    @TableField("SHOP_ID")
    private String shopId;
    @TableField("SHOP_NAME")
    private String shopName;
    @TableField("DELETED")
    private String deleted;
    @TableField("STATUS")
    private String status;
    @TableField("LOGO")
    private String logo;
    @TableField("MARK")
    private String mark;
    @TableField("CREATETIME")
    private LocalDateTime createtime;


    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MallShop{" +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", deleted=" + deleted +
        ", status=" + status +
        ", logo=" + logo +
        ", mark=" + mark +
        ", createtime=" + createtime +
        "}";
    }
}
