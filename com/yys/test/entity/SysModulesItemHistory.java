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
@TableName("SYS_MODULES_ITEM_HISTORY")
public class SysModulesItemHistory extends Model<SysModulesItemHistory> {

    private static final long serialVersionUID = 1L;

    @TableField("MODULE_ITEM_ID")
    private String moduleItemId;
    @TableField("MODULE_ID")
    private String moduleId;
    @TableField("CT")
    private String ct;
    @TableField("STATUS")
    private String status;
    @TableField("TYPE")
    private String type;
    @TableField("RECDATE")
    private LocalDateTime recdate;
    @TableField("VERSION")
    private String version;


    public String getModuleItemId() {
        return moduleItemId;
    }

    public void setModuleItemId(String moduleItemId) {
        this.moduleItemId = moduleItemId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getRecdate() {
        return recdate;
    }

    public void setRecdate(LocalDateTime recdate) {
        this.recdate = recdate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysModulesItemHistory{" +
        ", moduleItemId=" + moduleItemId +
        ", moduleId=" + moduleId +
        ", ct=" + ct +
        ", status=" + status +
        ", type=" + type +
        ", recdate=" + recdate +
        ", version=" + version +
        "}";
    }
}
