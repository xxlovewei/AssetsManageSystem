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
@TableName("CT_CATEGORY")
public class CtCategory extends Model<CtCategory> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;
    @TableField("ROOT")
    private Double root;
    @TableField("NAME")
    private String name;
    @TableField("MPIC")
    private String mpic;
    @TableField("PARENT_ID")
    private Long parentId;
    @TableField("ROUTE")
    private String route;
    @TableField("MARK")
    private String mark;
    @TableField("NODE_LEVEL")
    private Integer nodeLevel;
    @TableField("OD")
    private Integer od;
    @TableField("ISACTION")
    private String isaction;
    @TableField("DELETED")
    private String deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRoot() {
        return root;
    }

    public void setRoot(Double root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMpic() {
        return mpic;
    }

    public void setMpic(String mpic) {
        this.mpic = mpic;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public String getIsaction() {
        return isaction;
    }

    public void setIsaction(String isaction) {
        this.isaction = isaction;
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
        return "CtCategory{" +
        ", id=" + id +
        ", root=" + root +
        ", name=" + name +
        ", mpic=" + mpic +
        ", parentId=" + parentId +
        ", route=" + route +
        ", mark=" + mark +
        ", nodeLevel=" + nodeLevel +
        ", od=" + od +
        ", isaction=" + isaction +
        ", deleted=" + deleted +
        "}";
    }
}
