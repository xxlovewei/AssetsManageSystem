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
@TableName("OM_BACKUP_APP")
public class OmBackupApp extends Model<OmBackupApp> {

    private static final long serialVersionUID = 1L;

    @TableId("APP_ID")
    private String appId;
    @TableField("TYPE")
    private String type;
    @TableField("NAME")
    private String name;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("IP")
    private String ip;
    @TableField("POLICY")
    private String policy;
    @TableField("IF_WARN")
    private String ifWarn;
    @TableField("VALID")
    private String valid;
    @TableField("WARN_DAY")
    private Long warnDay;
    @TableField("MARK")
    private String mark;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getIfWarn() {
        return ifWarn;
    }

    public void setIfWarn(String ifWarn) {
        this.ifWarn = ifWarn;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Long getWarnDay() {
        return warnDay;
    }

    public void setWarnDay(Long warnDay) {
        this.warnDay = warnDay;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.appId;
    }

    @Override
    public String toString() {
        return "OmBackupApp{" +
        ", appId=" + appId +
        ", type=" + type +
        ", name=" + name +
        ", isDelete=" + isDelete +
        ", ip=" + ip +
        ", policy=" + policy +
        ", ifWarn=" + ifWarn +
        ", valid=" + valid +
        ", warnDay=" + warnDay +
        ", mark=" + mark +
        "}";
    }
}
