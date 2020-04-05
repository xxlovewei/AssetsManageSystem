package com.dt.module.flow.entity;

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
 * @since 2020-04-04
 */

@TableName("sys_process_data")

public class SysProcessData extends BaseModel<SysProcessData> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("busid")
    private String busid;
    @TableField("tabtype")
    private String tabtype;
    @TableField("processkey")
    private String processkey;
    @TableField("processname")
    private String processname;
    @TableField("processversion")
    private String processversion;
    @TableField("processInstanceId")
    private String processInstanceId;
    /**
     * 流程标题
     */
    @TableField("ptitle")
    private String ptitle;
    @TableField("ptype")
    private String ptype;
    @TableField("psubtype")
    private String psubtype;
    @TableField("pstatus")
    private String pstatus;
    @TableField("pstatusdtl")
    private String pstatusdtl;
    /**
     * 流程发起人
     */
    @TableField("pstartuserid")
    private String pstartuserid;
    /**
     * 流程发起人姓名
     */
    @TableField("pstartusername")
    private String pstartusername;
    @TableField("pendtime")
    private Date pendtime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid = busid;
    }

    public String getTabtype() {
        return tabtype;
    }

    public void setTabtype(String tabtype) {
        this.tabtype = tabtype;
    }

    public String getProcesskey() {
        return processkey;
    }

    public void setProcesskey(String processkey) {
        this.processkey = processkey;
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    public String getProcessversion() {
        return processversion;
    }

    public void setProcessversion(String processversion) {
        this.processversion = processversion;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPsubtype() {
        return psubtype;
    }

    public void setPsubtype(String psubtype) {
        this.psubtype = psubtype;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getPstatusdtl() {
        return pstatusdtl;
    }

    public void setPstatusdtl(String pstatusdtl) {
        this.pstatusdtl = pstatusdtl;
    }

    public String getPstartuserid() {
        return pstartuserid;
    }

    public void setPstartuserid(String pstartuserid) {
        this.pstartuserid = pstartuserid;
    }

    public String getPstartusername() {
        return pstartusername;
    }

    public void setPstartusername(String pstartusername) {
        this.pstartusername = pstartusername;
    }

    public Date getPendtime() {
        return pendtime;
    }

    public void setPendtime(Date pendtime) {
        this.pendtime = pendtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysProcessData{" +
                "id=" + id +
                ", busid=" + busid +
                ", tabtype=" + tabtype +
                ", processkey=" + processkey +
                ", processname=" + processname +
                ", processversion=" + processversion +
                ", processInstanceId=" + processInstanceId +
                ", ptitle=" + ptitle +
                ", ptype=" + ptype +
                ", psubtype=" + psubtype +
                ", pstatus=" + pstatus +
                ", pstatusdtl=" + pstatusdtl +
                ", pstartuserid=" + pstartuserid +
                ", pstartusername=" + pstartusername +
                ", pendtime=" + pendtime +
                "}";
    }
}
