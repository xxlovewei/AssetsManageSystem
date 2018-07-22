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
@TableName("MN_NODE_HEARTBEAT")
public class MnNodeHeartbeat extends Model<MnNodeHeartbeat> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("STATUS")
    private String status;
    @TableField("UPDATETIME")
    private LocalDateTime updatetime;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnNodeHeartbeat{" +
        ", node=" + node +
        ", status=" + status +
        ", updatetime=" + updatetime +
        "}";
    }
}
