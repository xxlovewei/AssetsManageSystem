package com.yys.test.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dt.module.common.bus_benum.sexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2018-07-22
 */
public class Dtuser extends Model<Dtuser> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("USERNAME")
    private String username;
    @TableField("PASSWORD")
    private String password;
    
    @TableField("SEX")
    private String sex;
    
 
    
    
    @TableField("GROUP_ID")
    private String groupId;
 
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("BIRTHDATA")
    private Date birthdata;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "DR", fill = FieldFill.INSERT)
    @TableLogic
    private String dr;
    @TableField("AMOUNT")
    private String amount;
    @TableField("XUEH")
    private String xueh;
    @TableField(value = "REMARK", fill = FieldFill.INSERT)
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getBirthdata() {
        return birthdata;
    }

    public void setBirthdata(Date birthdata) {
        this.birthdata = birthdata;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getXueh() {
        return xueh;
    }

    public void setXueh(String xueh) {
        this.xueh = xueh;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Dtuser{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", sex=" + sex +
        ", groupId=" + groupId +
        ", birthdata=" + birthdata +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", dr=" + dr +
        ", amount=" + amount +
        ", xueh=" + xueh +
        ", remark=" + remark +
        "}";
    }
}
