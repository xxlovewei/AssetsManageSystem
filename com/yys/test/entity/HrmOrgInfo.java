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
@TableName("HRM_ORG_INFO")
public class HrmOrgInfo extends Model<HrmOrgInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("ORG_ID")
    private Integer orgId;
    @TableField("ORG_NAME")
    private String orgName;
    @TableField("IS_ACTION")
    private String isAction;
    @TableField("DELETED")
    private String deleted;


    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIsAction() {
        return isAction;
    }

    public void setIsAction(String isAction) {
        this.isAction = isAction;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.orgId;
    }

    @Override
    public String toString() {
        return "HrmOrgInfo{" +
        ", orgId=" + orgId +
        ", orgName=" + orgName +
        ", isAction=" + isAction +
        ", deleted=" + deleted +
        "}";
    }
}
