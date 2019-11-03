package com.dt.module.cmdb.service;

import com.alibaba.fastjson.JSONObject;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @author: algernonking
 * @date: Oct 21, 2019 7:19:11 PM
 * @Description: TODO
 */
@ExcelTarget("ResEntity")
public class ResEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 用户提现导出
	@Excel(name = "资产编号", width = 16)
	private String uuid;

	@Excel(name = "大类(必需)", width = 15)
	private String classname;

	@Excel(name = "小类(不支持)", width = 15)
	private String typestr;

	@Excel(name = "资产名称(不支持)", width = 15)
	private String name;

	@Excel(name = "资产位置", width = 30)
	private String locstr;

	@Excel(name = "品牌", width = 10)
	private String brandstr;

	@Excel(name = "型号", width = 15)
	private String model;

	@Excel(name = "序列号", width = 12)
	private String sn;

	@Excel(name = "维保情况", width = 10)
	private String wbstr;

	@Excel(name = "脱保时间", width = 15)
	private String wbout_datestr;

	@Excel(name = "机柜", width = 10)
	private String rackstr;

	@Excel(name = "机架", width = 10)
	private String frame;

	@Excel(name = "位置详情", width = 30)
	private String locdtl;

	@Excel(name = "资产状态", width = 10)
	private String recyclestr;

	@Excel(name = "运行环境", width = 18)
	private String envstr;

	@Excel(name = "风险等级", width = 10)
	private String riskstr;

	@Excel(name = "使用部门(不支持)", width = 20)
	private String part_fullname;

//	@Excel(name = "管理部门", width = 18)
	private String mgr_part_fullname;

	@Excel(name = "使用人(不支持)", width = 20)
	private String used_username;

	@Excel(name = "采购时间", width = 15)
	private String buy_timestr;

	@Excel(name = "原值", width = 10)
	private String buy_price;

	@Excel(name = "配置描述", width = 30)
	private String confdesc;

	@Excel(name = "备注", width = 10)
	private String mark;

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the typestr
	 */
	public String getTypestr() {
		return typestr;
	}

	/**
	 * @param typestr the typestr to set
	 */
	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}

	/**
	 * @return the brandstr
	 */
	public String getBrandstr() {
		return brandstr;
	}

	/**
	 * @param brandstr the brandstr to set
	 */
	public void setBrandstr(String brandstr) {
		this.brandstr = brandstr;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the confdesc
	 */
	public String getConfdesc() {
		return confdesc;
	}

	/**
	 * @param confdesc the confdesc to set
	 */
	public void setConfdesc(String confdesc) {
		this.confdesc = confdesc;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the recyclestr
	 */
	public String getRecyclestr() {
		return recyclestr;
	}

	/**
	 * @param recyclestr the recyclestr to set
	 */
	public void setRecyclestr(String recyclestr) {
		this.recyclestr = recyclestr;
	}

	/**
	 * @return the wbstr
	 */
	public String getWbstr() {
		return wbstr;
	}

	/**
	 * @param wbstr the wbstr to set
	 */
	public void setWbstr(String wbstr) {
		this.wbstr = wbstr;
	}

	/**
	 * @return the envstr
	 */
	public String getEnvstr() {
		return envstr;
	}

	/**
	 * @param envstr the envstr to set
	 */
	public void setEnvstr(String envstr) {
		this.envstr = envstr;
	}

	/**
	 * @return the riskstr
	 */
	public String getRiskstr() {
		return riskstr;
	}

	/**
	 * @param riskstr the riskstr to set
	 */
	public void setRiskstr(String riskstr) {
		this.riskstr = riskstr;
	}

	/**
	 * @return the locstr
	 */
	public String getLocstr() {
		return locstr;
	}

	/**
	 * @param locstr the locstr to set
	 */
	public void setLocstr(String locstr) {
		this.locstr = locstr;
	}

	/**
	 * @return the rackstr
	 */
	public String getRackstr() {
		return rackstr;
	}

	/**
	 * @param rackstr the rackstr to set
	 */
	public void setRackstr(String rackstr) {
		this.rackstr = rackstr;
	}

	/**
	 * @return the frame
	 */
	public String getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(String frame) {
		this.frame = frame;
	}

	/**
	 * @return the buy_timestr
	 */
	public String getBuy_timestr() {
		return buy_timestr;
	}

	/**
	 * @param buy_timestr the buy_timestr to set
	 */
	public void setBuy_timestr(String buy_timestr) {
		this.buy_timestr = buy_timestr;
	}

	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the locdtl
	 */
	public String getLocdtl() {
		return locdtl;
	}

	/**
	 * @param locdtl the locdtl to set
	 */
	public void setLocdtl(String locdtl) {
		this.locdtl = locdtl;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the part_fullname
	 */
	public String getPart_fullname() {
		return part_fullname;
	}

	/**
	 * @param part_fullname the part_fullname to set
	 */
	public void setPart_fullname(String part_fullname) {
		this.part_fullname = part_fullname;
	}

	/**
	 * @return the mgr_part_fullname
	 */
	public String getMgr_part_fullname() {
		return mgr_part_fullname;
	}

	/**
	 * @param mgr_part_fullname the mgr_part_fullname to set
	 */
	public void setMgr_part_fullname(String mgr_part_fullname) {
		this.mgr_part_fullname = mgr_part_fullname;
	}

	/**
	 * @return the used_username
	 */
	public String getUsed_username() {
		return used_username;
	}

	/**
	 * @param used_username the used_username to set
	 */
	public void setUsed_username(String used_username) {
		this.used_username = used_username;
	}

	/**
	 * @return the buy_price
	 */
	public String getBuy_price() {
		return buy_price;
	}

	/**
	 * @param buy_price the buy_price to set
	 */
	public void setBuy_price(String buy_price) {
		this.buy_price = buy_price;
	}

	/**
	 * @return the wbout_datestr
	 */
	public String getWbout_datestr() {
		return wbout_datestr;
	}

	/**
	 * @param wbout_datestr the wbout_datestr to set
	 */
	public void setWbout_datestr(String wbout_datestr) {
		this.wbout_datestr = wbout_datestr;
	}

	public void fullResEntity(JSONObject obj) {

		this.uuid = obj.getString("uuid");
		this.classname = obj.getString("classname");
		this.wbout_datestr = obj.getString("wbout_datestr");
		this.typestr = obj.getString("typestr");
		this.brandstr = obj.getString("brandstr");
		this.model = obj.getString("model");
		this.confdesc = obj.getString("confdesc");
		this.sn = obj.getString("sn");
		this.recyclestr = obj.getString("recyclestr");
		this.wbstr = obj.getString("wbstr");
		this.envstr = obj.getString("envstr");
		this.riskstr = obj.getString("riskstr");
		this.locstr = obj.getString("locstr");
		this.rackstr = obj.getString("rackstr");
		this.frame = obj.getString("frame");
		this.buy_timestr = obj.getString("buy_timestr");
		this.mark = obj.getString("mark");

		this.buy_price = obj.getString("buy_price");
		this.name = obj.getString("name");
		this.part_fullname = obj.getString("part_fullname");
//		this.mgr_part_fullname = obj.getString("mgr_part_fullname");
		this.used_username = obj.getString("used_username");
		this.locdtl = obj.getString("locdtl");

	}

	/**
	 * @return the classname
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * @param classname the classname to set
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}

	private String processmsg = "";

	/**
	 * @return the processmsg
	 */
	public String getProcessmsg() {
		return processmsg;
	}

	/**
	 * @param processmsg the processmsg to set
	 */
	public void setProcessmsg(String processmsg) {
		this.processmsg = processmsg;
	}

}
