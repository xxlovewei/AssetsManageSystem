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
@TableName("SYS_QUD_CHENGS_ARCH")
public class SysQudChengsArch extends Model<SysQudChengsArch> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;
    @TableField("MINGC")
    private String mingc;
    @TableField("JIB")
    private String jib;
    @TableField("SHENGF_ID")
    private String shengfId;
    @TableField("YOUB")
    private String youb;
    @TableField("QUH")
    private String quh;
    @TableField("CHENGSID_TQ")
    private String chengsidTq;
    @TableField("JIANC")
    private String jianc;
    @TableField("GUOBM")
    private String guobm;
    @TableField("RENKSL")
    private Double renksl;
    @TableField("RENJSR")
    private Double renjsr;
    @TableField("RENJXSZC")
    private Double renjxszc;
    @TableField("RENJFZZC")
    private Double renjfzzc;
    @TableField("XIUGR")
    private String xiugr;
    @TableField("XIUGRQ")
    private LocalDateTime xiugrq;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMingc() {
        return mingc;
    }

    public void setMingc(String mingc) {
        this.mingc = mingc;
    }

    public String getJib() {
        return jib;
    }

    public void setJib(String jib) {
        this.jib = jib;
    }

    public String getShengfId() {
        return shengfId;
    }

    public void setShengfId(String shengfId) {
        this.shengfId = shengfId;
    }

    public String getYoub() {
        return youb;
    }

    public void setYoub(String youb) {
        this.youb = youb;
    }

    public String getQuh() {
        return quh;
    }

    public void setQuh(String quh) {
        this.quh = quh;
    }

    public String getChengsidTq() {
        return chengsidTq;
    }

    public void setChengsidTq(String chengsidTq) {
        this.chengsidTq = chengsidTq;
    }

    public String getJianc() {
        return jianc;
    }

    public void setJianc(String jianc) {
        this.jianc = jianc;
    }

    public String getGuobm() {
        return guobm;
    }

    public void setGuobm(String guobm) {
        this.guobm = guobm;
    }

    public Double getRenksl() {
        return renksl;
    }

    public void setRenksl(Double renksl) {
        this.renksl = renksl;
    }

    public Double getRenjsr() {
        return renjsr;
    }

    public void setRenjsr(Double renjsr) {
        this.renjsr = renjsr;
    }

    public Double getRenjxszc() {
        return renjxszc;
    }

    public void setRenjxszc(Double renjxszc) {
        this.renjxszc = renjxszc;
    }

    public Double getRenjfzzc() {
        return renjfzzc;
    }

    public void setRenjfzzc(Double renjfzzc) {
        this.renjfzzc = renjfzzc;
    }

    public String getXiugr() {
        return xiugr;
    }

    public void setXiugr(String xiugr) {
        this.xiugr = xiugr;
    }

    public LocalDateTime getXiugrq() {
        return xiugrq;
    }

    public void setXiugrq(LocalDateTime xiugrq) {
        this.xiugrq = xiugrq;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysQudChengsArch{" +
        ", id=" + id +
        ", mingc=" + mingc +
        ", jib=" + jib +
        ", shengfId=" + shengfId +
        ", youb=" + youb +
        ", quh=" + quh +
        ", chengsidTq=" + chengsidTq +
        ", jianc=" + jianc +
        ", guobm=" + guobm +
        ", renksl=" + renksl +
        ", renjsr=" + renjsr +
        ", renjxszc=" + renjxszc +
        ", renjfzzc=" + renjfzzc +
        ", xiugr=" + xiugr +
        ", xiugrq=" + xiugrq +
        "}";
    }
}
