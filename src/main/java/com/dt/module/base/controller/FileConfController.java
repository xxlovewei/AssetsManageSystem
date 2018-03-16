package com.dt.module.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Update;
import com.dt.module.db.DB;

@Controller()
@RequestMapping("/api/file")
public class FileConfController extends BaseController {
	@Autowired
	private DB db = null;

	@RequestMapping("/fileConfQuery.do")
	@ResponseBody
	@Acl(info="查询文件配置")
	public R fileConfQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RcdSet rs = db.query("select * from sys_file_conf where is_delete='N'");
		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	@RequestMapping("/fileConfQueryById.do")
	@ResponseBody
	@Acl(info="查询文件配置")
	public R fileConfQueryById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Rcd rs = db.uniqueRecord("select * from sys_file_conf where is_delete='N' and id=?",
				request.getParameter("id"));
		return R.SUCCESS_OPER(rs.toJsonObject());
	}

	@RequestMapping("/fileConfSave.do")
	@ResponseBody
	@Acl(info="保存文件配置")
	public R fileConfSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@RequestMapping("/fileConfDelete.do")
	@ResponseBody
	@Acl(info="删除文件配置")
	public R fileConfDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (db.uniqueRecord("select count(1) value from sys_files where bus=?", request.getParameter("bus"))
				.getInteger("value") > 0) {
			return R.FAILURE("已在使用中,不能删除");
		}
		Update ups = new Update("sys_file_conf");
		ups.set("is_delete", "Y");
		ups.where().and("id=?", request.getParameter("bus"));
		db.execute(ups);
		return R.SUCCESS_OPER();
	}
}
