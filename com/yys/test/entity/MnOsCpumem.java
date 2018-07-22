package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杨永生
 * @since 2018-07-22
 */
@TableName("MN_OS_CPUMEM")
public class MnOsCpumem extends Model<MnOsCpumem> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("RUNPROCESS")
    private Double runprocess;
    @TableField("OS_USER")
    private Double osUser;
    @TableField("OS_SYS")
    private Double osSys;
    @TableField("OS_IDLE")
    private Double osIdle;
    @TableField("OS_WIO")
    private Double osWio;
    @TableField("OS_MEM_FREE")
    private Double osMemFree;
    @TableField("OS_PAGE_IN")
    private Double osPageIn;
    @TableField("OS_PAGE_OUT")
    private Double osPageOut;
    @TableField("OS_PAGE_FR")
    private Double osPageFr;
    @TableField("OS_PAGE_SR")
    private Double osPageSr;
    @TableField("PRODUCETIME")
    private String producetime;
    @TableField("INSERTTIME")
    private LocalDateTime inserttime;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Double getRunprocess() {
        return runprocess;
    }

    public void setRunprocess(Double runprocess) {
        this.runprocess = runprocess;
    }

    public Double getOsUser() {
        return osUser;
    }

    public void setOsUser(Double osUser) {
        this.osUser = osUser;
    }

    public Double getOsSys() {
        return osSys;
    }

    public void setOsSys(Double osSys) {
        this.osSys = osSys;
    }

    public Double getOsIdle() {
        return osIdle;
    }

    public void setOsIdle(Double osIdle) {
        this.osIdle = osIdle;
    }

    public Double getOsWio() {
        return osWio;
    }

    public void setOsWio(Double osWio) {
        this.osWio = osWio;
    }

    public Double getOsMemFree() {
        return osMemFree;
    }

    public void setOsMemFree(Double osMemFree) {
        this.osMemFree = osMemFree;
    }

    public Double getOsPageIn() {
        return osPageIn;
    }

    public void setOsPageIn(Double osPageIn) {
        this.osPageIn = osPageIn;
    }

    public Double getOsPageOut() {
        return osPageOut;
    }

    public void setOsPageOut(Double osPageOut) {
        this.osPageOut = osPageOut;
    }

    public Double getOsPageFr() {
        return osPageFr;
    }

    public void setOsPageFr(Double osPageFr) {
        this.osPageFr = osPageFr;
    }

    public Double getOsPageSr() {
        return osPageSr;
    }

    public void setOsPageSr(Double osPageSr) {
        this.osPageSr = osPageSr;
    }

    public String getProducetime() {
        return producetime;
    }

    public void setProducetime(String producetime) {
        this.producetime = producetime;
    }

    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnOsCpumem{" +
        ", node=" + node +
        ", runprocess=" + runprocess +
        ", osUser=" + osUser +
        ", osSys=" + osSys +
        ", osIdle=" + osIdle +
        ", osWio=" + osWio +
        ", osMemFree=" + osMemFree +
        ", osPageIn=" + osPageIn +
        ", osPageOut=" + osPageOut +
        ", osPageFr=" + osPageFr +
        ", osPageSr=" + osPageSr +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
