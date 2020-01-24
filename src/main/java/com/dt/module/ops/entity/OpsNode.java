package com.dt.module.ops.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2020-01-24
 */
 
@TableName("ops_node")
 
public class OpsNode extends BaseModel<OpsNode> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("risk")
    private String risk;
    @TableField("grade")
    private String grade;
    @TableField("leader")
    private String leader;
    @TableField("bustype")
    private String bustype;
    @TableField("loc")
    private String loc;
    @TableField("ip")
    private String ip;
    @TableField("os")
    private String os;
    @TableField("osdtl")
    private String osdtl;
    @TableField("nodebackup")
    private String nodebackup;
    @TableField("middleware")
    private String middleware;
    @TableField("middlewarestr")
    private String middlewarestr;
    @TableField("db")
    private String db;
    @TableField("dbstr")
    private String dbstr;
    @TableField("envstr")
    private String envstr;
    @TableField("monitor")
    private String monitor;
    @TableField("pwdstrategy")
    private String pwdstrategy;
    @TableField("pwdmark")
    private String pwdmark;
    @TableField("mark")
    private String mark;


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

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsdtl() {
        return osdtl;
    }

    public void setOsdtl(String osdtl) {
        this.osdtl = osdtl;
    }

    public String getNodebackup() {
        return nodebackup;
    }

    public void setNodebackup(String nodebackup) {
        this.nodebackup = nodebackup;
    }

    public String getMiddleware() {
        return middleware;
    }

    public void setMiddleware(String middleware) {
        this.middleware = middleware;
    }

    public String getMiddlewarestr() {
        return middlewarestr;
    }

    public void setMiddlewarestr(String middlewarestr) {
        this.middlewarestr = middlewarestr;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDbstr() {
        return dbstr;
    }

    public void setDbstr(String dbstr) {
        this.dbstr = dbstr;
    }

    public String getEnvstr() {
        return envstr;
    }

    public void setEnvstr(String envstr) {
        this.envstr = envstr;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getPwdstrategy() {
        return pwdstrategy;
    }

    public void setPwdstrategy(String pwdstrategy) {
        this.pwdstrategy = pwdstrategy;
    }

    public String getPwdmark() {
        return pwdmark;
    }

    public void setPwdmark(String pwdmark) {
        this.pwdmark = pwdmark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OpsNode{" +
        "id=" + id +
        ", name=" + name +
        ", risk=" + risk +
        ", grade=" + grade +
        ", leader=" + leader +
        ", bustype=" + bustype +
        ", loc=" + loc +
        ", ip=" + ip +
        ", os=" + os +
        ", osdtl=" + osdtl +
        ", nodebackup=" + nodebackup +
        ", middleware=" + middleware +
        ", middlewarestr=" + middlewarestr +
        ", db=" + db +
        ", dbstr=" + dbstr +
        ", envstr=" + envstr +
        ", monitor=" + monitor +
        ", pwdstrategy=" + pwdstrategy +
        ", pwdmark=" + pwdmark +
        ", mark=" + mark +
        "}";
    }
}
