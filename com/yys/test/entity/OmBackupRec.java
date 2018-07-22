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
@TableName("OM_BACKUP_REC")
public class OmBackupRec extends Model<OmBackupRec> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("APP_ID")
    private String appId;
    @TableField("SDATE")
    private LocalDateTime sdate;
    @TableField("EDATE")
    private LocalDateTime edate;
    @TableField("RECDATE")
    private LocalDateTime recdate;
    @TableField("DATA_SIZE")
    private Long dataSize;
    @TableField("MARK")
    private String mark;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("SOURCE")
    private String source;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public LocalDateTime getSdate() {
        return sdate;
    }

    public void setSdate(LocalDateTime sdate) {
        this.sdate = sdate;
    }

    public LocalDateTime getEdate() {
        return edate;
    }

    public void setEdate(LocalDateTime edate) {
        this.edate = edate;
    }

    public LocalDateTime getRecdate() {
        return recdate;
    }

    public void setRecdate(LocalDateTime recdate) {
        this.recdate = recdate;
    }

    public Long getDataSize() {
        return dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OmBackupRec{" +
        ", id=" + id +
        ", appId=" + appId +
        ", sdate=" + sdate +
        ", edate=" + edate +
        ", recdate=" + recdate +
        ", dataSize=" + dataSize +
        ", mark=" + mark +
        ", isDelete=" + isDelete +
        ", source=" + source +
        "}";
    }
}
