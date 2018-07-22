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
@TableName("MN_METRIC_WARN_REC")
public class MnMetricWarnRec extends Model<MnMetricWarnRec> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("SERVICE_ID")
    private String serviceId;
    @TableField("NODE_ID")
    private String nodeId;
    @TableField("METRIC_ID")
    private String metricId;
    @TableField("V_A")
    private String vA;
    @TableField("V_A_V")
    private String vAV;
    @TableField("METRIC_NAME")
    private String metricName;
    @TableField("VALUE")
    private String value;
    @TableField("INSERTTIME")
    private String inserttime;
    @TableField("CREATETIME")
    private String createtime;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("IS_PROCESS")
    private String isProcess;
    @TableField("WTYPE")
    private String wtype;
    @TableField("MARK")
    private String mark;


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

    public String getvA() {
        return vA;
    }

    public void setvA(String vA) {
        this.vA = vA;
    }

    public String getvAV() {
        return vAV;
    }

    public void setvAV(String vAV) {
        this.vAV = vAV;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsProcess() {
        return isProcess;
    }

    public void setIsProcess(String isProcess) {
        this.isProcess = isProcess;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MnMetricWarnRec{" +
        ", id=" + id +
        ", serviceId=" + serviceId +
        ", nodeId=" + nodeId +
        ", metricId=" + metricId +
        ", vA=" + vA +
        ", vAV=" + vAV +
        ", metricName=" + metricName +
        ", value=" + value +
        ", inserttime=" + inserttime +
        ", createtime=" + createtime +
        ", isDelete=" + isDelete +
        ", isProcess=" + isProcess +
        ", wtype=" + wtype +
        ", mark=" + mark +
        "}";
    }
}
