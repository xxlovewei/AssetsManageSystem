package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("MN_NODE_LOG")
public class MnNodeLog extends Model<MnNodeLog> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("TYPE")
    private String type;
    @TableField("MLEVEL")
    private Double mlevel;
    @TableField("MESSAGE")
    private String message;
    @TableField("MARK")
    private String mark;
    @TableField("INSERTTIME")
    private LocalDateTime inserttime;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMlevel() {
        return mlevel;
    }

    public void setMlevel(Double mlevel) {
        this.mlevel = mlevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnNodeLog{" +
        ", node=" + node +
        ", type=" + type +
        ", mlevel=" + mlevel +
        ", message=" + message +
        ", mark=" + mark +
        ", inserttime=" + inserttime +
        "}";
    }
}
