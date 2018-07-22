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
@TableName("MN_NODE_INFO")
public class MnNodeInfo extends Model<MnNodeInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE_ID")
    private String nodeId;
    @TableField("NODE_ID1")
    private String nodeId1;
    @TableField("NODE_ID2")
    private String nodeId2;
    @TableField("TYPE")
    private String type;
    @TableField("INFO")
    private String info;
    @TableField("STATUS")
    private String status;
    @TableField("MARK")
    private String mark;
    @TableField("ITIME")
    private LocalDateTime itime;
    @TableField("CTIME")
    private LocalDateTime ctime;
    @TableField("UTIME")
    private LocalDateTime utime;


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId1() {
        return nodeId1;
    }

    public void setNodeId1(String nodeId1) {
        this.nodeId1 = nodeId1;
    }

    public String getNodeId2() {
        return nodeId2;
    }

    public void setNodeId2(String nodeId2) {
        this.nodeId2 = nodeId2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDateTime getItime() {
        return itime;
    }

    public void setItime(LocalDateTime itime) {
        this.itime = itime;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public LocalDateTime getUtime() {
        return utime;
    }

    public void setUtime(LocalDateTime utime) {
        this.utime = utime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnNodeInfo{" +
        ", nodeId=" + nodeId +
        ", nodeId1=" + nodeId1 +
        ", nodeId2=" + nodeId2 +
        ", type=" + type +
        ", info=" + info +
        ", status=" + status +
        ", mark=" + mark +
        ", itime=" + itime +
        ", ctime=" + ctime +
        ", utime=" + utime +
        "}";
    }
}
