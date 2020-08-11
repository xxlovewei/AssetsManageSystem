package com.dt.module.zc.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-08-11
 */

@TableName("res_c_maintenance")

public class ResCMaintenance extends BaseModel<ResCMaintenance> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("busuuid")
    private String busuuid;
    @TableField("busidate")
    private Date busidate;
    @TableField("status")
    private String status;
    @TableField("processuserid")
    private String processuserid;
    @TableField("processusername")
    private String processusername;
    @TableField("twb")
    private String twb;
    @TableField("twbsupplier")
    private String twbsupplier;
    @TableField("twbauto")
    private String twbauto;
    @TableField("twbct")
    private String twbct;
    @TableField("twboutdate")
    private Date twboutdate;
    @TableField("mark")
    private String mark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusuuid() {
        return busuuid;
    }

    public void setBusuuid(String busuuid) {
        this.busuuid = busuuid;
    }

    public Date getBusidate() {
        return busidate;
    }

    public void setBusidate(Date busidate) {
        this.busidate = busidate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessuserid() {
        return processuserid;
    }

    public void setProcessuserid(String processuserid) {
        this.processuserid = processuserid;
    }

    public String getProcessusername() {
        return processusername;
    }

    public void setProcessusername(String processusername) {
        this.processusername = processusername;
    }

    public String getTwb() {
        return twb;
    }

    public void setTwb(String twb) {
        this.twb = twb;
    }

    public String getTwbsupplier() {
        return twbsupplier;
    }

    public void setTwbsupplier(String twbsupplier) {
        this.twbsupplier = twbsupplier;
    }

    public String getTwbauto() {
        return twbauto;
    }

    public void setTwbauto(String twbauto) {
        this.twbauto = twbauto;
    }

    public String getTwbct() {
        return twbct;
    }

    public void setTwbct(String twbct) {
        this.twbct = twbct;
    }

    public Date getTwboutdate() {
        return twboutdate;
    }

    public void setTwboutdate(Date twboutdate) {
        this.twboutdate = twboutdate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResCMaintenance{" +
                "id=" + id +
                ", busuuid=" + busuuid +
                ", busidate=" + busidate +
                ", status=" + status +
                ", processuserid=" + processuserid +
                ", processusername=" + processusername +
                ", twb=" + twb +
                ", twbsupplier=" + twbsupplier +
                ", twbauto=" + twbauto +
                ", twbct=" + twbct +
                ", twboutdate=" + twboutdate +
                ", mark=" + mark +
                "}";
    }
}
