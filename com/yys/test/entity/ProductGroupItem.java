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
@TableName("PRODUCT_GROUP_ITEM")
public class ProductGroupItem extends Model<ProductGroupItem> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;
    @TableId("GROUP_ID")
    private String groupId;
    @TableField("STATUS")
    private String status;
    @TableField("SPU")
    private Long spu;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSpu() {
        return spu;
    }

    public void setSpu(Long spu) {
        this.spu = spu;
    }

    @Override
    protected Serializable pkVal() {
        return this.groupId;
    }

    @Override
    public String toString() {
        return "ProductGroupItem{" +
        ", id=" + id +
        ", groupId=" + groupId +
        ", status=" + status +
        ", spu=" + spu +
        "}";
    }
}
