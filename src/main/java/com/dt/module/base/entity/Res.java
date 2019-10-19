package com.dt.module.base.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2019-10-19
 */
 
@TableName("res")
 
public class Res extends BaseModel<Res> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("uuid")
    private String uuid;
    @TableField("class_id")
    private String classId;
    @TableField("sn")
    private String sn;
    @TableField("name")
    private String name;
    @TableField("desc")
    private String desc;
    @TableField("maintain_userid")
    private String maintainUserid;
    @TableField("headuserid")
    private String headuserid;
    @TableField("brand")
    private String brand;
    @TableField("loc")
    private String loc;
    @TableField("locshow")
    private String locshow;
    @TableField("fs1")
    private String fs1;
    @TableField("fs2")
    private String fs2;
    @TableField("fs3")
    private String fs3;
    @TableField("fs4")
    private String fs4;
    @TableField("fs5")
    private String fs5;
    @TableField("fs6")
    private String fs6;
    @TableField("fs7")
    private String fs7;
    @TableField("fs8")
    private String fs8;
    @TableField("fs9")
    private String fs9;
    @TableField("fs10")
    private String fs10;
    @TableField("fs11")
    private String fs11;
    @TableField("fs12")
    private String fs12;
    @TableField("fs13")
    private String fs13;
    @TableField("fs14")
    private String fs14;
    @TableField("fs15")
    private String fs15;
    @TableField("fs16")
    private String fs16;
    @TableField("fs17")
    private String fs17;
    @TableField("fs18")
    private String fs18;
    @TableField("fs19")
    private String fs19;
    @TableField("fs20")
    private String fs20;
    @TableField("fi1")
    private BigDecimal fi1;
    @TableField("fi2")
    private BigDecimal fi2;
    @TableField("fi3")
    private BigDecimal fi3;
    @TableField("fi4")
    private BigDecimal fi4;
    @TableField("fi5")
    private BigDecimal fi5;
    @TableField("fi6")
    private BigDecimal fi6;
    @TableField("fi7")
    private BigDecimal fi7;
    @TableField("fi8")
    private BigDecimal fi8;
    @TableField("fi9")
    private BigDecimal fi9;
    @TableField("fi10")
    private BigDecimal fi10;
    @TableField("fi11")
    private BigDecimal fi11;
    @TableField("fi12")
    private BigDecimal fi12;
    @TableField("fi13")
    private BigDecimal fi13;
    @TableField("fi14")
    private BigDecimal fi14;
    @TableField("fi15")
    private BigDecimal fi15;
    @TableField("fi16")
    private BigDecimal fi16;
    @TableField("fi17")
    private BigDecimal fi17;
    @TableField("fi18")
    private BigDecimal fi18;
    @TableField("fi19")
    private BigDecimal fi19;
    @TableField("fi20")
    private BigDecimal fi20;
    @TableField("fd1")
    private Date fd1;
    @TableField("fd2")
    private Date fd2;
    @TableField("fd3")
    private Date fd3;
    @TableField("status")
    private String status;
    @TableField("env")
    private String env;
    @TableField("risk")
    private String risk;
    @TableField("version")
    private String version;
    @TableField("img")
    private String img;
    @TableField("supplier")
    private String supplier;
    @TableField("mark")
    private String mark;
    @TableField("recycle")
    private String recycle;
    @TableField("buy_time")
    private Date buyTime;
    @TableField("offline_time")
    private Date offlineTime;
    @TableField("online_time")
    private Date onlineTime;
    @TableField("ip")
    private String ip;
    @TableField("rwm")
    private String rwm;
    @TableField("model")
    private String model;
    @TableField("type")
    private String type;
    @TableField("frame")
    private String frame;
    @TableField("confdesc")
    private String confdesc;
    @TableField("wb")
    private String wb;
    @TableField("rack")
    private String rack;


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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMaintainUserid() {
        return maintainUserid;
    }

    public void setMaintainUserid(String maintainUserid) {
        this.maintainUserid = maintainUserid;
    }

    public String getHeaduserid() {
        return headuserid;
    }

    public void setHeaduserid(String headuserid) {
        this.headuserid = headuserid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLocshow() {
        return locshow;
    }

    public void setLocshow(String locshow) {
        this.locshow = locshow;
    }

    public String getFs1() {
        return fs1;
    }

    public void setFs1(String fs1) {
        this.fs1 = fs1;
    }

    public String getFs2() {
        return fs2;
    }

    public void setFs2(String fs2) {
        this.fs2 = fs2;
    }

    public String getFs3() {
        return fs3;
    }

    public void setFs3(String fs3) {
        this.fs3 = fs3;
    }

    public String getFs4() {
        return fs4;
    }

    public void setFs4(String fs4) {
        this.fs4 = fs4;
    }

    public String getFs5() {
        return fs5;
    }

    public void setFs5(String fs5) {
        this.fs5 = fs5;
    }

    public String getFs6() {
        return fs6;
    }

    public void setFs6(String fs6) {
        this.fs6 = fs6;
    }

    public String getFs7() {
        return fs7;
    }

    public void setFs7(String fs7) {
        this.fs7 = fs7;
    }

    public String getFs8() {
        return fs8;
    }

    public void setFs8(String fs8) {
        this.fs8 = fs8;
    }

    public String getFs9() {
        return fs9;
    }

    public void setFs9(String fs9) {
        this.fs9 = fs9;
    }

    public String getFs10() {
        return fs10;
    }

    public void setFs10(String fs10) {
        this.fs10 = fs10;
    }

    public String getFs11() {
        return fs11;
    }

    public void setFs11(String fs11) {
        this.fs11 = fs11;
    }

    public String getFs12() {
        return fs12;
    }

    public void setFs12(String fs12) {
        this.fs12 = fs12;
    }

    public String getFs13() {
        return fs13;
    }

    public void setFs13(String fs13) {
        this.fs13 = fs13;
    }

    public String getFs14() {
        return fs14;
    }

    public void setFs14(String fs14) {
        this.fs14 = fs14;
    }

    public String getFs15() {
        return fs15;
    }

    public void setFs15(String fs15) {
        this.fs15 = fs15;
    }

    public String getFs16() {
        return fs16;
    }

    public void setFs16(String fs16) {
        this.fs16 = fs16;
    }

    public String getFs17() {
        return fs17;
    }

    public void setFs17(String fs17) {
        this.fs17 = fs17;
    }

    public String getFs18() {
        return fs18;
    }

    public void setFs18(String fs18) {
        this.fs18 = fs18;
    }

    public String getFs19() {
        return fs19;
    }

    public void setFs19(String fs19) {
        this.fs19 = fs19;
    }

    public String getFs20() {
        return fs20;
    }

    public void setFs20(String fs20) {
        this.fs20 = fs20;
    }

    public BigDecimal getFi1() {
        return fi1;
    }

    public void setFi1(BigDecimal fi1) {
        this.fi1 = fi1;
    }

    public BigDecimal getFi2() {
        return fi2;
    }

    public void setFi2(BigDecimal fi2) {
        this.fi2 = fi2;
    }

    public BigDecimal getFi3() {
        return fi3;
    }

    public void setFi3(BigDecimal fi3) {
        this.fi3 = fi3;
    }

    public BigDecimal getFi4() {
        return fi4;
    }

    public void setFi4(BigDecimal fi4) {
        this.fi4 = fi4;
    }

    public BigDecimal getFi5() {
        return fi5;
    }

    public void setFi5(BigDecimal fi5) {
        this.fi5 = fi5;
    }

    public BigDecimal getFi6() {
        return fi6;
    }

    public void setFi6(BigDecimal fi6) {
        this.fi6 = fi6;
    }

    public BigDecimal getFi7() {
        return fi7;
    }

    public void setFi7(BigDecimal fi7) {
        this.fi7 = fi7;
    }

    public BigDecimal getFi8() {
        return fi8;
    }

    public void setFi8(BigDecimal fi8) {
        this.fi8 = fi8;
    }

    public BigDecimal getFi9() {
        return fi9;
    }

    public void setFi9(BigDecimal fi9) {
        this.fi9 = fi9;
    }

    public BigDecimal getFi10() {
        return fi10;
    }

    public void setFi10(BigDecimal fi10) {
        this.fi10 = fi10;
    }

    public BigDecimal getFi11() {
        return fi11;
    }

    public void setFi11(BigDecimal fi11) {
        this.fi11 = fi11;
    }

    public BigDecimal getFi12() {
        return fi12;
    }

    public void setFi12(BigDecimal fi12) {
        this.fi12 = fi12;
    }

    public BigDecimal getFi13() {
        return fi13;
    }

    public void setFi13(BigDecimal fi13) {
        this.fi13 = fi13;
    }

    public BigDecimal getFi14() {
        return fi14;
    }

    public void setFi14(BigDecimal fi14) {
        this.fi14 = fi14;
    }

    public BigDecimal getFi15() {
        return fi15;
    }

    public void setFi15(BigDecimal fi15) {
        this.fi15 = fi15;
    }

    public BigDecimal getFi16() {
        return fi16;
    }

    public void setFi16(BigDecimal fi16) {
        this.fi16 = fi16;
    }

    public BigDecimal getFi17() {
        return fi17;
    }

    public void setFi17(BigDecimal fi17) {
        this.fi17 = fi17;
    }

    public BigDecimal getFi18() {
        return fi18;
    }

    public void setFi18(BigDecimal fi18) {
        this.fi18 = fi18;
    }

    public BigDecimal getFi19() {
        return fi19;
    }

    public void setFi19(BigDecimal fi19) {
        this.fi19 = fi19;
    }

    public BigDecimal getFi20() {
        return fi20;
    }

    public void setFi20(BigDecimal fi20) {
        this.fi20 = fi20;
    }

    public Date getFd1() {
        return fd1;
    }

    public void setFd1(Date fd1) {
        this.fd1 = fd1;
    }

    public Date getFd2() {
        return fd2;
    }

    public void setFd2(Date fd2) {
        this.fd2 = fd2;
    }

    public Date getFd3() {
        return fd3;
    }

    public void setFd3(Date fd3) {
        this.fd3 = fd3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRwm() {
        return rwm;
    }

    public void setRwm(String rwm) {
        this.rwm = rwm;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getConfdesc() {
        return confdesc;
    }

    public void setConfdesc(String confdesc) {
        this.confdesc = confdesc;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Res{" +
        "id=" + id +
        ", uuid=" + uuid +
        ", classId=" + classId +
        ", sn=" + sn +
        ", name=" + name +
        ", desc=" + desc +
        ", maintainUserid=" + maintainUserid +
        ", headuserid=" + headuserid +
        ", brand=" + brand +
        ", loc=" + loc +
        ", locshow=" + locshow +
        ", fs1=" + fs1 +
        ", fs2=" + fs2 +
        ", fs3=" + fs3 +
        ", fs4=" + fs4 +
        ", fs5=" + fs5 +
        ", fs6=" + fs6 +
        ", fs7=" + fs7 +
        ", fs8=" + fs8 +
        ", fs9=" + fs9 +
        ", fs10=" + fs10 +
        ", fs11=" + fs11 +
        ", fs12=" + fs12 +
        ", fs13=" + fs13 +
        ", fs14=" + fs14 +
        ", fs15=" + fs15 +
        ", fs16=" + fs16 +
        ", fs17=" + fs17 +
        ", fs18=" + fs18 +
        ", fs19=" + fs19 +
        ", fs20=" + fs20 +
        ", fi1=" + fi1 +
        ", fi2=" + fi2 +
        ", fi3=" + fi3 +
        ", fi4=" + fi4 +
        ", fi5=" + fi5 +
        ", fi6=" + fi6 +
        ", fi7=" + fi7 +
        ", fi8=" + fi8 +
        ", fi9=" + fi9 +
        ", fi10=" + fi10 +
        ", fi11=" + fi11 +
        ", fi12=" + fi12 +
        ", fi13=" + fi13 +
        ", fi14=" + fi14 +
        ", fi15=" + fi15 +
        ", fi16=" + fi16 +
        ", fi17=" + fi17 +
        ", fi18=" + fi18 +
        ", fi19=" + fi19 +
        ", fi20=" + fi20 +
        ", fd1=" + fd1 +
        ", fd2=" + fd2 +
        ", fd3=" + fd3 +
        ", status=" + status +
        ", env=" + env +
        ", risk=" + risk +
        ", version=" + version +
        ", img=" + img +
        ", supplier=" + supplier +
        ", mark=" + mark +
        ", recycle=" + recycle +
        ", buyTime=" + buyTime +
        ", offlineTime=" + offlineTime +
        ", onlineTime=" + onlineTime +
        ", ip=" + ip +
        ", rwm=" + rwm +
        ", model=" + model +
        ", type=" + type +
        ", frame=" + frame +
        ", confdesc=" + confdesc +
        ", wb=" + wb +
        ", rack=" + rack +
        "}";
    }
}
