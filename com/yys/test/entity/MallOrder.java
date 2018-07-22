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
@TableName("MALL_ORDER")
public class MallOrder extends Model<MallOrder> {

    private static final long serialVersionUID = 1L;

    @TableId("ORDER_ID")
    private String orderId;
    @TableField("ORDER_TYPE")
    private String orderType;
    @TableField("USER_ID")
    private String userId;
    @TableField("ISNEEDLOGISTICS")
    private String isneedlogistics;
    @TableField("AMOUNTREAL")
    private Long amountreal;
    @TableField("PROVINCEID")
    private String provinceid;
    @TableField("CITYID")
    private String cityid;
    @TableField("AREAID")
    private String areaid;
    @TableField("ADDRESS")
    private String address;
    @TableField("LINKMAN")
    private String linkman;
    @TableField("MOBILE")
    private String mobile;
    @TableField("CODE")
    private String code;
    @TableField("STATUS")
    private Integer status;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("DTL_NUMBER")
    private Integer dtlNumber;
    @TableField("REMARK")
    private String remark;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("MDATE")
    private LocalDateTime mdate;
    @TableField("CALCUTE")
    private String calcute;
    @TableField("ID")
    private String id;
    @TableField("AMOUNT")
    private Long amount;
    @TableField("YUNPRICE")
    private Long yunprice;
    @TableField("IS_PAY")
    private String isPay;
    @TableField("PREPAY_ID")
    private String prepayId;
    @TableField("PDATE")
    private LocalDateTime pdate;
    @TableField("FDATE")
    private LocalDateTime fdate;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsneedlogistics() {
        return isneedlogistics;
    }

    public void setIsneedlogistics(String isneedlogistics) {
        this.isneedlogistics = isneedlogistics;
    }

    public Long getAmountreal() {
        return amountreal;
    }

    public void setAmountreal(Long amountreal) {
        this.amountreal = amountreal;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getDtlNumber() {
        return dtlNumber;
    }

    public void setDtlNumber(Integer dtlNumber) {
        this.dtlNumber = dtlNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public LocalDateTime getMdate() {
        return mdate;
    }

    public void setMdate(LocalDateTime mdate) {
        this.mdate = mdate;
    }

    public String getCalcute() {
        return calcute;
    }

    public void setCalcute(String calcute) {
        this.calcute = calcute;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getYunprice() {
        return yunprice;
    }

    public void setYunprice(Long yunprice) {
        this.yunprice = yunprice;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public LocalDateTime getPdate() {
        return pdate;
    }

    public void setPdate(LocalDateTime pdate) {
        this.pdate = pdate;
    }

    public LocalDateTime getFdate() {
        return fdate;
    }

    public void setFdate(LocalDateTime fdate) {
        this.fdate = fdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

    @Override
    public String toString() {
        return "MallOrder{" +
        ", orderId=" + orderId +
        ", orderType=" + orderType +
        ", userId=" + userId +
        ", isneedlogistics=" + isneedlogistics +
        ", amountreal=" + amountreal +
        ", provinceid=" + provinceid +
        ", cityid=" + cityid +
        ", areaid=" + areaid +
        ", address=" + address +
        ", linkman=" + linkman +
        ", mobile=" + mobile +
        ", code=" + code +
        ", status=" + status +
        ", isDelete=" + isDelete +
        ", dtlNumber=" + dtlNumber +
        ", remark=" + remark +
        ", cdate=" + cdate +
        ", mdate=" + mdate +
        ", calcute=" + calcute +
        ", id=" + id +
        ", amount=" + amount +
        ", yunprice=" + yunprice +
        ", isPay=" + isPay +
        ", prepayId=" + prepayId +
        ", pdate=" + pdate +
        ", fdate=" + fdate +
        "}";
    }
}
