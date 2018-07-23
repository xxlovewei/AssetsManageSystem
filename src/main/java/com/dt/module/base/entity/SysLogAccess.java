package com.dt.module.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2018-07-23
 */
@TableName("SYS_LOG_ACCESS")
public class SysLogAccess extends Model<SysLogAccess> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("USER_ID")
    private String userId;
    @TableField("IP")
    private String ip;
    @TableField("RTIME")
    private Date rtime;
    @TableField("TYPE")
    private String type;
    @TableField("URL")
    private String url;
    @TableField("POSTORGET")
    private String postorget;
    @TableField("REMARK")
    private String remark;
    @TableField("METHOD_TYPE")
    private String methodType;
    @TableField("INFO")
    private String info;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostorget() {
        return postorget;
    }

    public void setPostorget(String postorget) {
        this.postorget = postorget;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysLogAccess{" +
        ", id=" + id +
        ", userId=" + userId +
        ", ip=" + ip +
        ", rtime=" + rtime +
        ", type=" + type +
        ", url=" + url +
        ", postorget=" + postorget +
        ", remark=" + remark +
        ", methodType=" + methodType +
        ", info=" + info +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
