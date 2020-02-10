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
 * @since 2020-02-10
 */
 
@TableName("ops_node")
 
public class OpsNode extends BaseModel<OpsNode> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("runenv")
    private String runenv;
    @TableField("syslevel")
    private String syslevel;
    @TableField("leader")
    private String leader;
    @TableField("busitype")
    private String busitype;
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
    @TableField("dbdtl")
    private String dbdtl;
    @TableField("execenv")
    private String execenv;
    @TableField("monitor")
    private String monitor;
    @TableField("pwdstrategy")
    private String pwdstrategy;
    @TableField("pwdmark")
    private String pwdmark;
    @TableField("mark")
    private String mark;
    @TableField("importlabel")
    private String importlabel;
    @TableField("label1")
    private String label1;
    @TableField("label2")
    private String label2;
    @TableField("label3")
    private String label3;
    @TableField("label4")
    private String label4;
    @TableField("label5")
    private String label5;
    @TableField("label6")
    private String label6;
    
    @TableField("status")
    private String status;


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

    public String getRunenv() {
        return runenv;
    }

    public void setRunenv(String runenv) {
        this.runenv = runenv;
    }

    public String getSyslevel() {
        return syslevel;
    }

    public void setSyslevel(String syslevel) {
        this.syslevel = syslevel;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getBusitype() {
        return busitype;
    }

    public void setBusitype(String busitype) {
        this.busitype = busitype;
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

    public String getDbdtl() {
        return dbdtl;
    }

    public void setDbdtl(String dbdtl) {
        this.dbdtl = dbdtl;
    }

    public String getExecenv() {
        return execenv;
    }

    public void setExecenv(String execenv) {
        this.execenv = execenv;
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

    public String getImportlabel() {
        return importlabel;
    }

    public void setImportlabel(String importlabel) {
        this.importlabel = importlabel;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public String getLabel4() {
        return label4;
    }

    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    public String getLabel5() {
        return label5;
    }

    public void setLabel5(String label5) {
        this.label5 = label5;
    }

    public String getLabel6() {
        return label6;
    }

    public void setLabel6(String label6) {
        this.label6 = label6;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        ", runenv=" + runenv +
        ", syslevel=" + syslevel +
        ", leader=" + leader +
        ", busitype=" + busitype +
        ", loc=" + loc +
        ", ip=" + ip +
        ", os=" + os +
        ", osdtl=" + osdtl +
        ", nodebackup=" + nodebackup +
        ", middleware=" + middleware +
        ", middlewarestr=" + middlewarestr +
        ", db=" + db +
        ", dbdtl=" + dbdtl +
        ", execenv=" + execenv +
        ", monitor=" + monitor +
        ", pwdstrategy=" + pwdstrategy +
        ", pwdmark=" + pwdmark +
        ", mark=" + mark +
        ", importlabel=" + importlabel +
        ", label1=" + label1 +
        ", label2=" + label2 +
        ", label3=" + label3 +
        ", label4=" + label4 +
        ", label5=" + label5 +
        ", label6=" + label6 +
        ", status=" + status +
        "}";
    }
}
