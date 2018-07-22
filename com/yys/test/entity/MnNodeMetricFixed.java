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
@TableName("MN_NODE_METRIC_FIXED")
public class MnNodeMetricFixed extends Model<MnNodeMetricFixed> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NODE_ID")
    private String nodeId;
    @TableField("USER_ID")
    private String userId;
    @TableField("METRIC_ID")
    private String metricId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MnNodeMetricFixed{" +
        ", id=" + id +
        ", nodeId=" + nodeId +
        ", userId=" + userId +
        ", metricId=" + metricId +
        "}";
    }
}
