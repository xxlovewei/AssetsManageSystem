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
@TableName("SYS_LOG_LOGIN")
public class SysLogLogin extends Model<SysLogLogin> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("IP")
    private String ip;
    @TableField("USER_ID")
    private String userId;
    @TableField("RDATE")
    private LocalDateTime rdate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getRdate() {
        return rdate;
    }

    public void setRdate(LocalDateTime rdate) {
        this.rdate = rdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysLogLogin{" +
        ", id=" + id +
        ", ip=" + ip +
        ", userId=" + userId +
        ", rdate=" + rdate +
        "}";
    }
}
