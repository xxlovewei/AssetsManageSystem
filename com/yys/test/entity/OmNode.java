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
@TableName("OM_NODE")
public class OmNode extends Model<OmNode> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("TYPE")
    private String type;
    @TableField("SMALLTYPE")
    private String smalltype;
    @TableField("NAME")
    private String name;
    @TableField("OPERATOR")
    private String operator;
    @TableField("IP")
    private String ip;
    @TableField("USERNAME")
    private String username;
    @TableField("PWD")
    private String pwd;
    @TableField("LOGINTYPE")
    private String logintype;
    @TableField("PORT")
    private String port;
    @TableField("ISVALID")
    private String isvalid;
    @TableField("ISRUNNING")
    private String isrunning;
    @TableField("DELETED")
    private String deleted;
    @TableField("MARK")
    private String mark;
    @TableField("OD")
    private Integer od;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("MDATE")
    private LocalDateTime mdate;
    @TableField("PWDMD5")
    private String pwdmd5;
    @TableField("CMD_START")
    private String cmdStart;
    @TableField("CMD_STOP")
    private String cmdStop;
    @TableField("CMD_STATUS")
    private String cmdStatus;
    @TableField("TEMPLID")
    private String templid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSmalltype() {
        return smalltype;
    }

    public void setSmalltype(String smalltype) {
        this.smalltype = smalltype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getIsrunning() {
        return isrunning;
    }

    public void setIsrunning(String isrunning) {
        this.isrunning = isrunning;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
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

    public String getPwdmd5() {
        return pwdmd5;
    }

    public void setPwdmd5(String pwdmd5) {
        this.pwdmd5 = pwdmd5;
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

    public String getTemplid() {
        return templid;
    }

    public void setTemplid(String templid) {
        this.templid = templid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OmNode{" +
        ", id=" + id +
        ", type=" + type +
        ", smalltype=" + smalltype +
        ", name=" + name +
        ", operator=" + operator +
        ", ip=" + ip +
        ", username=" + username +
        ", pwd=" + pwd +
        ", logintype=" + logintype +
        ", port=" + port +
        ", isvalid=" + isvalid +
        ", isrunning=" + isrunning +
        ", deleted=" + deleted +
        ", mark=" + mark +
        ", od=" + od +
        ", cdate=" + cdate +
        ", mdate=" + mdate +
        ", pwdmd5=" + pwdmd5 +
        ", cmdStart=" + cmdStart +
        ", cmdStop=" + cmdStop +
        ", cmdStatus=" + cmdStatus +
        ", templid=" + templid +
        "}";
    }
}
