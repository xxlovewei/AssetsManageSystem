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
@TableName("MN_ORADB_SIZE")
public class MnOradbSize extends Model<MnOradbSize> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ORANAME")
    private String oraname;
    @TableField("ALLOCATE")
    private Double allocate;
    @TableField("FREE")
    private Double free;
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

    public String getOraname() {
        return oraname;
    }

    public void setOraname(String oraname) {
        this.oraname = oraname;
    }

    public Double getAllocate() {
        return allocate;
    }

    public void setAllocate(Double allocate) {
        this.allocate = allocate;
    }

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
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
        return "MnOradbSize{" +
        ", node=" + node +
        ", oraname=" + oraname +
        ", allocate=" + allocate +
        ", free=" + free +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
