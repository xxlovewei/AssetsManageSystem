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
@TableName("MN_SERVICE_NODE_METRIC")
public class MnServiceNodeMetric extends Model<MnServiceNodeMetric> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("SERVICE_ID")
    private String serviceId;
    @TableField("NODE_ID")
    private String nodeId;
    @TableField("METRIC_ID")
    private String metricId;
    @TableField("DATA_INTERVAL")
    private Long dataInterval;
    @TableField("IS_SHOW")
    private String isShow;
    @TableField("MTYPE")
    private String mtype;
    @TableField("V_A_V")
    private String vAV;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public Long getDataInterval() {
        return dataInterval;
    }

    public void setDataInterval(Long dataInterval) {
        this.dataInterval = dataInterval;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getvAV() {
        return vAV;
    }

    public void setvAV(String vAV) {
        this.vAV = vAV;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MnServiceNodeMetric{" +
        ", id=" + id +
        ", serviceId=" + serviceId +
        ", nodeId=" + nodeId +
        ", metricId=" + metricId +
        ", dataInterval=" + dataInterval +
        ", isShow=" + isShow +
        ", mtype=" + mtype +
        ", vAV=" + vAV +
        "}";
    }
}
