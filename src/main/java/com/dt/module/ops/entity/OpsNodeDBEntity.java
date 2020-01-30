package com.dt.module.ops.entity;

import com.alibaba.fastjson.JSONObject;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2020-01-24
 */
@ExcelTarget("OpsNodeDBEntity")
public class OpsNodeDBEntity {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Excel(name = "编号", width = 30)
	private String id;

	@Excel(name = "系统", width = 20)
	private String xtname;
	@Excel(name = "IP", width = 15)
	private String ip;

	@Excel(name = "数据库", width = 15)
	private String sysdbdtlstr;

	@Excel(name = "数据库名称", width = 15)
	private String dbinstance;

	@Excel(name = "当前状况", width = 15)
	private String dbbkstatusstr;

	@Excel(name = "备份类型", width = 15)
	private String dbbktypestr;

	@Excel(name = "备份方式", width = 15)
	private String dbbkmethodstr;

	@Excel(name = "日志模式", width = 15)
	private String dbbkarchtypestr;

	@Excel(name = "备份策略", width = 15)
	private String bkstrategy;

	@Excel(name = "保留策略", width = 15)
	private String bkkeep;

	@Excel(name = "备注", width = 15)
	private String mark;

	public void fullEntity(JSONObject obj) {
		this.id = obj.getString("id");
		this.xtname = obj.getString("xtname");
		this.ip = obj.getString("ip");
		this.sysdbdtlstr = obj.getString("sysdbdtlstr");
		this.dbinstance = obj.getString("dbinstance");
		this.dbbkstatusstr = obj.getString("dbbkstatusstr");
		this.dbbktypestr = obj.getString("dbbktypestr");
		this.dbbkmethodstr = obj.getString("dbbkmethodstr");
		this.dbbkarchtypestr = obj.getString("dbbkarchtypestr");
		this.bkstrategy = obj.getString("bkstrategy");
		this.bkkeep = obj.getString("bkkeep");
		this.mark = obj.getString("mark");

	}
}
