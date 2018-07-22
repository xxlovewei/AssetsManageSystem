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
@TableName("BUS_COMMENT")
public class BusComment extends Model<BusComment> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("ITEM_ID")
    private String itemId;
    @TableField("CT")
    private String ct;
    @TableField("DR")
    private Double dr;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("MDATE")
    private LocalDateTime mdate;
    @TableField("USER_ID")
    private String userId;
    @TableField("CM_ROLE")
    private String cmRole;
    @TableField("CODE")
    private String code;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCmRole() {
        return cmRole;
    }

    public void setCmRole(String cmRole) {
        this.cmRole = cmRole;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BusComment{" +
        ", id=" + id +
        ", itemId=" + itemId +
        ", ct=" + ct +
        ", dr=" + dr +
        ", cdate=" + cdate +
        ", mdate=" + mdate +
        ", userId=" + userId +
        ", cmRole=" + cmRole +
        ", code=" + code +
        "}";
    }
}
