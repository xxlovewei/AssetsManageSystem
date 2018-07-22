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
@TableName("OM_NODE_APP")
public class OmNodeApp extends Model<OmNodeApp> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NODE_ID")
    private String nodeId;
    @TableField("TYPE")
    private String type;
    @TableField("NAME")
    private String name;
    @TableField("USERNAME")
    private String username;
    @TableField("PWD")
    private String pwd;
    @TableField("PORT")
    private String port;
    @TableField("PWDMD5")
    private String pwdmd5;
    @TableField("MARK")
    private String mark;
    @TableField("ISVALID")
    private String isvalid;
    @TableField("STATUS")
    private String status;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("MDATE")
    private LocalDateTime mdate;
    @TableField("DELETED")
    private String deleted;
    @TableField("OD")
    private Integer od;
    @TableField("CMD_START")
    private String cmdStart;
    @TableField("CMD_STOP")
    private String cmdStop;
    @TableField("CMD_STATUS")
    private String cmdStatus;
    @TableField("IP")
    private String ip;
    @TableField("USE_HOST_LOGIN")
    private String useHostLogin;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPwdmd5() {
        return pwdmd5;
    }

    public void setPwdmd5(String pwdmd5) {
        this.pwdmd5 = pwdmd5;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public LocalDateTime getMdate() {
        return mdate;
    }

    public void setMdate(LocalDateTime mdate) {
        this.mdate = mdate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public String getCmdStart() {
        return cmdStart;
    }

    public void setCmdStart(String cmdStart) {
        this.cmdStart = cmdStart;
    }

    public String getCmdStop() {
        return cmdStop;
    }

    public void setCmdStop(String cmdStop) {
        this.cmdStop = cmdStop;
    }

    public String getCmdStatus() {
        return cmdStatus;
    }

    public void setCmdStatus(String cmdStatus) {
        this.cmdStatus = cmdStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUseHostLogin() {
        return useHostLogin;
    }

    public void setUseHostLogin(String useHostLogin) {
        this.useHostLogin = useHostLogin;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OmNodeApp{" +
        ", id=" + id +
        ", nodeId=" + nodeId +
        ", type=" + type +
        ", name=" + name +
        ", username=" + username +
        ", pwd=" + pwd +
        ", port=" + port +
        ", pwdmd5=" + pwdmd5 +
        ", mark=" + mark +
        ", isvalid=" + isvalid +
        ", status=" + status +
        ", cdate=" + cdate +
        ", mdate=" + mdate +
        ", deleted=" + deleted +
        ", od=" + od +
        ", cmdStart=" + cmdStart +
        ", cmdStop=" + cmdStop +
        ", cmdStatus=" + cmdStatus +
        ", ip=" + ip +
        ", useHostLogin=" + useHostLogin +
        "}";
    }
}
