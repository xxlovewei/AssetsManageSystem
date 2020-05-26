package com.dt.module.zc.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2020-05-26
 */
 
@TableName("res_inout")
 
public class ResInout extends BaseModel<ResInout> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;
    @TableField("type")
    private String type;
    @TableField("uuid")
    private String uuid;
    @TableField("title")
    private String title;
    @TableField("zcsource")
    private String zcsource;
    /**
     * 供应商
     */
    @TableField("suppliername")
    private String suppliername;
    /**
     * 购买时间
     */
    @TableField("buytime")
    private String buytime;
    /**
     * 购买总价
     */
    @TableField("price")
    private BigDecimal price;
    @TableField("operuserid")
    private String operuserid;
    @TableField("operusername")
    private String operusername;
    @TableField("rdate")
    private Date rdate;
    @TableField("busidate")
    private Date busidate;
    @TableField("loc")
    private String loc;
    @TableField("warehouse")
    private String warehouse;
    @TableField("mark")
    private String mark;
    @TableField("label1")
    private String label1;
    @TableField("label2")
    private String label2;
    @TableField("remark")
    private String remark;
    @TableField("action")
    private String action;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZcsource() {
        return zcsource;
    }

    public void setZcsource(String zcsource) {
        this.zcsource = zcsource;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getBuytime() {
        return buytime;
    }

    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOperuserid() {
        return operuserid;
    }

    public void setOperuserid(String operuserid) {
        this.operuserid = operuserid;
    }

    public String getOperusername() {
        return operusername;
    }

    public void setOperusername(String operusername) {
        this.operusername = operusername;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Date getBusidate() {
        return busidate;
    }

    public void setBusidate(Date busidate) {
        this.busidate = busidate;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ResInout{" +
        "id=" + id +
        ", type=" + type +
        ", uuid=" + uuid +
        ", title=" + title +
        ", zcsource=" + zcsource +
        ", suppliername=" + suppliername +
        ", buytime=" + buytime +
        ", price=" + price +
        ", operuserid=" + operuserid +
        ", operusername=" + operusername +
        ", rdate=" + rdate +
        ", busidate=" + busidate +
        ", loc=" + loc +
        ", warehouse=" + warehouse +
        ", mark=" + mark +
        ", label1=" + label1 +
        ", label2=" + label2 +
        ", remark=" + remark +
        ", action=" + action +
        "}";
    }
}
