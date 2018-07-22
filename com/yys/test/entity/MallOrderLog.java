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
@TableName("MALL_ORDER_LOG")
public class MallOrderLog extends Model<MallOrderLog> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("ORDER_ID")
    private String orderId;
    @TableField("USER_ID")
    private String userId;
    @TableField("ACTION")
    private String action;
    @TableField("CT")
    private String ct;
    @TableField("CDATE")
    private LocalDateTime cdate;


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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallOrderLog{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", action=" + action +
        ", ct=" + ct +
        ", cdate=" + cdate +
        "}";
    }
}
