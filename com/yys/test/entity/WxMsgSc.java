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
@TableName("WX_MSG_SC")
public class WxMsgSc extends Model<WxMsgSc> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("PIC_ID")
    private String picId;
    @TableField("SCTYPE")
    private String sctype;
    @TableField("DR")
    private Double dr;
    @TableField("CTIME")
    private LocalDateTime ctime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getSctype() {
        return sctype;
    }

    public void setSctype(String sctype) {
        this.sctype = sctype;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WxMsgSc{" +
        ", id=" + id +
        ", picId=" + picId +
        ", sctype=" + sctype +
        ", dr=" + dr +
        ", ctime=" + ctime +
        "}";
    }
}
