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
@TableName("HRM_ORG_PART")
public class HrmOrgPart extends Model<HrmOrgPart> {

    private static final long serialVersionUID = 1L;

    @TableId("NODE_ID")
    private Integer nodeId;
    @TableField("NODE_NAME")
    private String nodeName;
    @TableField("ORG_ID")
    private Integer orgId;
    @TableField("PARENT_ID")
    private Integer parentId;
    @TableField("ROUTE")
    private String route;
    @TableField("TYPE")
    private String type;
    @TableField("ROUTE_NAME")
    private String routeName;


    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.nodeId;
    }

    @Override
    public String toString() {
        return "HrmOrgPart{" +
        ", nodeId=" + nodeId +
        ", nodeName=" + nodeName +
        ", orgId=" + orgId +
        ", parentId=" + parentId +
        ", route=" + route +
        ", type=" + type +
        ", routeName=" + routeName +
        "}";
    }
}
