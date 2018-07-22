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
@TableName("WX_APPS")
public class WxApps extends Model<WxApps> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;
    @TableField("NAME")
    private String name;
    @TableField("APP_ID")
    private String appId;
    @TableField("DR")
    private Double dr;
    @TableField("MARK")
    private String mark;
    @TableField("MENU")
    private String menu;
    @TableField("SECRET")
    private String secret;
    @TableField("CDATE")
    private LocalDateTime cdate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "WxApps{" +
        ", id=" + id +
        ", name=" + name +
        ", appId=" + appId +
        ", dr=" + dr +
        ", mark=" + mark +
        ", menu=" + menu +
        ", secret=" + secret +
        ", cdate=" + cdate +
        "}";
    }
}
