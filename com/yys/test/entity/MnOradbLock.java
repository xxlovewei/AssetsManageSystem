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
@TableName("MN_ORADB_LOCK")
public class MnOradbLock extends Model<MnOradbLock> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ORANAME")
    private String oraname;
    @TableField("STATUS")
    private String status;
    @TableField("WAITING_SESSION")
    private String waitingSession;
    @TableField("USERNAME")
    private String username;
    @TableField("OBJECT_NAME")
    private String objectName;
    @TableField("OBJECT_ID")
    private String objectId;
    @TableField("OWNER")
    private String owner;
    @TableField("OBJECT_TYPE")
    private String objectType;
    @TableField("COMMAND")
    private String command;
    @TableField("SID")
    private String sid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaitingSession() {
        return waitingSession;
    }

    public void setWaitingSession(String waitingSession) {
        this.waitingSession = waitingSession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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
        return "MnOradbLock{" +
        ", node=" + node +
        ", oraname=" + oraname +
        ", status=" + status +
        ", waitingSession=" + waitingSession +
        ", username=" + username +
        ", objectName=" + objectName +
        ", objectId=" + objectId +
        ", owner=" + owner +
        ", objectType=" + objectType +
        ", command=" + command +
        ", sid=" + sid +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
