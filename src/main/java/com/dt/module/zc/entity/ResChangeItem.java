package com.dt.module.zc.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2020-04-26
 */
 
@TableName("res_change_item")
 
public class ResChangeItem extends BaseModel<ResChangeItem> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;
    @TableField("resid")
    private String resid;
    @TableField("busuuid")
    private String busuuid;
    @TableField("type")
    private String type;
    @TableField("mark")
    private String mark;
    @TableField("fct")
    private String fct;
    @TableField("tct")
    private String tct;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getBusuuid() {
        return busuuid;
    }

    public void setBusuuid(String busuuid) {
        this.busuuid = busuuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFct() {
        return fct;
    }

    public void setFct(String fct) {
        this.fct = fct;
    }

    public String getTct() {
        return tct;
    }

    public void setTct(String tct) {
        this.tct = tct;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ResChangeItem{" +
        "id=" + id +
        ", resid=" + resid +
        ", busuuid=" + busuuid +
        ", type=" + type +
        ", mark=" + mark +
        ", fct=" + fct +
        ", tct=" + tct +
        "}";
    }
}
