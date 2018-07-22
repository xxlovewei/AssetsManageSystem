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
@TableName("MN_WARN_CONTINUE")
public class MnWarnContinue extends Model<MnWarnContinue> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NODE")
    private String node;
    @TableField("CNT")
    private Integer cnt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MnWarnContinue{" +
        ", id=" + id +
        ", node=" + node +
        ", cnt=" + cnt +
        "}";
    }
}
