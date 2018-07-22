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
@TableName("WX_MSG_IMGITEM")
public class WxMsgImgitem extends Model<WxMsgImgitem> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("TITLE")
    private String title;
    @TableField("MSGDESC")
    private String msgdesc;
    @TableField("DOCURL")
    private String docurl;
    @TableField("IMGURL")
    private String imgurl;
    @TableField("GROUP_ID")
    private String groupId;
    @TableField("DR")
    private Double dr;
    @TableField("MARK")
    private String mark;
    @TableField("RN")
    private Integer rn;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgdesc() {
        return msgdesc;
    }

    public void setMsgdesc(String msgdesc) {
        this.msgdesc = msgdesc;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public Integer getRn() {
        return rn;
    }

    public void setRn(Integer rn) {
        this.rn = rn;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WxMsgImgitem{" +
        ", id=" + id +
        ", title=" + title +
        ", msgdesc=" + msgdesc +
        ", docurl=" + docurl +
        ", imgurl=" + imgurl +
        ", groupId=" + groupId +
        ", dr=" + dr +
        ", mark=" + mark +
        ", rn=" + rn +
        "}";
    }
}
