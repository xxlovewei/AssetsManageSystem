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

@TableName("res_collectionreturn_item")

public class ResCollectionreturnItem extends BaseModel<ResCollectionreturnItem> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("busuuid")
    private String busuuid;
    @TableField("resid")
    private String resid;
    @TableField("cruserid")
    private String cruserid;
    @TableField("crusername")
    private String crusername;
    @TableField("processuserid")
    private String processuserid;
    @TableField("processusername")
    private String processusername;
    @TableField("fusedcompanyid")
    private String fusedcompanyid;
    @TableField("fpartid")
    private String fpartid;
    @TableField("fuseduserid")
    private String fuseduserid;
    @TableField("floc")
    private String floc;
    @TableField("flocdtl")
    private String flocdtl;
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
    @TableField("returndate")
    private Date returndate;
    @TableField("rreturndate")
    private Date rreturndate;
    @TableField("isreturn")
    private String isreturn;
    @TableField("busdate")
    private Date busdate;
    @TableField("mark")
    private String mark;
    @TableField("returnuuid")
    private String returnuuid;


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

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
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

    public String getFusedcompanyid() {
        return fusedcompanyid;
    }

    public void setFusedcompanyid(String fusedcompanyid) {
        this.fusedcompanyid = fusedcompanyid;
    }

    public String getFpartid() {
        return fpartid;
    }

    public void setFpartid(String fpartid) {
        this.fpartid = fpartid;
    }

    public String getFuseduserid() {
        return fuseduserid;
    }

    public void setFuseduserid(String fuseduserid) {
        this.fuseduserid = fuseduserid;
    }

    public String getFloc() {
        return floc;
    }

    public void setFloc(String floc) {
        this.floc = floc;
    }

    public String getFlocdtl() {
        return flocdtl;
    }

    public void setFlocdtl(String flocdtl) {
        this.flocdtl = flocdtl;
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

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Date getRreturndate() {
        return rreturndate;
    }

    public void setRreturndate(Date rreturndate) {
        this.rreturndate = rreturndate;
    }

    public String getIsreturn() {
        return isreturn;
    }

    public void setIsreturn(String isreturn) {
        this.isreturn = isreturn;
    }

    public Date getBusdate() {
        return busdate;
    }

    public void setBusdate(Date busdate) {
        this.busdate = busdate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getReturnuuid() {
        return returnuuid;
    }

    public void setReturnuuid(String returnuuid) {
        this.returnuuid = returnuuid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResCollectionreturnItem{" +
                "id=" + id +
                ", busuuid=" + busuuid +
                ", resid=" + resid +
                ", cruserid=" + cruserid +
                ", crusername=" + crusername +
                ", processuserid=" + processuserid +
                ", processusername=" + processusername +
                ", fusedcompanyid=" + fusedcompanyid +
                ", fpartid=" + fpartid +
                ", fuseduserid=" + fuseduserid +
                ", floc=" + floc +
                ", flocdtl=" + flocdtl +
                ", tusedcompanyid=" + tusedcompanyid +
                ", tpartid=" + tpartid +
                ", tuseduserid=" + tuseduserid +
                ", tloc=" + tloc +
                ", tlocdtl=" + tlocdtl +
                ", returndate=" + returndate +
                ", rreturndate=" + rreturndate +
                ", isreturn=" + isreturn +
                ", busdate=" + busdate +
                ", mark=" + mark +
                ", returnuuid=" + returnuuid +
                "}";
    }
}
