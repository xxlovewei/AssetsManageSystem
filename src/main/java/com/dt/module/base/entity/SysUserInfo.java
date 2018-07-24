package com.dt.module.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@TableName("SYS_USER_INFO")
public class SysUserInfo extends Model<SysUserInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;
    @TableField("EMPL_ID")
    private String emplId;
    /**
     * user_name,建议唯一
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 用户类型:sys,crm,wx
     */
    @TableField("USER_TYPE")
    private String userType;
    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;
    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;
    @TableField("PWD")
    private String pwd;
    @TableField("STATUS")
    private String status;
    /**
     * 组织Id
     */
    @TableField("ORG_ID")
    private String orgId;
    /**
     * Y|N
     */
    @TableField("LOCKED")
    private String locked;
    @TableField("TOKEN")
    private String token;
    /**
     * 手机号码
     */
    @TableField("TEL")
    private String tel;
    @TableField("QQ")
    private String qq;
    @TableField("MAIL")
    private String mail;
    @TableField("PROFILE")
    private String profile;
    /**
     * 备注
     */
    @TableField("MARK")
    private String mark;
    /**
     * 家庭地址
     */
    @TableField("HOMEADDR_DEF")
    private String homeaddrDef;
    /**
     * 收货地址
     */
    @TableField("RECEADDR_DEF")
    private String receaddrDef;
    @TableField("BIRTH")
    private Date birth;
    @TableField("WEIXIN")
    private String weixin;
    /**
     * 1男,2女
     */
    @TableField("SEX")
    private String sex;
    
    @TableField(exist=false)
    private String sexName;
    
    /**
	 * @return the sexName
	 */
	public String getSexName() {
		return sexName;
	}

	/**
	 * @param sexName the sexName to set
	 */
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	@TableField(value = "DR", fill = FieldFill.INSERT)
    @TableLogic
    private String dr;
    /**
     * 头像
     */
    @TableField("PHOTO")
    private String photo;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("CREATE_IP")
    private String createIp;
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;
    @TableField("LAST_LOGIN_IP")
    private String lastLoginIp;
    /**
     * 我的系统默认菜单
     */
    @TableField("SYSTEM")
    private String system;
    /**
     * 微信open_id
     */
    @TableField("OPEN_ID")
    private String openId;
    /**
     * 积分
     */
    @TableField("SCORE")
    private String score;
    /**
     * 我的店铺
     */
    @TableField("SHOP_ID")
    private String shopId;
    /**
     * 微信头像
     */
    @TableField("AVATARURL")
    private String avatarurl;
    @TableField("BALANCE")
    private String balance;
    /**
     * 结算卡ID
     */
    @TableField("CARD")
    private String card;
    /**
     * 余额
     */
    @TableField("AMOUNT")
    private String amount;
    /**
     * 其他冻结金额
     */
    @TableField("FAMOUNT")
    private String famount;
    /**
     * 提现金额
     */
    @TableField("TIXAMOUNT")
    private String tixamount;
    /**
     * 信用分
     */
    @TableField("CREDIT_SCORE")
    private String creditScore;
    /**
     * 身份证
     */
    @TableField("IDENTITY_CARD")
    private String identityCard;
    /**
     * 驾照
     */
    @TableField("DRIVER_CARD")
    private String driverCard;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 民族
     */
    @TableField("NATION")
    private String nation;
    /**
     * 籍贯
     */
    @TableField("NATIVE_PLACE")
    private String nativePlace;
    /**
     * 自我评价
     */
    @TableField("SELF_EVALUATE")
    private String selfEvaluate;
    @TableField("ALI_PAY_USERNAME")
    private String aliPayUsername;
    @TableField("ALI_PAY_ACCOUNT")
    private String aliPayAccount;
    /**
     * 手机短号
     */
    @TableField("SHORTMOBILE")
    private String shortmobile;
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getHomeaddrDef() {
        return homeaddrDef;
    }

    public void setHomeaddrDef(String homeaddrDef) {
        this.homeaddrDef = homeaddrDef;
    }

    public String getReceaddrDef() {
        return receaddrDef;
    }

    public void setReceaddrDef(String receaddrDef) {
        this.receaddrDef = receaddrDef;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFamount() {
        return famount;
    }

    public void setFamount(String famount) {
        this.famount = famount;
    }

    public String getTixamount() {
        return tixamount;
    }

    public void setTixamount(String tixamount) {
        this.tixamount = tixamount;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDriverCard() {
        return driverCard;
    }

    public void setDriverCard(String driverCard) {
        this.driverCard = driverCard;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSelfEvaluate() {
        return selfEvaluate;
    }

    public void setSelfEvaluate(String selfEvaluate) {
        this.selfEvaluate = selfEvaluate;
    }

    public String getAliPayUsername() {
        return aliPayUsername;
    }

    public void setAliPayUsername(String aliPayUsername) {
        this.aliPayUsername = aliPayUsername;
    }

    public String getAliPayAccount() {
        return aliPayAccount;
    }

    public void setAliPayAccount(String aliPayAccount) {
        this.aliPayAccount = aliPayAccount;
    }

    public String getShortmobile() {
        return shortmobile;
    }

    public void setShortmobile(String shortmobile) {
        this.shortmobile = shortmobile;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "SysUserInfo{" +
        ", userId=" + userId +
        ", emplId=" + emplId +
        ", userName=" + userName +
        ", userType=" + userType +
        ", nickname=" + nickname +
        ", name=" + name +
        ", pwd=" + pwd +
        ", status=" + status +
        ", orgId=" + orgId +
        ", locked=" + locked +
        ", token=" + token +
        ", tel=" + tel +
        ", qq=" + qq +
        ", mail=" + mail +
        ", profile=" + profile +
        ", mark=" + mark +
        ", homeaddrDef=" + homeaddrDef +
        ", receaddrDef=" + receaddrDef +
        ", birth=" + birth +
        ", weixin=" + weixin +
        ", sex=" + sex +
        ", dr=" + dr +
        ", photo=" + photo +
        ", createTime=" + createTime +
        ", createIp=" + createIp +
        ", lastLoginTime=" + lastLoginTime +
        ", lastLoginIp=" + lastLoginIp +
        ", system=" + system +
        ", openId=" + openId +
        ", score=" + score +
        ", shopId=" + shopId +
        ", avatarurl=" + avatarurl +
        ", balance=" + balance +
        ", card=" + card +
        ", amount=" + amount +
        ", famount=" + famount +
        ", tixamount=" + tixamount +
        ", creditScore=" + creditScore +
        ", identityCard=" + identityCard +
        ", driverCard=" + driverCard +
        ", updateTime=" + updateTime +
        ", nation=" + nation +
        ", nativePlace=" + nativePlace +
        ", selfEvaluate=" + selfEvaluate +
        ", aliPayUsername=" + aliPayUsername +
        ", aliPayAccount=" + aliPayAccount +
        ", shortmobile=" + shortmobile +
        ", createBy=" + createBy +
        ", updateBy=" + updateBy +
        "}";
    }
}
