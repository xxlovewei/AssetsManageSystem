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
@ExcelTarget("OpsNodeEntity")
public class OpsNodeEntity {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Excel(name = "编号", width = 30)
	private String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Excel(name = "名称",width = 30)
	private String name;
	@Excel(name = "IP", width = 15)
	private String ip;
	@Excel(name = "业务类型", width = 15)
	private String systypestr;
	@Excel(name = "位置", width = 15)
	private String syslocstr;
	@Excel(name = "操作系统", width = 15)
	private String sysosstr;
	@Excel(name = "操作系统详情", width = 30)
	private String sysosdtlstr;
	@Excel(name = "数据库", width = 15)
	private String sysdbstr;
	@Excel(name = "数据库详情", width = 18)
	private String sysdbdtlstr;
	@Excel(name = "中间件", width = 40)
	private String middlewarestr;
	@Excel(name = "执行环境", width = 15)
	private String sysexecenvstr;
	@Excel(name = "监控部署", width = 15)
	private String sysmonitorstr;
	@Excel(name = "改密策略", width = 15)
	private String syspwdstrategystr;
	@Excel(name = "密码备注", width = 20)
	private String pwdmark;
	@Excel(name = "负责人", width = 15)
	private String leader;
	
	@Excel(name = "节点备份", width = 35)
	private String nodebackup;
	
	
	@Excel(name = "标签1", width = 15)
	private String label1;
	
	@Excel(name = "标签2", width = 15)
	private String label2;
	
	
	/**
	 * @return the label1
	 */
	public String getLabel1() {
		return label1;
	}

	/**
	 * @param label1 the label1 to set
	 */
	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	/**
	 * @return the label2
	 */
	public String getLabel2() {
		return label2;
	}

	/**
	 * @param label2 the label2 to set
	 */
	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	/**
	 * @return the nodebackup
	 */
	public String getNodebackup() {
		return nodebackup;
	}

	/**
	 * @param nodebackup the nodebackup to set
	 */
	public void setNodebackup(String nodebackup) {
		this.nodebackup = nodebackup;
	}

	@Excel(name = "风险等级", width = 15)
	private String syslevelstr;
	@Excel(name = "运行环境", width = 15)
	private String sysenvstr;
	@Excel(name = "备注", width = 20)
	private String mark;

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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the systypestr
	 */
	public String getSystypestr() {
		return systypestr;
	}

	/**
	 * @param systypestr the systypestr to set
	 */
	public void setSystypestr(String systypestr) {
		this.systypestr = systypestr;
	}

	/**
	 * @return the syslocstr
	 */
	public String getSyslocstr() {
		return syslocstr;
	}

	/**
	 * @param syslocstr the syslocstr to set
	 */
	public void setSyslocstr(String syslocstr) {
		this.syslocstr = syslocstr;
	}

	/**
	 * @return the sysosstr
	 */
	public String getSysosstr() {
		return sysosstr;
	}

	/**
	 * @param sysosstr the sysosstr to set
	 */
	public void setSysosstr(String sysosstr) {
		this.sysosstr = sysosstr;
	}

	/**
	 * @return the sysosdtlstr
	 */
	public String getSysosdtlstr() {
		return sysosdtlstr;
	}

	/**
	 * @param sysosdtlstr the sysosdtlstr to set
	 */
	public void setSysosdtlstr(String sysosdtlstr) {
		this.sysosdtlstr = sysosdtlstr;
	}

	/**
	 * @return the sysdbstr
	 */
	public String getSysdbstr() {
		return sysdbstr;
	}

	/**
	 * @param sysdbstr the sysdbstr to set
	 */
	public void setSysdbstr(String sysdbstr) {
		this.sysdbstr = sysdbstr;
	}

	/**
	 * @return the sysdbdtlstr
	 */
	public String getSysdbdtlstr() {
		return sysdbdtlstr;
	}

	/**
	 * @param sysdbdtlstr the sysdbdtlstr to set
	 */
	public void setSysdbdtlstr(String sysdbdtlstr) {
		this.sysdbdtlstr = sysdbdtlstr;
	}

	/**
	 * @return the middlewarestr
	 */
	public String getMiddlewarestr() {
		return middlewarestr;
	}

	/**
	 * @param middlewarestr the middlewarestr to set
	 */
	public void setMiddlewarestr(String middlewarestr) {
		this.middlewarestr = middlewarestr;
	}

	/**
	 * @return the sysexecenvstr
	 */
	public String getSysexecenvstr() {
		return sysexecenvstr;
	}

	/**
	 * @param sysexecenvstr the sysexecenvstr to set
	 */
	public void setSysexecenvstr(String sysexecenvstr) {
		this.sysexecenvstr = sysexecenvstr;
	}

	/**
	 * @return the sysmonitorstr
	 */
	public String getSysmonitorstr() {
		return sysmonitorstr;
	}

	/**
	 * @param sysmonitorstr the sysmonitorstr to set
	 */
	public void setSysmonitorstr(String sysmonitorstr) {
		this.sysmonitorstr = sysmonitorstr;
	}

	/**
	 * @return the syspwdstrategystr
	 */
	public String getSyspwdstrategystr() {
		return syspwdstrategystr;
	}

	/**
	 * @param syspwdstrategystr the syspwdstrategystr to set
	 */
	public void setSyspwdstrategystr(String syspwdstrategystr) {
		this.syspwdstrategystr = syspwdstrategystr;
	}

	/**
	 * @return the pwdmark
	 */
	public String getPwdmark() {
		return pwdmark;
	}

	/**
	 * @param pwdmark the pwdmark to set
	 */
	public void setPwdmark(String pwdmark) {
		this.pwdmark = pwdmark;
	}

	/**
	 * @return the leader
	 */
	public String getLeader() {
		return leader;
	}

	/**
	 * @param leader the leader to set
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}

	/**
	 * @return the syslevelstr
	 */
	public String getSyslevelstr() {
		return syslevelstr;
	}

	/**
	 * @param syslevelstr the syslevelstr to set
	 */
	public void setSyslevelstr(String syslevelstr) {
		this.syslevelstr = syslevelstr;
	}

	/**
	 * @return the sysenvstr
	 */
	public String getSysenvstr() {
		return sysenvstr;
	}

	/**
	 * @param sysenvstr the sysenvstr to set
	 */
	public void setSysenvstr(String sysenvstr) {
		this.sysenvstr = sysenvstr;
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

	public void fullEntity(JSONObject obj) {
		this.name = obj.getString("name");
		this.id = obj.getString("id");
		this.ip = obj.getString("ip");
		this.systypestr = obj.getString("systypestr");
		this.syslocstr = obj.getString("syslocstr");
		this.sysosstr = obj.getString("sysosstr");
		this.sysosdtlstr = obj.getString("sysosdtlstr");
		this.sysdbstr = obj.getString("sysdbstr");
		this.sysdbdtlstr = obj.getString("sysdbdtlstr");
		this.middlewarestr = obj.getString("middlewarestr");
		this.sysexecenvstr = obj.getString("sysexecenvstr");
		this.sysmonitorstr = obj.getString("sysmonitorstr");
		this.syspwdstrategystr = obj.getString("syspwdstrategystr");
		this.pwdmark = obj.getString("pwdmark");
		this.leader = obj.getString("leader");
		this.syslevelstr = obj.getString("syslevelstr");
		this.sysenvstr = obj.getString("sysenvstr");
		this.mark = obj.getString("mark");
		this.nodebackup=obj.getString("nodebackup");
	}
}
