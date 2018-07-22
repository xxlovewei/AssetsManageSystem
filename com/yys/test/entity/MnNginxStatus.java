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
@TableName("MN_NGINX_STATUS")
public class MnNginxStatus extends Model<MnNginxStatus> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ACTIVE")
    private Long active;
    @TableField("READING")
    private Long reading;
    @TableField("WRITING")
    private Long writing;
    @TableField("WAITING")
    private Long waiting;
    @TableField("ACCEPTS")
    private Long accepts;
    @TableField("HANDLED")
    private Long handled;
    @TableField("REQUESTS")
    private Long requests;
    @TableField("PRODUCETIME")
    private String producetime;
    @TableField("INSERTTIME")
    private LocalDateTime inserttime;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getReading() {
        return reading;
    }

    public void setReading(Long reading) {
        this.reading = reading;
    }

    public Long getWriting() {
        return writing;
    }

    public void setWriting(Long writing) {
        this.writing = writing;
    }

    public Long getWaiting() {
        return waiting;
    }

    public void setWaiting(Long waiting) {
        this.waiting = waiting;
    }

    public Long getAccepts() {
        return accepts;
    }

    public void setAccepts(Long accepts) {
        this.accepts = accepts;
    }

    public Long getHandled() {
        return handled;
    }

    public void setHandled(Long handled) {
        this.handled = handled;
    }

    public Long getRequests() {
        return requests;
    }

    public void setRequests(Long requests) {
        this.requests = requests;
    }

    public String getProducetime() {
        return producetime;
    }

    public void setProducetime(String producetime) {
        this.producetime = producetime;
    }

    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnNginxStatus{" +
        ", node=" + node +
        ", active=" + active +
        ", reading=" + reading +
        ", writing=" + writing +
        ", waiting=" + waiting +
        ", accepts=" + accepts +
        ", handled=" + handled +
        ", requests=" + requests +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
