package com.dt.module.cmdb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2019-12-01
 */
 
@TableName("res_action")
 
public class ResAction extends BaseModel<ResAction> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("uuid")
    private String uuid;
    @TableField("spstatus")
    private String spstatus;
    @TableField("actusername")
    private String actusername;
    @TableField("operuserid")
    private String operuserid;
    @TableField("ct")
    private String ct;
    @TableField("backtime")
    private Date backtime;
    @TableField("mark")
    private String mark;
    @TableField("type")
    private String type;
    @TableField("total")
    private BigDecimal total;
    @TableField("operusername")
    private String operusername;
    @TableField("backtimestr")
    private String backtimestr;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSpstatus() {
        return spstatus;
    }

    public void setSpstatus(String spstatus) {
        this.spstatus = spstatus;
    }

    public String getActusername() {
        return actusername;
    }

    public void setActusername(String actusername) {
        this.actusername = actusername;
    }

    public String getOperuserid() {
        return operuserid;
    }

    public void setOperuserid(String operuserid) {
        this.operuserid = operuserid;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getOperusername() {
        return operusername;
    }

    public void setOperusername(String operusername) {
        this.operusername = operusername;
    }

    public String getBacktimestr() {
        return backtimestr;
    }

    public void setBacktimestr(String backtimestr) {
        this.backtimestr = backtimestr;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResAction{" +
        "id=" + id +
        ", uuid=" + uuid +
        ", spstatus=" + spstatus +
        ", actusername=" + actusername +
        ", operuserid=" + operuserid +
        ", ct=" + ct +
        ", backtime=" + backtime +
        ", mark=" + mark +
        ", type=" + type +
        ", total=" + total +
        ", operusername=" + operusername +
        ", backtimestr=" + backtimestr +
        "}";
    }
}
