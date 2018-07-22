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
@TableName("MN_OS_NETFLOW")
public class MnOsNetflow extends Model<MnOsNetflow> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("NETINFO")
    private String netinfo;
    @TableField("NETIN")
    private Double netin;
    @TableField("NETOUT")
    private Double netout;
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

    public String getNetinfo() {
        return netinfo;
    }

    public void setNetinfo(String netinfo) {
        this.netinfo = netinfo;
    }

    public Double getNetin() {
        return netin;
    }

    public void setNetin(Double netin) {
        this.netin = netin;
    }

    public Double getNetout() {
        return netout;
    }

    public void setNetout(Double netout) {
        this.netout = netout;
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
        return "MnOsNetflow{" +
        ", node=" + node +
        ", netinfo=" + netinfo +
        ", netin=" + netin +
        ", netout=" + netout +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
