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
@TableName("HRM_ORG_EMPLOYEE")
public class HrmOrgEmployee extends Model<HrmOrgEmployee> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NODE_ID")
    private String nodeId;
    @TableField("EMPL_ID")
    private String emplId;
    @TableField("DELETED")
    private String deleted;


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

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HrmOrgEmployee{" +
        ", id=" + id +
        ", nodeId=" + nodeId +
        ", emplId=" + emplId +
        ", deleted=" + deleted +
        "}";
    }
}
