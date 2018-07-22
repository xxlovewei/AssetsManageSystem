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
@TableName("MN_SERVICE")
public class MnService extends Model<MnService> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;
    @TableField("NODE_ID")
    private String nodeId;
    @TableField("STATUS")
    private String status;
    @TableField("TYPE")
    private String type;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("MARK")
    private String mark;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnService{" +
        ", id=" + id +
        ", nodeId=" + nodeId +
        ", status=" + status +
        ", type=" + type +
        ", isDelete=" + isDelete +
        ", mark=" + mark +
        "}";
    }
}
