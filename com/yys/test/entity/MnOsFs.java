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
@TableName("MN_OS_FS")
public class MnOsFs extends Model<MnOsFs> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("LVNAME")
    private String lvname;
    @TableField("FSIZE")
    private Double fsize;
    @TableField("USED")
    private Double used;
    @TableField("AVAIL")
    private Double avail;
    @TableField("USDRAT")
    private Double usdrat;
    @TableField("MOUNTPOINT")
    private String mountpoint;
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

    public String getLvname() {
        return lvname;
    }

    public void setLvname(String lvname) {
        this.lvname = lvname;
    }

    public Double getFsize() {
        return fsize;
    }

    public void setFsize(Double fsize) {
        this.fsize = fsize;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public Double getAvail() {
        return avail;
    }

    public void setAvail(Double avail) {
        this.avail = avail;
    }

    public Double getUsdrat() {
        return usdrat;
    }

    public void setUsdrat(Double usdrat) {
        this.usdrat = usdrat;
    }

    public String getMountpoint() {
        return mountpoint;
    }

    public void setMountpoint(String mountpoint) {
        this.mountpoint = mountpoint;
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
        return "MnOsFs{" +
        ", node=" + node +
        ", lvname=" + lvname +
        ", fsize=" + fsize +
        ", used=" + used +
        ", avail=" + avail +
        ", usdrat=" + usdrat +
        ", mountpoint=" + mountpoint +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
