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
@TableName("SYS_NOTICE")
public class SysNotice extends Model<SysNotice> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("TYPE")
    private String type;
    @TableField("TITLE")
    private String title;
    @TableField("CT")
    private String ct;
    @TableField("RDATE")
    private LocalDateTime rdate;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("IS_SHOW")
    private String isShow;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("USER_ID")
    private String userId;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public LocalDateTime getRdate() {
        return rdate;
    }

    public void setRdate(LocalDateTime rdate) {
        this.rdate = rdate;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysNotice{" +
        ", id=" + id +
        ", type=" + type +
        ", title=" + title +
        ", ct=" + ct +
        ", rdate=" + rdate +
        ", cdate=" + cdate +
        ", isShow=" + isShow +
        ", isDelete=" + isDelete +
        ", userId=" + userId +
        "}";
    }
}
