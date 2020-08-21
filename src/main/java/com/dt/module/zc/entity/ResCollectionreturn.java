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
 * @since 2020-08-20
 */

@TableName("res_collectionreturn")

public class ResCollectionreturn extends BaseModel<ResCollectionreturn> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("busuuid")
    private String busuuid;
    @TableField("status")
    private String status;
    @TableField("pinst")
    private String pinst;
    @TableField("cruserid")
    private String cruserid;
    @TableField("crusername")
    private String crusername;
    @TableField("processuserid")
    private String processuserid;
    @TableField("processusername")
    private String processusername;
    @TableField("tusedcompanyid")
    private String tusedcompanyid;
    @TableField("tpartid")
    private String tpartid;
    @TableField("tuseduserid")
    private String tuseduserid;
    @TableField("tloc")
    private String tloc;
    @TableField("tlocdtl")
    private String tlocdtl;
    @TableField("busdate")
    private Date busdate;
    @TableField("returndate")
    private Date returndate;
    @TableField("mark")
    private String mark;
    @TableField("bustype")
    private String bustype;
    @TableField("rreturndate")
    private Date rreturndate;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPinst() {
        return pinst;
    }

    public void setPinst(String pinst) {
        this.pinst = pinst;
    }

    public String getCruserid() {
        return cruserid;
    }

    public void setCruserid(String cruserid) {
        this.cruserid = cruserid;
    }

    public String getCrusername() {
        return crusername;
    }

    public void setCrusername(String crusername) {
        this.crusername = crusername;
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

    public String getTusedcompanyid() {
        return tusedcompanyid;
    }

    public void setTusedcompanyid(String tusedcompanyid) {
        this.tusedcompanyid = tusedcompanyid;
    }

    public String getTpartid() {
        return tpartid;
    }

    public void setTpartid(String tpartid) {
        this.tpartid = tpartid;
    }

    public String getTuseduserid() {
        return tuseduserid;
    }

    public void setTuseduserid(String tuseduserid) {
        this.tuseduserid = tuseduserid;
    }

    public String getTloc() {
        return tloc;
    }

    public void setTloc(String tloc) {
        this.tloc = tloc;
    }

    public String getTlocdtl() {
        return tlocdtl;
    }

    public void setTlocdtl(String tlocdtl) {
        this.tlocdtl = tlocdtl;
    }

    public Date getBusdate() {
        return busdate;
    }

    public void setBusdate(Date busdate) {
        this.busdate = busdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public Date getRreturndate() {
        return rreturndate;
    }

    public void setRreturndate(Date rreturndate) {
        this.rreturndate = rreturndate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResCollectionreturn{" +
                "id=" + id +
                ", busuuid=" + busuuid +
                ", status=" + status +
                ", pinst=" + pinst +
                ", cruserid=" + cruserid +
                ", crusername=" + crusername +
                ", processuserid=" + processuserid +
                ", processusername=" + processusername +
                ", tusedcompanyid=" + tusedcompanyid +
                ", tpartid=" + tpartid +
                ", tuseduserid=" + tuseduserid +
                ", tloc=" + tloc +
                ", tlocdtl=" + tlocdtl +
                ", busdate=" + busdate +
                ", returndate=" + returndate +
                ", mark=" + mark +
                ", bustype=" + bustype +
                ", rreturndate=" + rreturndate +
                "}";
    }
}
