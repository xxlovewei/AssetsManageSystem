package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("MN_METRIC_DEFINE")
public class MnMetricDefine extends Model<MnMetricDefine> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NAME")
    private String name;
    @TableField("DS")
    private String ds;
    @TableField("SHOWTYPE")
    private String showtype;
    @TableField("CHARTOPT")
    private String chartopt;
    @TableField("COLS")
    private String cols;
    @TableField("MARK")
    private String mark;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("STATUS")
    private String status;
    @TableField("DS_VALUE")
    private String dsValue;
    @TableField("CHARTDATATYPE")
    private String chartdatatype;
    @TableField("DATA_INTERVAL")
    private Long dataInterval;
    @TableField("V_A")
    private String vA;
    @TableField("V_A_M")
    private String vAM;
    @TableField("V_A_V")
    private String vAV;
    @TableField("IS_WARN")
    private String isWarn;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getShowtype() {
        return showtype;
    }

    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }

    public String getChartopt() {
        return chartopt;
    }

    public void setChartopt(String chartopt) {
        this.chartopt = chartopt;
    }

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDsValue() {
        return dsValue;
    }

    public void setDsValue(String dsValue) {
        this.dsValue = dsValue;
    }

    public String getChartdatatype() {
        return chartdatatype;
    }

    public void setChartdatatype(String chartdatatype) {
        this.chartdatatype = chartdatatype;
    }

    public Long getDataInterval() {
        return dataInterval;
    }

    public void setDataInterval(Long dataInterval) {
        this.dataInterval = dataInterval;
    }

    public String getvA() {
        return vA;
    }

    public void setvA(String vA) {
        this.vA = vA;
    }

    public String getvAM() {
        return vAM;
    }

    public void setvAM(String vAM) {
        this.vAM = vAM;
    }

    public String getvAV() {
        return vAV;
    }

    public void setvAV(String vAV) {
        this.vAV = vAV;
    }

    public String getIsWarn() {
        return isWarn;
    }

    public void setIsWarn(String isWarn) {
        this.isWarn = isWarn;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MnMetricDefine{" +
        ", id=" + id +
        ", name=" + name +
        ", ds=" + ds +
        ", showtype=" + showtype +
        ", chartopt=" + chartopt +
        ", cols=" + cols +
        ", mark=" + mark +
        ", isDelete=" + isDelete +
        ", status=" + status +
        ", dsValue=" + dsValue +
        ", chartdatatype=" + chartdatatype +
        ", dataInterval=" + dataInterval +
        ", vA=" + vA +
        ", vAM=" + vAM +
        ", vAV=" + vAV +
        ", isWarn=" + isWarn +
        "}";
    }
}
