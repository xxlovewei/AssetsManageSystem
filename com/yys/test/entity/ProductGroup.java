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
@TableName("PRODUCT_GROUP")
public class ProductGroup extends Model<ProductGroup> {

    private static final long serialVersionUID = 1L;

    @TableId("GROUP_ID")
    private String groupId;
    @TableField("GROUP_NAME")
    private String groupName;
    @TableField("STATUS")
    private String status;
    @TableField("IS_DELETED")
    private String isDeleted;
    @TableField("TYPE")
    private String type;
    @TableField("MARK")
    private String mark;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.groupId;
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
        ", groupId=" + groupId +
        ", groupName=" + groupName +
        ", status=" + status +
        ", isDeleted=" + isDeleted +
        ", type=" + type +
        ", mark=" + mark +
        "}";
    }
}
