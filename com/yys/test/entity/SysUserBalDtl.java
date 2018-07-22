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
@TableName("SYS_USER_BAL_DTL")
public class SysUserBalDtl extends Model<SysUserBalDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("USER_ID")
    private String userId;
    @TableField("ACTION")
    private String action;
    @TableField("VALUE")
    private String value;
    @TableField("MARK")
    private String mark;
    @TableField("FLAG")
    private String flag;
    @TableField("RTIME")
    private LocalDateTime rtime;
    @TableField("IS_DELETE")
    private String isDelete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LocalDateTime getRtime() {
        return rtime;
    }

    public void setRtime(LocalDateTime rtime) {
        this.rtime = rtime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUserBalDtl{" +
        ", id=" + id +
        ", userId=" + userId +
        ", action=" + action +
        ", value=" + value +
        ", mark=" + mark +
        ", flag=" + flag +
        ", rtime=" + rtime +
        ", isDelete=" + isDelete +
        "}";
    }
}
