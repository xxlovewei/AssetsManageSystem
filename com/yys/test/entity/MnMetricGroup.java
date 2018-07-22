package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("MN_METRIC_GROUP")
public class MnMetricGroup extends Model<MnMetricGroup> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;
    @TableField("METRIC_ID")
    private String metricId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnMetricGroup{" +
        ", id=" + id +
        ", metricId=" + metricId +
        "}";
    }
}
