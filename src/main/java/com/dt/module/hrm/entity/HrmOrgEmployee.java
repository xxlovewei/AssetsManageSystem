package com.dt.module.hrm.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2020-04-13
 */
 
@TableName("hrm_org_employee")
 
public class HrmOrgEmployee extends BaseModel<HrmOrgEmployee> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 组织节点ID
     */
    @TableField("node_id")
    private String nodeId;
    /**
     * 员工工号
     */
    @TableField("empl_id")
    private String emplId;


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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HrmOrgEmployee{" +
        "id=" + id +
        ", nodeId=" + nodeId +
        ", emplId=" + emplId +
        "}";
    }
}
