package com.dt.module.zc.entity;

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
 * @since 2020-08-11
 */

@TableName("res_c_finance")

public class ResCFinance extends BaseModel<ResCFinance> {

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
    @TableField("tbelongcomp")
    private String tbelongcomp;
    @TableField("tbelongpart")
    private String tbelongpart;
    @TableField("tbuyprice")
    private BigDecimal tbuyprice;
    @TableField("tnetworth")
    private BigDecimal tnetworth;
    @TableField("tresidualvalue")
    private BigDecimal tresidualvalue;
    @TableField("taccumulateddepreciation")
    private BigDecimal taccumulateddepreciation;
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

    public String getTbelongcomp() {
        return tbelongcomp;
    }

    public void setTbelongcomp(String tbelongcomp) {
        this.tbelongcomp = tbelongcomp;
    }

    public String getTbelongpart() {
        return tbelongpart;
    }

    public void setTbelongpart(String tbelongpart) {
        this.tbelongpart = tbelongpart;
    }

    public BigDecimal getTbuyprice() {
        return tbuyprice;
    }

    public void setTbuyprice(BigDecimal tbuyprice) {
        this.tbuyprice = tbuyprice;
    }

    public BigDecimal getTnetworth() {
        return tnetworth;
    }

    public void setTnetworth(BigDecimal tnetworth) {
        this.tnetworth = tnetworth;
    }

    public BigDecimal getTresidualvalue() {
        return tresidualvalue;
    }

    public void setTresidualvalue(BigDecimal tresidualvalue) {
        this.tresidualvalue = tresidualvalue;
    }

    public BigDecimal getTaccumulateddepreciation() {
        return taccumulateddepreciation;
    }

    public void setTaccumulateddepreciation(BigDecimal taccumulateddepreciation) {
        this.taccumulateddepreciation = taccumulateddepreciation;
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
        return "ResCFinance{" +
                "id=" + id +
                ", busuuid=" + busuuid +
                ", busidate=" + busidate +
                ", status=" + status +
                ", processuserid=" + processuserid +
                ", processusername=" + processusername +
                ", tbelongcomp=" + tbelongcomp +
                ", tbelongpart=" + tbelongpart +
                ", tbuyprice=" + tbuyprice +
                ", tnetworth=" + tnetworth +
                ", tresidualvalue=" + tresidualvalue +
                ", taccumulateddepreciation=" + taccumulateddepreciation +
                ", mark=" + mark +
                "}";
    }
}
