package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("MN_URL_TOUCH_WARN")
public class MnUrlTouchWarn extends Model<MnUrlTouchWarn> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NODE")
    private String node;
    @TableField("NODENAME")
    private String nodename;
    @TableField("URL")
    private String url;
    @TableField("DATA_ID")
    private String dataId;
    @TableField("STATUS")
    private String status;
    @TableField("RESP_TIME")
    private Integer respTime;
    @TableField("THRESHOLD")
    private Integer threshold;
    @TableField("INSERTTIME")
    private LocalDateTime inserttime;
    @TableField("DR")
    private Double dr;
    @TableField("PROCESS")
    private Double process;
    @TableField("WARN")
    private Double warn;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRespTime() {
        return respTime;
    }

    public void setRespTime(Integer respTime) {
        this.respTime = respTime;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
    }

    public Double getProcess() {
        return process;
    }

    public void setProcess(Double process) {
        this.process = process;
    }

    public Double getWarn() {
        return warn;
    }

    public void setWarn(Double warn) {
        this.warn = warn;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MnUrlTouchWarn{" +
        ", id=" + id +
        ", node=" + node +
        ", nodename=" + nodename +
        ", url=" + url +
        ", dataId=" + dataId +
        ", status=" + status +
        ", respTime=" + respTime +
        ", threshold=" + threshold +
        ", inserttime=" + inserttime +
        ", dr=" + dr +
        ", process=" + process +
        ", warn=" + warn +
        "}";
    }
}
