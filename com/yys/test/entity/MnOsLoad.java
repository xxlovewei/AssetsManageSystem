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
@TableName("MN_OS_LOAD")
public class MnOsLoad extends Model<MnOsLoad> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("USERS")
    private Double users;
    @TableField("PROC")
    private Double proc;
    @TableField("ORA_PROC")
    private Double oraProc;
    @TableField("ORA_SYS_PROC")
    private Double oraSysProc;
    @TableField("OS_LOAD")
    private Double osLoad;
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

    public Double getUsers() {
        return users;
    }

    public void setUsers(Double users) {
        this.users = users;
    }

    public Double getProc() {
        return proc;
    }

    public void setProc(Double proc) {
        this.proc = proc;
    }

    public Double getOraProc() {
        return oraProc;
    }

    public void setOraProc(Double oraProc) {
        this.oraProc = oraProc;
    }

    public Double getOraSysProc() {
        return oraSysProc;
    }

    public void setOraSysProc(Double oraSysProc) {
        this.oraSysProc = oraSysProc;
    }

    public Double getOsLoad() {
        return osLoad;
    }

    public void setOsLoad(Double osLoad) {
        this.osLoad = osLoad;
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
        return "MnOsLoad{" +
        ", node=" + node +
        ", users=" + users +
        ", proc=" + proc +
        ", oraProc=" + oraProc +
        ", oraSysProc=" + oraSysProc +
        ", osLoad=" + osLoad +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
