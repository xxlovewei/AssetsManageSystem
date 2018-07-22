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
@TableName("SYS_USER_FUND_REC")
public class SysUserFundRec extends Model<SysUserFundRec> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("USER_ID")
    private String userId;
    @TableField("IS_PLUS")
    private Double isPlus;
    @TableField("AMOUNT")
    private Double amount;
    @TableField("ORDER_ID")
    private String orderId;
    @TableField("TYPE")
    private String type;
    @TableField("STATUS")
    private String status;
    @TableField("OPER_ID")
    private String operId;
    @TableField("DR")
    private Double dr;
    @TableField("MARK")
    private String mark;
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    @TableField("PROCESS_TIME")
    private LocalDateTime processTime;
    @TableField("TITLE")
    private String title;
    @TableField("ORDER_TYPE")
    private String orderType;
    @TableField("ITEM_ID")
    private String itemId;
    @TableField("REASON")
    private String reason;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getIsPlus() {
        return isPlus;
    }

    public void setIsPlus(Double isPlus) {
        this.isPlus = isPlus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUserFundRec{" +
        ", id=" + id +
        ", userId=" + userId +
        ", isPlus=" + isPlus +
        ", amount=" + amount +
        ", orderId=" + orderId +
        ", type=" + type +
        ", status=" + status +
        ", operId=" + operId +
        ", dr=" + dr +
        ", mark=" + mark +
        ", createTime=" + createTime +
        ", processTime=" + processTime +
        ", title=" + title +
        ", orderType=" + orderType +
        ", itemId=" + itemId +
        ", reason=" + reason +
        "}";
    }
}
