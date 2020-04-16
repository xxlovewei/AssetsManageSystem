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
 * @since 2020-04-16
 */
 
@TableName("sys_process_data")
 
public class SysProcessData extends BaseModel<SysProcessData> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 业务id
     */
    @TableField("busid")
    private String busid;
    /**
     * 业务类型 flow,form
     */
    @TableField("bustype")
    private String bustype;
    /**
     * 流程Key
     */
    @TableField("processkey")
    private String processkey;
    /**
     * 流程名称
     */
    @TableField("processname")
    private String processname;
    /**
     * 流程版本
     */
    @TableField("processversion")
    private String processversion;
    /**
     * 流程实例化后的ID
     */
    @TableField("processInstanceId")
    private String processInstanceId;
    /**
     * 流程标题
     */
    @TableField("ptitle")
    private String ptitle;
    /**
     * 流程类型(LY,JY...)
     */
    @TableField("ptype")
    private String ptype;
    /**
     * 流程子类型
     */
    @TableField("psubtype")
    private String psubtype;
    /**
     * 
waiting 
inreview  
finish ?
     */
    @TableField("pstatus")
    private String pstatus;
    /**
     * success 
failed 
     */
    @TableField("pstatusdtl")
    private String pstatusdtl;
    /**
     * 流程发起人用户ID
     */
    @TableField("pstartuserid")
    private String pstartuserid;
    /**
     * 流程发起人姓名
     */
    @TableField("pstartusername")
    private String pstartusername;
    /**
     * 流程结束时间
     */
    @TableField("pendtime")
    private Date pendtime;
    /**
     * 流程调用的表单ID
     */
    @TableField("formid")
    private String formid;
    @TableField("ifsp")
    private String ifsp;


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

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
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

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getIfsp() {
        return ifsp;
    }

    public void setIfsp(String ifsp) {
        this.ifsp = ifsp;
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
        ", bustype=" + bustype +
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
        ", formid=" + formid +
        ", ifsp=" + ifsp +
        "}";
    }
}
