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
@TableName("MN_NODE_MESSAGE")
public class MnNodeMessage extends Model<MnNodeMessage> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("TYPE")
    private String type;
    @TableField("SEND_TIME")
    private LocalDateTime sendTime;
    @TableField("INSERTTIME")
    private LocalDateTime inserttime;
    @TableField("LV")
    private Double lv;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }

    public Double getLv() {
        return lv;
    }

    public void setLv(Double lv) {
        this.lv = lv;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnNodeMessage{" +
        ", node=" + node +
        ", type=" + type +
        ", sendTime=" + sendTime +
        ", inserttime=" + inserttime +
        ", lv=" + lv +
        "}";
    }
}
