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
 * @since 2020-08-21
 */

@TableName("res_loanreturn")

public class ResLoanreturn extends BaseModel<ResLoanreturn> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 单据
     */
    @TableField("busuuid")
    private String busuuid;
    /**
     * 类型
     */
    @TableField("bustype")
    private String bustype;
    /**
     * 借用时间
     */
    @TableField("busdate")
    private Date busdate;
    /**
     * 办理状态
     */
    @TableField("status")
    private String status;
    /**
     * 流程审批
     */
    @TableField("pinst")
    private String pinst;
    /**
     * 借用人ID/归还人ID
     */
    @TableField("lruserid")
    private String lruserid;
    /**
     * 借用人/归还人
     */
    @TableField("lrusername")
    private String lrusername;
    /**
     * 处理人ID
     */
    @TableField("processuserid")
    private String processuserid;
    /**
     * 处理人
     */
    @TableField("processusername")
    private String processusername;
    /**
     * 使用公司
     */
    @TableField("tusedcompanyid")
    private String tusedcompanyid;
    /**
     * 部门
     */
    @TableField("tpartid")
    private String tpartid;
    /**
     * 区域
     */
    @TableField("tloc")
    private String tloc;
    /**
     * 位置
     */
    @TableField("tlocdtl")
    private String tlocdtl;
    /**
     * 预计归还时间
     */
    @TableField("returndate")
    private Date returndate;
    /**
     * 实际归还时间
     */
    @TableField("rreturndate")
    private Date rreturndate;
    @TableField("isreturn")
    private String isreturn;
    @TableField("returnuuid")
    private String returnuuid;
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

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public Date getBusdate() {
        return busdate;
    }

    public void setBusdate(Date busdate) {
        this.busdate = busdate;
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

    public String getLruserid() {
        return lruserid;
    }

    public void setLruserid(String lruserid) {
        this.lruserid = lruserid;
    }

    public String getLrusername() {
        return lrusername;
    }

    public void setLrusername(String lrusername) {
        this.lrusername = lrusername;
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

    public String getReturnuuid() {
        return returnuuid;
    }

    public void setReturnuuid(String returnuuid) {
        this.returnuuid = returnuuid;
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
        return "ResLoanreturn{" +
                "id=" + id +
                ", busuuid=" + busuuid +
                ", bustype=" + bustype +
                ", busdate=" + busdate +
                ", status=" + status +
                ", pinst=" + pinst +
                ", lruserid=" + lruserid +
                ", lrusername=" + lrusername +
                ", processuserid=" + processuserid +
                ", processusername=" + processusername +
                ", tusedcompanyid=" + tusedcompanyid +
                ", tpartid=" + tpartid +
                ", tloc=" + tloc +
                ", tlocdtl=" + tlocdtl +
                ", returndate=" + returndate +
                ", rreturndate=" + rreturndate +
                ", isreturn=" + isreturn +
                ", returnuuid=" + returnuuid +
                ", mark=" + mark +
                "}";
    }
}
