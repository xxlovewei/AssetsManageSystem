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
@TableName("MN_ORADB_ARCHSW")
public class MnOradbArchsw extends Model<MnOradbArchsw> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ORANAME")
    private String oraname;
    @TableField("SEQNUM")
    private Double seqnum;
    @TableField("FRE_PERS")
    private Double frePers;
    @TableField("SIZE_KB")
    private Double sizeKb;
    @TableField("FTIME")
    private LocalDateTime ftime;
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

    public Double getSeqnum() {
        return seqnum;
    }

    public void setSeqnum(Double seqnum) {
        this.seqnum = seqnum;
    }

    public Double getFrePers() {
        return frePers;
    }

    public void setFrePers(Double frePers) {
        this.frePers = frePers;
    }

    public Double getSizeKb() {
        return sizeKb;
    }

    public void setSizeKb(Double sizeKb) {
        this.sizeKb = sizeKb;
    }

    public LocalDateTime getFtime() {
        return ftime;
    }

    public void setFtime(LocalDateTime ftime) {
        this.ftime = ftime;
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
        return "MnOradbArchsw{" +
        ", node=" + node +
        ", oraname=" + oraname +
        ", seqnum=" + seqnum +
        ", frePers=" + frePers +
        ", sizeKb=" + sizeKb +
        ", ftime=" + ftime +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
