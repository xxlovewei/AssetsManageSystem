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
@TableName("WX_MSG_DEF")
public class WxMsgDef extends Model<WxMsgDef> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("CODE")
    private String code;
    @TableField("NAME")
    private String name;
    @TableField("MSGTYPE")
    private String msgtype;
    @TableField("VALUE")
    private String value;
    @TableField("DR")
    private Double dr;
    @TableField("MARK")
    private String mark;
    @TableField("GROUP_ID")
    private String groupId;
    @TableField("FUNTYPE")
    private String funtype;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFuntype() {
        return funtype;
    }

    public void setFuntype(String funtype) {
        this.funtype = funtype;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WxMsgDef{" +
        ", id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", msgtype=" + msgtype +
        ", value=" + value +
        ", dr=" + dr +
        ", mark=" + mark +
        ", groupId=" + groupId +
        ", funtype=" + funtype +
        "}";
    }
}
