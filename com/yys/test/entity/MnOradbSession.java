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
@TableName("MN_ORADB_SESSION")
public class MnOradbSession extends Model<MnOradbSession> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ORANAME")
    private String oraname;
    @TableField("SESSCNT")
    private Double sesscnt;
    @TableField("ACTIVESESSCNT")
    private Double activesesscnt;
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

    public Double getSesscnt() {
        return sesscnt;
    }

    public void setSesscnt(Double sesscnt) {
        this.sesscnt = sesscnt;
    }

    public Double getActivesesscnt() {
        return activesesscnt;
    }

    public void setActivesesscnt(Double activesesscnt) {
        this.activesesscnt = activesesscnt;
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
        return "MnOradbSession{" +
        ", node=" + node +
        ", oraname=" + oraname +
        ", sesscnt=" + sesscnt +
        ", activesesscnt=" + activesesscnt +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
