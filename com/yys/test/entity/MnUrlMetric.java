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
@TableName("MN_URL_METRIC")
public class MnUrlMetric extends Model<MnUrlMetric> {

    private static final long serialVersionUID = 1L;

    @TableId("NODE")
    private String node;
    @TableField("NAME")
    private String name;
    @TableField("IS_RUNNING")
    private Double isRunning;
    @TableField("URL")
    private String url;
    @TableField("STATUS")
    private String status;
    @TableField("DR")
    private Double dr;
    @TableField("INTERVAL_TIME")
    private Double intervalTime;
    @TableField("CURCNT")
    private Double curcnt;
    @TableField("MARK")
    private String mark;
    @TableField("THRESHOLD")
    private Double threshold;
    @TableField("MAXWARN")
    private Double maxwarn;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Double isRunning) {
        this.isRunning = isRunning;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
    }

    public Double getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Double intervalTime) {
        this.intervalTime = intervalTime;
    }

    public Double getCurcnt() {
        return curcnt;
    }

    public void setCurcnt(Double curcnt) {
        this.curcnt = curcnt;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Double getMaxwarn() {
        return maxwarn;
    }

    public void setMaxwarn(Double maxwarn) {
        this.maxwarn = maxwarn;
    }

    @Override
    protected Serializable pkVal() {
        return this.node;
    }

    @Override
    public String toString() {
        return "MnUrlMetric{" +
        ", node=" + node +
        ", name=" + name +
        ", isRunning=" + isRunning +
        ", url=" + url +
        ", status=" + status +
        ", dr=" + dr +
        ", intervalTime=" + intervalTime +
        ", curcnt=" + curcnt +
        ", mark=" + mark +
        ", threshold=" + threshold +
        ", maxwarn=" + maxwarn +
        "}";
    }
}
