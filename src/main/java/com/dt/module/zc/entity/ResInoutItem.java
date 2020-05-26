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
 * @since 2020-05-25
 */
 
@TableName("res_inout_item")
 
public class ResInoutItem extends BaseModel<ResInoutItem> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;
    @TableField("uuid")
    private String uuid;
    @TableField("class_id")
    private String classId;
    @TableField("zc_category")
    private String zcCategory;
    @TableField("cnt")
    private BigDecimal cnt;
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
    @TableField("price")
    private BigDecimal price;
    /**
     * 购买单价
     */
    @TableField("unit")
    private BigDecimal unit;
    /**
     * 配置描述
     */
    @TableField("confdesc")
    private String confdesc;
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
     * 归属公司
     */
    @TableField("belong_company_id")
    private String belongCompanyId;
    /**
     * 归属部门
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
    /**
     * 管理部门Id
     */
    @TableField("mgr_part_id")
    private String mgrPartId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getCnt() {
        return cnt;
    }

    public void setCnt(BigDecimal cnt) {
        this.cnt = cnt;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public String getConfdesc() {
        return confdesc;
    }

    public void setConfdesc(String confdesc) {
        this.confdesc = confdesc;
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

    public String getMgrPartId() {
        return mgrPartId;
    }

    public void setMgrPartId(String mgrPartId) {
        this.mgrPartId = mgrPartId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ResInoutItem{" +
        "id=" + id +
        ", uuid=" + uuid +
        ", classId=" + classId +
        ", zcCategory=" + zcCategory +
        ", cnt=" + cnt +
        ", supplier=" + supplier +
        ", buyTime=" + buyTime +
        ", price=" + price +
        ", unit=" + unit +
        ", confdesc=" + confdesc +
        ", loc=" + loc +
        ", warehouse=" + warehouse +
        ", belongCompanyId=" + belongCompanyId +
        ", belongPartId=" + belongPartId +
        ", usedCompanyId=" + usedCompanyId +
        ", partId=" + partId +
        ", usedUserid=" + usedUserid +
        ", mgrPartId=" + mgrPartId +
        "}";
    }
}
