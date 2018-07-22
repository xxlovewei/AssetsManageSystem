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
@TableName("SYS_JOB_DTL")
public class SysJobDtl extends Model<SysJobDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("SEQ")
    private String seq;
    @TableField("JOBSEQ")
    private String jobseq;
    @TableField("JOBCONTENT")
    private String jobcontent;
    @TableField("OPERTYPE")
    private String opertype;
    @TableField("STATUS")
    private String status;
    @TableField("RECLOG")
    private String reclog;
    @TableField("STIME")
    private LocalDateTime stime;
    @TableField("ETIME")
    private LocalDateTime etime;


    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getJobseq() {
        return jobseq;
    }

    public void setJobseq(String jobseq) {
        this.jobseq = jobseq;
    }

    public String getJobcontent() {
        return jobcontent;
    }

    public void setJobcontent(String jobcontent) {
        this.jobcontent = jobcontent;
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReclog() {
        return reclog;
    }

    public void setReclog(String reclog) {
        this.reclog = reclog;
    }

    public LocalDateTime getStime() {
        return stime;
    }

    public void setStime(LocalDateTime stime) {
        this.stime = stime;
    }

    public LocalDateTime getEtime() {
        return etime;
    }

    public void setEtime(LocalDateTime etime) {
        this.etime = etime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysJobDtl{" +
        ", seq=" + seq +
        ", jobseq=" + jobseq +
        ", jobcontent=" + jobcontent +
        ", opertype=" + opertype +
        ", status=" + status +
        ", reclog=" + reclog +
        ", stime=" + stime +
        ", etime=" + etime +
        "}";
    }
}
