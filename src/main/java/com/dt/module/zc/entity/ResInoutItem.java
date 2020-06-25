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
 * @since 2020-05-27
 */
 
@TableName("res_inout_item")
 
public class ResInoutItem extends BaseModel<ResInoutItem> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;
    /**
     * 批次号
     */
    @TableField("batchno")
    private String batchno;
    @TableField("crkstatus")
    private String crkstatus;
    /**
     * 数量
     */
    @TableField("zc_cnt")
    private BigDecimal zcCnt;
    /**
     * 出库记录物品ID
     */
    @TableField("resid")
    private String resid;
    /**
     * 单据号
     */
    @TableField("uuid")
    private String uuid;
    /**
     * 物品ID
     */
    @TableField("class_id")
    private String classId;
    /**
     * 厂商
     */
    @TableField("zc_category")
    private String zcCategory;
    /**
     * 供应商
     */
    @TableField("supplier")
    private String supplier;
    /**
     * 购买时间
     */
    @TableField("buy_time")
    private Date buyTime;
    /**
     * 购买总价
     */
    @TableField("buy_price")
    private BigDecimal buyPrice;
    /**
     * 购买单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;
    /**
     * 资产区域
     */
    @TableField("loc")
    private String loc;
    /**
     * 存放仓库
     */
    @TableField("warehouse")
    private String warehouse;
    /**
     * 所属公司
     */
    @TableField("belong_company_id")
    private String belongCompanyId;
    /**
     * 所属部门
     */
    @TableField("belong_part_id")
    private String belongPartId;
    /**
     * 使用公司Id
     */
    @TableField("used_company_id")
    private String usedCompanyId;
    /**
     * 使用部门Id
     */
    @TableField("part_id")
    private String partId;
    /**
     * 使用人Id
     */
    @TableField("used_userid")
    private String usedUserid;
    @TableField("mark")
    private String mark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getCrkstatus() {
        return crkstatus;
    }

    public void setCrkstatus(String crkstatus) {
        this.crkstatus = crkstatus;
    }

    public BigDecimal getZcCnt() {
        return zcCnt;
    }

    public void setZcCnt(BigDecimal zcCnt) {
        this.zcCnt = zcCnt;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getZcCategory() {
        return zcCategory;
    }

    public void setZcCategory(String zcCategory) {
        this.zcCategory = zcCategory;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public String getBelongCompanyId() {
        return belongCompanyId;
    }

    public void setBelongCompanyId(String belongCompanyId) {
        this.belongCompanyId = belongCompanyId;
    }

    public String getBelongPartId() {
        return belongPartId;
    }

    public void setBelongPartId(String belongPartId) {
        this.belongPartId = belongPartId;
    }

    public String getUsedCompanyId() {
        return usedCompanyId;
    }

    public void setUsedCompanyId(String usedCompanyId) {
        this.usedCompanyId = usedCompanyId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getUsedUserid() {
        return usedUserid;
    }

    public void setUsedUserid(String usedUserid) {
        this.usedUserid = usedUserid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ResInoutItem{" +
        "id=" + id +
        ", batchno=" + batchno +
        ", crkstatus=" + crkstatus +
        ", zcCnt=" + zcCnt +
        ", resid=" + resid +
        ", uuid=" + uuid +
        ", classId=" + classId +
        ", zcCategory=" + zcCategory +
        ", supplier=" + supplier +
        ", buyTime=" + buyTime +
        ", buyPrice=" + buyPrice +
        ", unitPrice=" + unitPrice +
        ", loc=" + loc +
        ", warehouse=" + warehouse +
        ", belongCompanyId=" + belongCompanyId +
        ", belongPartId=" + belongPartId +
        ", usedCompanyId=" + usedCompanyId +
        ", partId=" + partId +
        ", usedUserid=" + usedUserid +
        ", mark=" + mark +
        "}";
    }
}