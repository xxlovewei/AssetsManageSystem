package com.dt.module.cmdb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2018-12-30
 */
@TableName("RES")
public class Res extends BaseModel<Res> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("UUID")
    private String uuid;
    @TableField("CLASS_ID")
    private String classId;
    @TableField("SN")
    private String sn;
    @TableField("NAME")
    private String name;
    @TableField("DESCRIBE")
    private String describe;
    @TableField("MAINTAIN_USERID")
    private String maintainUserid;
    @TableField("HEADUSERID")
    private String headuserid;
    @TableField("PINP")
    private String pinp;
    @TableField("LOC")
    private String loc;
    @TableField("LOCSHOW")
    private String locshow;
    @TableField("FS1")
    private String fs1;
    @TableField("FS2")
    private String fs2;
    @TableField("FS3")
    private String fs3;
    @TableField("FS4")
    private String fs4;
    @TableField("FS5")
    private String fs5;
    @TableField("FS6")
    private String fs6;
    @TableField("FS7")
    private String fs7;
    @TableField("FS8")
    private String fs8;
    @TableField("FS9")
    private String fs9;
    @TableField("FS10")
    private String fs10;
    @TableField("FS11")
    private String fs11;
    @TableField("FS12")
    private String fs12;
    @TableField("FS13")
    private String fs13;
    @TableField("FS14")
    private String fs14;
    @TableField("FS15")
    private String fs15;
    @TableField("FS16")
    private String fs16;
    @TableField("FS17")
    private String fs17;
    @TableField("FS18")
    private String fs18;
    @TableField("FS19")
    private String fs19;
    @TableField("FS20")
    private String fs20;
    @TableField("FI1")
    private String fi1;
    @TableField("FI2")
    private String fi2;
    @TableField("FI3")
    private String fi3;
    @TableField("FI4")
    private String fi4;
    @TableField("FI5")
    private String fi5;
    @TableField("FI6")
    private String fi6;
    @TableField("FI7")
    private String fi7;
    @TableField("FI8")
    private String fi8;
    @TableField("FI9")
    private String fi9;
    @TableField("FI10")
    private String fi10;
    @TableField("FI11")
    private String fi11;
    @TableField("FI12")
    private String fi12;
    @TableField("FI13")
    private String fi13;
    @TableField("FI14")
    private String fi14;
    @TableField("FI15")
    private String fi15;
    @TableField("FI16")
    private String fi16;
    @TableField("FI17")
    private String fi17;
    @TableField("FI18")
    private String fi18;
    @TableField("FI19")
    private String fi19;
    @TableField("FI20")
    private String fi20;
    @TableField("FD1")
    private Date fd1;
    @TableField("FD2")
    private Date fd2;
    @TableField("FD3")
    private Date fd3;
    @TableField("STATUS")
    private String status;
    @TableField("ENV")
    private String env;
    @TableField("OS")
    private String os;
    @TableField("VERSION")
    private String version;
    @TableField("IMG")
    private String img;


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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public String getPinp() {
        return pinp;
    }

    public void setPinp(String pinp) {
        this.pinp = pinp;
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

    public String getFi1() {
        return fi1;
    }

    public void setFi1(String fi1) {
        this.fi1 = fi1;
    }

    public String getFi2() {
        return fi2;
    }

    public void setFi2(String fi2) {
        this.fi2 = fi2;
    }

    public String getFi3() {
        return fi3;
    }

    public void setFi3(String fi3) {
        this.fi3 = fi3;
    }

    public String getFi4() {
        return fi4;
    }

    public void setFi4(String fi4) {
        this.fi4 = fi4;
    }

    public String getFi5() {
        return fi5;
    }

    public void setFi5(String fi5) {
        this.fi5 = fi5;
    }

    public String getFi6() {
        return fi6;
    }

    public void setFi6(String fi6) {
        this.fi6 = fi6;
    }

    public String getFi7() {
        return fi7;
    }

    public void setFi7(String fi7) {
        this.fi7 = fi7;
    }

    public String getFi8() {
        return fi8;
    }

    public void setFi8(String fi8) {
        this.fi8 = fi8;
    }

    public String getFi9() {
        return fi9;
    }

    public void setFi9(String fi9) {
        this.fi9 = fi9;
    }

    public String getFi10() {
        return fi10;
    }

    public void setFi10(String fi10) {
        this.fi10 = fi10;
    }

    public String getFi11() {
        return fi11;
    }

    public void setFi11(String fi11) {
        this.fi11 = fi11;
    }

    public String getFi12() {
        return fi12;
    }

    public void setFi12(String fi12) {
        this.fi12 = fi12;
    }

    public String getFi13() {
        return fi13;
    }

    public void setFi13(String fi13) {
        this.fi13 = fi13;
    }

    public String getFi14() {
        return fi14;
    }

    public void setFi14(String fi14) {
        this.fi14 = fi14;
    }

    public String getFi15() {
        return fi15;
    }

    public void setFi15(String fi15) {
        this.fi15 = fi15;
    }

    public String getFi16() {
        return fi16;
    }

    public void setFi16(String fi16) {
        this.fi16 = fi16;
    }

    public String getFi17() {
        return fi17;
    }

    public void setFi17(String fi17) {
        this.fi17 = fi17;
    }

    public String getFi18() {
        return fi18;
    }

    public void setFi18(String fi18) {
        this.fi18 = fi18;
    }

    public String getFi19() {
        return fi19;
    }

    public void setFi19(String fi19) {
        this.fi19 = fi19;
    }

    public String getFi20() {
        return fi20;
    }

    public void setFi20(String fi20) {
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
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
        ", describe=" + describe +
        ", maintainUserid=" + maintainUserid +
        ", headuserid=" + headuserid +
        ", pinp=" + pinp +
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
        ", os=" + os +
        ", version=" + version +
        ", img=" + img +
        "}";
    }
}
