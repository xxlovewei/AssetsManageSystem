package com.dt.module.ops.entity;

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
 * @since 2020-01-24
 */
 
@TableName("ops_node_item")
 
public class OpsNodeItem extends BaseModel<OpsNodeItem> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("nid")
    private String nid;
    @TableField("item")
    private String item;
    @TableField("value")
    private String value;
    @TableField("type")
    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OpsNodeItem{" +
        "id=" + id +
        ", nid=" + nid +
        ", item=" + item +
        ", value=" + value +
        ", type=" + type +
        "}";
    }
}
