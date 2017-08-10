package com.dt.module.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.db.DB;

@Controller()
@RequestMapping("/file")
public class FileConfController extends BaseController{

	@Autowired
	private DB db = null;

	@RequestMapping("/fileConfQuery.do")
	@Res
	@Acl
	public ResData fileConfQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RcdSet rs=db.query("select * from SYS_FILE_CONF");
		return ResData.SUCCESS(rs.toJsonArrayWithJsonObject());
	}
	
	@RequestMapping("/fileConfQueryById.do")
	@Res
	@Acl
	public ResData fileConfQueryById(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		Rcd rs=db.uniqueRecord("select * from SYS_FILE_CONF where id=?",request.getParameter("ID"));
		return ResData.SUCCESS(rs.toJsonObject());
		
	 
	}

	@RequestMapping("/fileConfSave.do")
	@Res
	@Acl
	public ResData fileConfSave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return null;
	}

	@RequestMapping("/fileConfDelete.do")
	@Res
	@Acl
	public ResData fileConfDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( db.uniqueRecord("select count(1) value from SYS_FILES where bus=?",request.getParameter("bus")).getInteger("value")>0 ){
			return ResData.FAILURE("已在使用中,不能删除");
			
		}
		db.execute("delete from sys_file_conf where id=?",request.getParameter("bus"));
		return ResData.SUCCESS_OPER();
	}

}
