package com.dt.module.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.base.BaseController;

@Controller
@RequestMapping(value = "/api")
public class ModuleController extends BaseController{

//	@Autowired
//	private DB db = null;
//
//	@RequestMapping("/module/moduleTreelist.do")
//	@Res
//	@Acl
//	public ResData moduleTreelist(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		//只支持三层树
//		String sql = "select "+
//				"decode(cnt,0,level1_name,1,level1_name||'->'||level2_name,level1_name||'->'||level2_name||'->'||level3_name) routename, "+
//				"outer.* "+
//				"from ( "+
//				"select t.* , "+
//				"(select node_name  from sys_menus_node i where i.node_id=t.level1) level1_name, "+
//				"(select node_name  from sys_menus_node i where i.node_id=t.level2) level2_name, "+
//				"(select node_name  from sys_menus_node i where i.node_id=t.level3) level3_name "+
//				"from ( "+
//				"select "+
//				"node_id, "+
//				"module_id, "+
//				"LENGTH(route) - LENGTH(REPLACE(route,'-','')) cnt, "+
//				"decode(instr(route,'-'), "+
//				"0,route, "+
//				"substr(route,1,instr(route,'-') -1)) level1 , "+
//				"decode( LENGTH(route) - LENGTH(REPLACE(route,'-','')), "+
//				"0 , '-1', "+
//				"1 , substr(route,instr(route,'-',1,1)+1,LENGTH(route)-instr(route,'-',1,1)  )  , "+
//				"2 , substr(route,instr(route,'-',1,1)+1 , instr(route,'-',1,2)-instr(route,'-',1,1)-1  ), "+
//				"3 , substr(route,instr(route,'-',1,1)+1 , instr(route,'-',1,2)-instr(route,'-',1,1)-1  ),'-1') level2, "+
//				"decode( LENGTH(route) - LENGTH(REPLACE(route,'-','')), "+
//				"0,'-1', "+
//				"1,'-1', "+
//				"2,substr(route,instr(route,'-',1,2)+1 , length(route) -instr(route,'-',1,2) ), "+
//				"3,substr(route,instr(route,'-',1,2)+1 , instr(route,'-',1,3) -instr(route,'-',1,2)-1 ), "+
//				"'-1')  level3, "+
//				"a.route "+
//				"from sys_menus_node a "+
//				")t) outer order by route ";
//		RcdSet r = db.query(sql);
//		 
//		return  ResData.SUCCESS(r.toJsonArrayWithJsonObject());
//		 
//	}
//	
//	
//	@RequestMapping("/module/module_query.do")
//	@Res
//	@Acl
//	public ResData module_query(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String sql = "select * from sys_modules where deleted='N'";
//		RcdSet r = db.query(sql);
//		
//		return ResData.SUCCESS(r.toJsonArrayWithJsonObject());
//		 
//	}
//
//	@RequestMapping("/module/moduleQueryById.do")
//	@Res
//	@Acl
//	public ResData moduleQueryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String module_id = request.getParameter("MODULE_ID");
//		if (module_id == null || module_id.length() == 0) {
//			return ResData.FAILURE_OPER();
//		}
//		String sql = "select * from sys_modules where MODULE_ID=?  ";
//		return ResData.SUCCESS(db.uniqueRecord(sql, module_id).toJsonObject());
//	 
//	}
//
//	@RequestMapping("/module/module_resource_save.do")
//	@Res
//	@Acl
//	public void module_resource_save(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	}
//
//	@RequestMapping("/module/module_resource_delete.do")
//	@Acl
//	public void module_resource_delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//	}
//
//	@RequestMapping("/module/resource_save.do")
//	@Acl
//	public void resource_save(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//	}
//
//	@RequestMapping("/module/resource_del.do")
//	@Acl
//	public void resource_del(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		 
//	}
//
//	@RequestMapping("/module/moduleDelete.do")
//	@Res
//	@Acl
//	public ResData module_delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		// 删除前请先接绑菜单
//
//		String module_id = request.getParameter("MODULE_ID");
//		if (module_id == null || module_id.length() == 0) {
//			return ResData.FAILURE_OPER();
//		}
//
//		String sql = "select * from sys_menus_node where module_id=? ";
//		RcdSet rs = db.query(sql, module_id);
//		if (rs.size() > 0) {
//			return ResData.FAILURE("已绑定菜单,请先解绑");
//		}
//
//		Update ups = new Update("sys_modules");
//		ups.set("deleted", 'Y');
//		String bsql=ups.getSQL()+" where module_id='"+module_id+"' ";
//	 
//		  db.execute(bsql);
//		 
//		return ResData.SUCCESS_OPER();
//
//	}
//
//	@RequestMapping("/module/moduleSave.do")
//	@Res
//	@Acl
//	public ResData module_save(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//	
//		String module_id = request.getParameter("MODULE_ID");
//		 
//		String sql = "";
//		if (module_id == null || module_id.length() == 0) {
//			// 新增
//			String getidSql = "select LPAD(decode (  max(cast(module_id as int)),null,5,  max(cast(module_id as int))+1),5,'0') vstr from sys_modules";
//			String idstr = db.uniqueRecord(getidSql).getString("vstr");
//			if (idstr == null || idstr.length() == 0) {
//				return ResData.FAILURE_OPER();
//			}
//			Insert ins = new Insert("sys_modules");
//			ins.setIf("MODULE_NAME", request.getParameter("MODULE_NAME"));
//			ins.setIf("KEY", request.getParameter("KEY"));
//			ins.setIf("MARK", request.getParameter("MARK"));
//			ins.set("IS_ACTION", request.getParameter("IS_ACTION"));
//			ins.set("TYPE", "system");
//			ins.set("MODULE_ID", idstr);
//			ins.set("deleted", 'Y');
//			sql = ins.getSQL();
//
//		} else {
//			Update ups = new Update("sys_modules");
//			ups.setIf("KEY", request.getParameter("KEY"));
//			ups.setIf("MARK", request.getParameter("MARK"));
//			ups.set("IS_ACTION", request.getParameter("IS_ACTION"));
//			ups.setIf("MODULE_NAME", request.getParameter("MODULE_NAME"));
//			sql=ups.getSQL()+ " where MODULE_ID='"+module_id+"'";
//		}
//
//		db.execute(sql);
//		 
//		 
//		return ResData.SUCCESS_OPER();
//	}

}
